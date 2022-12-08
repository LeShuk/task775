package com.example.task775.security.controller;

import com.example.task775.security.model.AccountCredentials;
import com.example.task775.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private String JsonTokenWrapper(String token) {
        return "{\n\t\"token\": \"" + token + "\"\n}\n";
    }
    @PostMapping("/token")
    public String getToken(@RequestBody AccountCredentials accountCredentials) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(accountCredentials.getName(), accountCredentials.getPassword());

        Authentication auth = authenticationManager.authenticate(authenticationToken);

        String jwt = jwtService.getToken(auth.getName());

        return JsonTokenWrapper(jwt);
    }
}
