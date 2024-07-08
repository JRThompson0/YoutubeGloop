package com.c15.YoutubeGloop.Services;

import com.c15.YoutubeGloop.Repositories.CommentRepo;
import com.c15.YoutubeGloop.Repositories.CommentThreadRepo;
import com.c15.YoutubeGloop.Models.LocalComment;
import com.c15.YoutubeGloop.Models.LocalCommentThread;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class CommentService
{
    private static final String DEVELOPER_KEY = "AIzaSyD3zxTyEGIR6fNSHtuNS1Ifj-Ag5qkUMyM";
    private static final String APPLICATION_NAME = "PooTube";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public static YouTube getService() throws GeneralSecurityException, IOException
    {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null).setApplicationName(APPLICATION_NAME).build();
    }
    private CommentRepo commentRepo;
    private CommentThreadRepo commentThreadRepo;

    // Define and execute the API request
    public CommentThreadListResponse getVideoThreads(String videoID) throws GeneralSecurityException, IOException {
        YouTube youtubeService = CommentService.getService();
        YouTube.CommentThreads.List request = youtubeService.commentThreads().list("snippet,replies,id");
        CommentThreadListResponse response = request.setKey(DEVELOPER_KEY).setVideoId("cr1KaZ11KCo").execute();
        return response;
    }
    public void addThreadsToDB(CommentThreadListResponse commentThreadListResponse)
    {
        List<CommentThread> threadList = commentThreadListResponse.getItems();
        LocalCommentThread localThread;
        for(CommentThread thisThread:threadList)
        {
            localThread = new LocalCommentThread(thisThread);
            LocalComment topComment = localThread.getTopLevelComment();
            commentRepo.save(topComment);
            List<LocalComment> listComments = localThread.getComments();
            commentRepo.saveAll(listComments);
            commentThreadRepo.save(localThread);
        }
    }
}
