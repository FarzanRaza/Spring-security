package com.example.blog1.controller;

import com.example.blog1.payload.PostDto;
import com.example.blog1.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> saveRecord(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
    return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postService.saveRecord(postDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable("id") long id){
        postService.deleteRecord(id);
        return new ResponseEntity<>("Delete record sucessfully", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findRecord(@PathVariable("id") long id){

        return new ResponseEntity<>(postService.findRecord(id), HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllRecords(){
        List<PostDto> allRecords = postService.getAllRecords();
        return new ResponseEntity<>(allRecords,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updateRecord(@PathVariable("id") long id,@RequestBody PostDto postDto){
        PostDto postDto1 = postService.updateRecord(id, postDto);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

}
