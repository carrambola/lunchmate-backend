package com.example.lunchmateback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Comment;
import com.example.lunchmateback.models.Recipe;
import com.example.lunchmateback.models.User;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByAuthor(User author);
    List<Comment> findByRecipe(Recipe recipe);
}
