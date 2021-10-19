package com.fortice.popo.domain.auth.dto;

import com.fortice.popo.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    String email;

    @NotBlank
    String password;

    @NotBlank
    String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(this.email)
                .password(passwordEncoder.encode(this.password))
                .phone(this.phone)
                .authority(User.Authority.ROLE_USER)
                .build();
    }
}
