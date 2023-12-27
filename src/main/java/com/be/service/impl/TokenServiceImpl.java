package com.be.service.impl;

import com.be.common_api.HethongNguoidungToken;
import com.be.repository.TokenRepository;
import com.be.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public HethongNguoidungToken createToken(HethongNguoidungToken token){
        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public HethongNguoidungToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
