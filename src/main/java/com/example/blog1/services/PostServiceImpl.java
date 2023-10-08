package com.example.blog1.services;

import com.example.blog1.repository.PostRepository;
import com.example.blog1.entity.Post;
import com.example.blog1.exception.ResourceNotFound;
import com.example.blog1.payload.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private PostRepository repoo;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository repoo, ModelMapper mapper) {
        this.repoo = repoo;
        this.mapper = mapper;
    }

    @Override
    public PostDto saveRecord(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post save = repoo.save(post);
        return mapToDto(save);
    }

    @Override
    public void deleteRecord(long id) {
        repoo.deleteById(id);
    }

    @Override
    public PostDto findRecord(long id) {
        Post post = repoo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Record not found with id:"+ id)
        );

        return mapToDto(post);
    }

    @Override
    public List<PostDto> getAllRecords() {
        List<Post> all = repoo.findAll();
        List<PostDto> collect = all.stream().map(list -> (mapToDto(list))).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PostDto updateRecord(long id, PostDto postDto) {
        Post post = repoo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Record not found with id:" + id)
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post save = repoo.save(post);
        return mapToDto(save);

    }

    private Post mapToEntity(PostDto postDto) {
        return mapper.map(postDto, Post.class);

    }
    private PostDto mapToDto(Post post) {
        return mapper.map(post, PostDto.class);

    }
}
