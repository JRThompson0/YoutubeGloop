package com.c15.YoutubeGloop.Models;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.Comment;
import com.google.api.services.youtube.model.CommentSnippet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;

@Getter
@Entity
public class LocalComment
{
    @Id
    @Column(name="COMMENT_ID")
    private String ID;
    private String authorChannelId;
    private String authorChannelUrl;
    private String authorDisplayName;
    private String authorProfileImageUrl;
    private String channelId;
    private Long likeCount;
    private String moderationStatus;

    private String parentId;

    private DateTime publishedAt;

    private String textDisplay;

    private DateTime updatedAt;
    @JoinColumn(name="VIDEO_ID")
    private String videoId;
    private String viewerRating;

    public LocalComment() {
    }

    public LocalComment(Comment comment)
    {
        this.ID=comment.getId();
        CommentSnippet snippet = comment.getSnippet();
        this.authorChannelId= (String) snippet.getAuthorChannelId();
        this.authorChannelUrl = snippet.getAuthorChannelUrl();
        this.authorDisplayName=snippet.getAuthorDisplayName();
        this.authorProfileImageUrl = snippet.getAuthorProfileImageUrl();
        this.channelId = snippet.getChannelId();
        this.likeCount= snippet.getLikeCount();
        this.moderationStatus=snippet.getModerationStatus();
        this.parentId = snippet.getParentId();
        this.publishedAt = snippet.getPublishedAt();
        this.textDisplay = snippet.getTextDisplay();
        this.updatedAt = snippet.getUpdatedAt();
        this.videoId = snippet.getVideoId();
        this.viewerRating = snippet.getViewerRating();

    }

    public LocalComment(String ID, String authorChannelId, String authorChannelUrl, String authorDisplayName, String authorProfileImageUrl, String channelId, Long likeCount, String moderationStatus, String parentId, DateTime publishedAt, String textDisplay, DateTime updatedAt, String videoId, String viewerRating) {
        this.ID = ID;
        this.authorChannelId = authorChannelId;
        this.authorChannelUrl = authorChannelUrl;
        this.authorDisplayName = authorDisplayName;
        this.authorProfileImageUrl = authorProfileImageUrl;
        this.channelId = channelId;
        this.likeCount = likeCount;
        this.moderationStatus = moderationStatus;
        this.parentId = parentId;
        this.publishedAt = publishedAt;
        this.textDisplay = textDisplay;
        this.updatedAt = updatedAt;
        this.videoId = videoId;
        this.viewerRating = viewerRating;
    }


    public void setAuthorChannelUrl(String authorChannelUrl) {
        this.authorChannelUrl = authorChannelUrl;
    }

    public void setAuthorDisplayName(String authorDisplayName) {
        this.authorDisplayName = authorDisplayName;
    }

    public void setAuthorProfileImageUrl(String authorProfileImageUrl) {
        this.authorProfileImageUrl = authorProfileImageUrl;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public void setModerationStatus(String moderationStatus) {
        this.moderationStatus = moderationStatus;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setPublishedAt(DateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setTextDisplay(String textDisplay) {
        this.textDisplay = textDisplay;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

}
