package com.newspeed.sixteenflow.domain.post.dto;

import com.newspeed.sixteenflow.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long id;

    private PostMemberDto postMemberDto;

    private String content;

    private String imageUrl;

    private LocalDateTime modifiedAt;

    private Long likeCount;

    private Long commentCount;

    public PostResponseDto(Post post, Long likeCount, Long commentCount) {
        this.id = post.getId();
        this.postMemberDto = new PostMemberDto(
                post.getMember().getId(),
                post.getMember().getNickname(),
                post.getMember().getProfileImageUrl()
        );
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.modifiedAt = post.getModifiedAt();
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }
}
