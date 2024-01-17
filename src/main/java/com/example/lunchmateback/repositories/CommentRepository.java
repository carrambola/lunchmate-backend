package com.example.lunchmateback.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Comment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findByRecipe(Long recipeId);
    @Query("UPDATE Comment c " + "SET c.description = ?2 " + "WHERE c.id = ?1 ")
    @Transactional
    @Modifying
    int updateDescription(Long id, String description);
    @Query("UPDATE Comment c " + "SET c.title = ?2 " + "WHERE c.id = ?1 ")
    @Transactional
    @Modifying
    int updateTitle(Long id, String title);

    @Query("DELETE FROM Comment c WHERE c.id=?1")
    @Modifying
    @Transactional
    int deleteComment(Long commentId);
    @Query("SELECT c FROM Comment c WHERE c.recipe.id = ?1")
    List<Comment> findAllByRecipeId(Long recipeId);



    
}
