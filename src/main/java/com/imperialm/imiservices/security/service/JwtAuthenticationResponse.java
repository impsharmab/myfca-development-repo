package com.imperialm.imiservices.security.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    
    @Getter @Setter
    private String name = "";
    
    @Getter @Setter
    private boolean isAdmin = false;
    
    @Getter @Setter
    private List<String> positionCode;
    
    @Getter @Setter
    private List<String> dealerCode;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
        this.positionCode = new ArrayList<String>();
        this.dealerCode = new ArrayList<String>();
    }

    public String getToken() {
        return this.token;
    }
}
