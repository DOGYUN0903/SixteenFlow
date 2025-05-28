package com.newspeed.sixteenflow.domain.post.service;

import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.entity.Post;

public interface PostService {
    CreatePostResponseDto createPost(Long memberId, CreatePostRequestDto requestDto);
}
