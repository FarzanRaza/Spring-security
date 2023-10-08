package com.example.blog1.services;

import com.example.blog1.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto saveRecord(PostDto postDto);

    void deleteRecord(long id);

    PostDto findRecord(long id);

    List<PostDto> getAllRecords();

    PostDto updateRecord(long id, PostDto postDto);
}
