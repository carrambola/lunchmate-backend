package com.example.lunchmateback.controllers;

import com.example.lunchmateback.dtos.CommentRequest;
import com.example.lunchmateback.dtos.CommentUpdateRequest;
import com.example.lunchmateback.services.CommentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;

@RestController
@RequestMapping(path = "/api/comments")
@AllArgsConstructor
@CrossOrigin
public class CommentsController {

    private final CommentsService commentService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> saveComment(@RequestBody CommentRequest request) {
        return commentService.saveComment(request);
    }

    @GetMapping(path = "/getFromRecipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readAllComments(@RequestParam("recipeId") Long recipeId) {
        return commentService.getAllComments(recipeId);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateComment(commentUpdateRequest.getCommentId(), commentUpdateRequest.getTitle(), commentUpdateRequest.getDescription());
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> deleteComment(@RequestParam("commentId") Long commentId) {
        return commentService.deleteComment(commentId);
    }

}
