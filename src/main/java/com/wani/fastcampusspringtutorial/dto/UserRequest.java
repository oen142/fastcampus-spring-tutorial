package com.wani.fastcampusspringtutorial.dto;

import com.wani.fastcampusspringtutorial.model.User;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {

    private String account;
    private String email;
    private String phoneNumber;

    public User toUser() {
        return new User(null, account, email, phoneNumber, LocalDateTime.now(),
            LocalDateTime.now());
    }
}
