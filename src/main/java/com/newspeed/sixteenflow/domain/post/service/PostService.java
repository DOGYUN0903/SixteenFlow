package com.newspeed.sixteenflow.domain.post.service;

import com.newspeed.sixteenflow.domain.post.dto.PostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.global.common.PageResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PostService {
    CreatePostResponseDto create(Long memberId, CreatePostRequestDto requestDto);

    PageResponse<PostResponseDto> findPosts(Long memberId, Boolean feed, LocalDate startDate, LocalDate endDate, String keyword, Pageable pageable);

    PostResponseDto findById(Long postId);

    UpdatePostResponseDto update(Long memberId, Long postId, UpdatePostRequestDto requestDto);

    void delete(Long memberId, Long postId);

    Post findPostByIdOrElseThrow(Long postId);
}
