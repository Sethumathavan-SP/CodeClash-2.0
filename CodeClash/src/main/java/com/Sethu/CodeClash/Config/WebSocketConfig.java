package com.Sethu.CodeClash.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketInterceptor webSocketInterceptor;
    private final CustomHandshakeHandler customHandshakeHandler;

    public WebSocketConfig(WebSocketInterceptor webSocketInterceptor, CustomHandshakeHandler customHandshakeHandler) {
        this.webSocketInterceptor = webSocketInterceptor;
        this.customHandshakeHandler = customHandshakeHandler;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/queue", "/topic");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connect")
            .setHandshakeHandler(customHandshakeHandler)
            .addInterceptors(webSocketInterceptor)
            .setAllowedOriginPatterns("*")
            .withSockJS();
    }
}

