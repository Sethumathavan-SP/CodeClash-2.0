package com.Sethu.CodeClash.Controller;

import com.Sethu.CodeClash.Services.PairingService;
import com.Sethu.CodeClash.Config.InboundMessageLogger;
import com.Sethu.CodeClash.models.PairRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class MatchMakingController {

    private final PairingService pairingService;

    public MatchMakingController(PairingService pairingService) {
        this.pairingService = pairingService;
    }
    @MessageMapping("/pair")
    public void pairUser(@Payload PairRequest pairRequest, SimpMessageHeaderAccessor headerAccessor) {
        String username = pairRequest.getUsername();
        if (username == null || username.isEmpty()) {
            System.out.println("[ERROR] Received empty username in pairUser method");
            return;
        }

        String sessionId = headerAccessor.getSessionId();
        if (sessionId == null) {
            System.out.println("[ERROR] Session ID is null");
            return;
        }


        System.out.println("[STOMP INTERCEPTOR] STOMP Command: SEND");
        System.out.println("[PAIR REQUEST] Username: " + username);

        // Log session ID for debugging
        System.out.println("[DEBUG] Session ID: " + sessionId);

        // Store username to session ID mapping
        if (!username.equals("unknown")) {
            InboundMessageLogger.usernameToPrincipalId.put(username, sessionId);
            pairingService.pair(username);
        } else {
            System.out.println("[ERROR] Username is null or unknown");
        }
    }
}
