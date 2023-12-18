package com.example.lunchmateback.repositories;

import com.example.lunchmateback.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Comment;

import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Override
    Optional<Comment> findById(Long id);
}
