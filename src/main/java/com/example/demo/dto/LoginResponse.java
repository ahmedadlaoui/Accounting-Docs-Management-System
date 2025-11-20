package com.example.demo.dto;

import com.example.demo.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String token;
    private String email;
    private String fullName;
    private Role role;
    private Long userId;
    private Long companyId;
    private String companyName;
}
