package com.newspeed.sixteenflow.domain.post.dto.create;

import com.newspeed.sixteenflow.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePostResponseDto {

    private Long id;

    private Long memberId; // 회원의 고유 Id

    private String nickname; // 회원의 닉네임

    private String profileImageUrl; // 회원의 이미지 주소

    private String content;

    private String imageUrl;

    private LocalDateTime createdAt;

    public CreatePostResponseDto(Post post) {
        this.id = post.getId();
        this.memberId = post.getMember().getId();
        this.nickname = post.getMember().getNickname();
        this.profileImageUrl = post.getMember().getProfileImageUrl();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.createdAt = post.getCreatedAt();
    }
}
