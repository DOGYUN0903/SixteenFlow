package com.newspeed.sixteenflow.domain.post.service;

import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import com.newspeed.sixteenflow.domain.post.dto.PostListResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.PostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.domain.post.repository.PostRepository;
import com.newspeed.sixteenflow.global.common.PageResponse;
import com.newspeed.sixteenflow.global.exception.post.PostNotFoundException;
import com.newspeed.sixteenflow.global.exception.post.PostUnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
//    private final FollowService followService;

    @Transactional
    @Override
    public CreatePostResponseDto create(Long memberId, CreatePostRequestDto requestDto) {
        Member findMember = memberService.findByIdOrElseThrow(memberId);

        Post post = new Post(
                requestDto.getContent(),
                requestDto.getImageUrl(),
                findMember
        );

        return new CreatePostResponseDto(postRepository.save(post));
    }

    @Override
    public PageResponse<PostResponseDto> findAll(Pageable pageable) {
        Page<PostResponseDto> postResponseDtoPage = postRepository.findAll(pageable)
                .map(post -> new PostResponseDto(post, 0L, 0L));

        return new PageResponse<>(postResponseDtoPage);
    }

    @Override
    public PostResponseDto findById(Long postId) {
        Post findPost = findPostByIdOrElseThrow(postId);

//        Long likeCount = postRepository.likeCount(findPost.getId());
//        Long commentCount = postRepository.commentCount(findPost.getId());

        return new PostResponseDto(findPost, 0L, 0L);
    }

    @Transactional
    @Override
    public UpdatePostResponseDto update(Long memberId, Long postId, UpdatePostRequestDto requestDto) {
        Post findPost = findPostByIdOrElseThrow(postId);


        validatePostOwner(memberId, findPost);

        findPost.update(requestDto.getContent(), requestDto.getImageUrl());

        return new UpdatePostResponseDto(findPost);
    }

    @Transactional
    @Override
    public void delete(Long memberId, Long postId) {
        Post findPost = findPostByIdOrElseThrow(postId);

        validatePostOwner(memberId, findPost);

        postRepository.delete(findPost);
    }

    @Override
    public PostListResponseDto getFollowingFeeds(Long memberId) {
//        // 1. 멤버 ID를 활용해서 팔로잉 아이디들 찾기
//        List<Long> followingIds = followService.findFollowingIdsByMemberId(memberId);
//
//        // 2. 팔로잉이 없다면 예외 던지기
//        if (followingIds.isEmpty()) {
//            throw new PostFollowingsNotFoundException();
//        }
//
//        postRepository.findAllById(followingIds).stream()
//                .iterator()

        return null;
    }

    public Post findPostByIdOrElseThrow(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
    }

    // 게시물 id와 멤버의 id를 비교해서 동일한지 검증하는 메서드입니다.
    private static void validatePostOwner(Long memberId, Post findPost) {
        if (!findPost.getMember().getId().equals(memberId)) {
            throw new PostUnauthorizedException();
        }
    }

}
