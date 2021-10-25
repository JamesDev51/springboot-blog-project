package com.jamesdev.blog.dto;

import com.jamesdev.blog.model.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    String title;
    String content;
    public Board toEntity(){
        return Board
                .builder()
                .title(title)
                .content(content)
                .build();

    }
}
