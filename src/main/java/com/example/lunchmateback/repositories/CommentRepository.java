package com.example.lunchmateback.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.lunchmateback.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    
}
