package com.jamesdev.blog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDupCheckDto {
    public String email;
}
