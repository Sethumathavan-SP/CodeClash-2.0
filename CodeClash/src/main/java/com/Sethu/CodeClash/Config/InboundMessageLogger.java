package com.Sethu.CodeClash.Config;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class InboundMessageLogger {

    public static final ConcurrentHashMap<String, String> usernameToPrincipalId = new ConcurrentHashMap<>();
}

