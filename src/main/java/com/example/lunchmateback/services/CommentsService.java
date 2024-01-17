package com.example.lunchmateback.services;

import com.example.lunchmateback.dtos.CommentRequest;
import com.example.lunchmateback.dtos.MessageResponse;
import com.example.lunchmateback.models.Comment;
import com.example.lunchmateback.models.User;
import com.example.lunchmateback.repositories.CommentRepository;
import com.example.lunchmateback.repositories.RecipesRepository;
import com.example.lunchmateback.repositories.UserRepository;
import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentsService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    private final RecipesRepository recipesRepository;

    public ResponseEntity<?> saveComment(@RequestBody CommentRequest request) {
        Comment comment = new Comment();
        if (!userRepository.findById(request.getAuthor()).isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Provided user doesn't exist"));
        }
        comment.setAuthor(userRepository.findById(request.getAuthor()).get());
        comment.setAuthor(userRepository.findById(request.getAuthor()).get());
        if (!recipesRepository.findById(request.getRecipe()).isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Provided recipe doesn't exist"));
        }
        comment.setRecipe(recipesRepository.findById(request.getRecipe()).get());
        comment.setDescription(request.getDescription());
        comment.setTitle(request.getTitle());
        commentRepository.save(comment);
        return ResponseEntity.ok().body(new MessageResponse("Comment " + comment.getId() + "created succesfully"));
    }

    public Optional<Comment> getComment(Long recipeId) {
        return commentRepository.findByRecipe(recipeId);
    }

    public ResponseEntity<?> getAllComments(Long recipeId) {
        List<Comment> comments = commentRepository.findAllByRecipeId(recipeId);
        System.out.println(comments.size());
        List<CommentRequest> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            dtos.add(new CommentRequest(comment.getAuthor().getId(), comment.getRecipe().getId(), comment.getTitle(), comment.getDescription()));
        }
        return ResponseEntity.ok().body(dtos);
    }

    public void updateDescription(Long id, String newDescription) {
            commentRepository.updateDescription(id, newDescription);
    }

    public void updateTitle(Long id, String newTitle) {
        commentRepository.updateTitle(id, newTitle);
    }

    public ResponseEntity<?> deleteComment(Long commentId) {
        commentRepository.deleteComment(commentId);
        return ResponseEntity.ok().body("Comment deleted successfully");
    }

    public ResponseEntity<?> updateComment(Long id, String newTitle, String newDescription) {
        if (commentRepository.findById(id).isPresent()) {
            commentRepository.updateTitle(id, newTitle);
            commentRepository.updateDescription(id, newDescription);
            return ResponseEntity.ok().body(commentRepository.findById(id).get().toString());
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Comment not found!"));
        }
    }
}
