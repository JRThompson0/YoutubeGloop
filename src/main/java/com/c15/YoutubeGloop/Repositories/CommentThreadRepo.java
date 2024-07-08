package com.c15.YoutubeGloop.Repositories;

import com.c15.YoutubeGloop.Models.LocalCommentThread;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentThreadRepo extends JpaRepository<LocalCommentThread, String>
{

}
