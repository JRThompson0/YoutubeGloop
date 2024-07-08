package com.c15.YoutubeGloop.Controllers;

import com.c15.YoutubeGloop.Response.CommentResponse;
import com.c15.YoutubeGloop.Services.CommentService;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class ThreadController
{
    @Autowired
    private CommentResponse commentResponse;
    @GetMapping(value = "/getThreadsFromVid")
    public ResponseEntity<?> getThreadsFromVid(@RequestBody String videoID)
    {
        return commentResponse.getThreadsFromVid(videoID);
    }
    @GetMapping(value="/startYoutubeService")
    public ResponseEntity<?> startYoutubeService()
    {
        HttpStatus httpStatus;
        try {
            CommentService.getService();
            httpStatus=HttpStatus.ACCEPTED;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(httpStatus);
    }


    @PostMapping(value = "/saveCurrentThreads")
    public ResponseEntity<?> addThreadToDB(@RequestBody CommentThreadListResponse cTLR)
    {

        return commentResponse.addThreadsToDB(cTLR);
    }

    @GetMapping(value = "/getMapTest")
    public ResponseEntity<?> doIT()
    {
        return new ResponseEntity<>("WE DID IT", HttpStatus.I_AM_A_TEAPOT);
    }

}