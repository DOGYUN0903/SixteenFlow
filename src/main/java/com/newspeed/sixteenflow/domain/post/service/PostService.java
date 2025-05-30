package com.newspeed.sixteenflow.domain.post.service;

import com.newspeed.sixteenflow.domain.post.dto.PostListResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.PostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.global.common.PageResponse;
import org.springframework.data.domain.Pageable;

public interface PostService {
    CreatePostResponseDto create(Long memberId, CreatePostRequestDto requestDto);

    PageResponse<PostResponseDto> findAll(Pageable pageable);

    PostResponseDto findById(Long postId);

    UpdatePostResponseDto update(Long memberId, Long postId, UpdatePostRequestDto requestDto);

    void delete(Long memberId, Long postId);

    PostListResponseDto getFollowingFeeds(Long memberId);

    Post findPostByIdOrElseThrow(Long postId);
}
