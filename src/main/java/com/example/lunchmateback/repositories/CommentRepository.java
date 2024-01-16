package com.example.lunchmateback.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    
}
