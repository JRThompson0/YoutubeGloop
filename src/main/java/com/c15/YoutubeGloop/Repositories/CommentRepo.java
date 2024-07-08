package com.c15.YoutubeGloop.Repositories;

import com.c15.YoutubeGloop.Models.LocalComment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepo extends JpaRepository<LocalComment, String>
{

}
