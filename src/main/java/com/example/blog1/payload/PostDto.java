package com.example.blog1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private long id;
    @NotNull
    @Size(min = 5,message = "title must have 5 charracter")
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String description;
}
