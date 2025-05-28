package com.newspeed.sixteenflow.domain.post.service;

import com.newspeed.sixteenflow.domain.post.dto.PostListResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.PostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import jakarta.validation.Valid;

public interface PostService {
    CreatePostResponseDto createPost(Long memberId, CreatePostRequestDto requestDto);

    PostListResponseDto findAllPost();

    PostResponseDto findPostById(Long postId);

    UpdatePostResponseDto updatePost(Long postId, UpdatePostRequestDto requestDto);
}
