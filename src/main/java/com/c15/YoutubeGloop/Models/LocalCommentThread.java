package com.c15.YoutubeGloop.Models;

import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentThread;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class LocalCommentThread
{
    @Id
    @Column(name="THREAD_ID")
    private String id;
    @OneToMany
    @JoinColumn(name="COMMENT_ID")
    private List<LocalComment> comments;
    private Boolean canReply;
    private String channelId;
    private Boolean isPublic;
    @OneToOne
    @JoinColumn(name="COMMENT_ID")
    private LocalComment topLevelComment;
    private Long totalReplyCount;
    @Column(name="VIDEO_ID")
    private String videoId;

    public LocalCommentThread() {
    }

    public LocalCommentThread(String id, List<LocalComment> comments, Boolean canReply, String channelId, Boolean isPublic, LocalComment topLevelComment, Long totalReplyCount, String videoId) {
        this.id = id;
        this.comments = comments;
        this.canReply = canReply;
        this.channelId = channelId;
        this.isPublic = isPublic;
        this.topLevelComment = topLevelComment;
        this.totalReplyCount = totalReplyCount;
        this.videoId = videoId;
    }

    public LocalCommentThread(CommentThread thread)
    {
        this.id = thread.getId();
        this.canReply = thread.getSnippet().getCanReply();
        this.channelId = thread.getSnippet().getChannelId();
        this.isPublic = thread.getSnippet().getIsPublic();
        this.totalReplyCount = thread.getSnippet().getTotalReplyCount();
        this.videoId = thread.getSnippet().getVideoId();
        List<Comment> comments = thread.getReplies().getComments();
        for(Comment thisComment:comments)
        {
            LocalComment input = new LocalComment(thisComment);
            this.comments.add(input);
        }
        Comment comment = thread.getSnippet().getTopLevelComment();
        this.topLevelComment=new LocalComment(comment);
    }
}
