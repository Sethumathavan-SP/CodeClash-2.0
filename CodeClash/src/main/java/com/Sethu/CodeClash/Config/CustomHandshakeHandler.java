package com.Sethu.CodeClash.Config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @SuppressWarnings("null")
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
                                      java.util.Map<String, Object> attributes) {
        // Extract username from query parameters or request attributes
        String username = (String) attributes.get("username");
        if (username == null) {
            username = request.getPrincipal() != null ? request.getPrincipal().getName() : "anonymous";
        }

        // Create a custom principal with the username
        final String finalUsername = username;
        return () -> finalUsername;
    }
}
