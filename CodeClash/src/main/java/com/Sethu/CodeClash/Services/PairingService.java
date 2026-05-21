package com.Sethu.CodeClash.Services;

import com.Sethu.CodeClash.Config.InboundMessageLogger;
import com.Sethu.CodeClash.db.MatchDAO;
import com.Sethu.CodeClash.db.MatchRequestDAO;
import com.Sethu.CodeClash.db.UserDAO;
import com.Sethu.CodeClash.models.Match;
import com.Sethu.CodeClash.models.MatchRequest;
import com.Sethu.CodeClash.models.User;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PairingService {

    private final MatchRequestDAO matchRequestDAO;
    private final MatchDAO matchDAO;
    private final UserDAO userDAO;
    private final SimpMessagingTemplate messagingTemplate;

    private static final ConcurrentHashMap<String, MatchRequest> waitingQueue = new ConcurrentHashMap<>();

    public PairingService(MatchRequestDAO matchRequestDAO, MatchDAO matchDAO, UserDAO userDAO, SimpMessagingTemplate messagingTemplate) {
        this.matchRequestDAO = matchRequestDAO;
        this.matchDAO = matchDAO;
        this.userDAO = userDAO;
        this.messagingTemplate = messagingTemplate;
    }

    public void pair(String username) {
        System.out.println("[PAIR REQUEST] Username: " + username + " | Principal: " + username);

        // Find user by username
        User user = userDAO.findByUserName(username);
        if (user == null) {
            System.out.println("[PAIRING ERROR] User not found in database: " + username);
            return;
        }

        System.out.println("[DEBUG] User found: " + user.getUsername());

        // Create match request
        MatchRequest matchRequest = new MatchRequest();
        matchRequest.setUser(user);
        matchRequest.setStatus("WAITING");
        matchRequest.setCreatedAt(System.currentTimeMillis());
        matchRequestDAO.save(matchRequest);

        System.out.println("[DEBUG] Match request saved for user: " + username);

        // Check if there's a waiting user
        if (waitingQueue.isEmpty()) {
            System.out.println("[NO MATCH] User " + username + " is waiting in queue");
            waitingQueue.put(username, matchRequest);
        } else {
            // Get the first waiting user
            String firstWaitingUsername = waitingQueue.keys().nextElement();
            MatchRequest waitingRequest = waitingQueue.remove(firstWaitingUsername);

            // Create match
            User waitingUser = waitingRequest.getUser();
            Match match = new Match();
            match.setUser1(waitingUser);
            match.setUser2(user);
            match.setStatus("ACTIVE");
            match.setCreatedAt(System.currentTimeMillis());
            matchDAO.save(match);

            System.out.println("[DEBUG] Match created between " + waitingUser.getUsername() + " and " + user.getUsername());

            // Update match requests
            matchRequest.setStatus("MATCHED");
            waitingRequest.setStatus("MATCHED");
            matchRequestDAO.update(matchRequest);
            matchRequestDAO.update(waitingRequest);

            System.out.println("[MATCH SUCCESS] Paired " + user.getUsername() + " with " + waitingUser.getUsername());

            // Send response to both users
            sendMatchResponse(firstWaitingUsername, waitingUser, user);
            sendMatchResponse(username, user, waitingUser);
        }
    }

    private void sendMatchResponse(String username, User user1, User user2) {
        String destination = "/user/" + username + "/queue/pair";
        System.out.println("[SENDING MESSAGE] To user: " + username + " | Destination: " + destination);

        try {
            messagingTemplate.convertAndSendToUser(
                    username,
                    "/queue/pair",
                    "Match found! Paired with: " + (user1.equals(user2) ? user2.getUsername() : (user1.getUsername().equals(username) ? user2.getUsername() : user1.getUsername()))
            );
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to send message to " + username + ": " + e.getMessage());
        }
    }

    public void removePendingRequest(String username) {
        waitingQueue.remove(username);
    }
}

