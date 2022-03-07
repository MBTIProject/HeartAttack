package com.example.mbti.advice;

import com.example.mbti.model.Poster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetPost {

    private boolean success;

    private String msg;

    private Long post_id;

    private int poster_view_count;

    public GetPost(boolean success, String msg, Poster poster){
        this.success = success;
        this.msg = msg;
        this.post_id = poster.getId();
        this.poster_view_count = poster.getPoster_view_count();
    }

}
