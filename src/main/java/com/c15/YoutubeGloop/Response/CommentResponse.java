package com.c15.YoutubeGloop.Response;

import com.c15.YoutubeGloop.Services.CommentService;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
public class CommentResponse
{
    @Autowired
    CommentService commentService;
    public ResponseEntity<?> getThreadsFromVid(String videoID)
    {
        ResponseEntity<?> response;
        try{
            CommentThreadListResponse daResposne = commentService.getVideoThreads(videoID);
            response = new ResponseEntity<>(daResposne, HttpStatus.FOUND);
            return response;
        }catch(GeneralSecurityException exception){
            response = new ResponseEntity<>("Something went wrong! GeneralSecurityException", HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
        catch(IOException exception){
            response = new ResponseEntity<>("Something went wrong! IOException", HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    public ResponseEntity<?> addThreadsToDB(CommentThreadListResponse cTLR)
    {
        ResponseEntity<?> response;
        try
        {
            commentService.addThreadsToDB(cTLR);
            response=new ResponseEntity<>(HttpStatus.CREATED);

        }
        catch(Exception exception)
        {
            response=new ResponseEntity<>("Something went wrong!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}

