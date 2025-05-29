package com.newspeed.sixteenflow.domain.post.dto.update;

import com.newspeed.sixteenflow.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePostResponseDto {

    private Long id;

    private String content;

    private String imageUrl;

    private LocalDateTime modifiedAt;

    public UpdatePostResponseDto(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.modifiedAt = post.getModifiedAt();
    }
}
