package com.example.mbti.advice;

import com.example.mbti.dto.response.PosterResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Success {
    private boolean success;

    private String msg;

    private Object data;

    public Success(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public Success(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}