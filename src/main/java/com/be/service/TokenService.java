package com.be.service;

import com.be.common_api.HethongNguoidungToken;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    HethongNguoidungToken createToken(HethongNguoidungToken token);

    HethongNguoidungToken findByToken(String token);
}
