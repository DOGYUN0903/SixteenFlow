package com.newspeed.sixteenflow.domain.post.service;

import com.newspeed.sixteenflow.domain.follow.service.FollowService;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import com.newspeed.sixteenflow.domain.post.dto.PostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.domain.post.repository.PostRepository;
import com.newspeed.sixteenflow.global.common.PageResponse;
import com.newspeed.sixteenflow.global.exception.member.MemberException;
import com.newspeed.sixteenflow.global.exception.post.PostFollowingsNotFoundException;
import com.newspeed.sixteenflow.global.exception.post.PostNotFoundException;
import com.newspeed.sixteenflow.global.response.error.MemberError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final FollowService followService;

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

    /**
     * 조건에 따라 조회기능이 달라집니다.
     * @param memberId 로그인한 멤버의 id
     * @param feed 뉴스피드 조회인지 확인
     * @param startDate 기간 검색 시작일
     * @param endDate 기간 검색 종료일
     * @param keyword 검색 키워드
     * @param pageable 페이징
     * @return 하단의 조건문에 따른 return 값 변화
     * feed가 true이면 뉴스피드 조회이므로 팔로잉한 피드만 조회
     * feed가 false이고, 시작일, 종료일, 키워드 중 하나라도 있으면 검색 기능 조회
     * 모두 없으면 일반 전체조회
     */
    @Override
    public PageResponse<PostResponseDto> findPosts(
            Long memberId,
            Boolean feed,
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Pageable pageable
    ) {
        if (Boolean.TRUE.equals(feed)) {
            return findFollowingFeeds(memberId, pageable);
        } else if (startDate != null || endDate != null || keyword != null) {
            return findAllWithSearch(startDate, endDate, keyword, pageable);
        } else {
            return findAll(pageable);
        }
    }

    private PageResponse<PostResponseDto> findFollowingFeeds(Long memberId, Pageable pageable) {
        List<Long> followingIds = followService.getFollowingsIds(memberId);

        if (followingIds.isEmpty()) {
            throw new PostFollowingsNotFoundException();
        }

        // TODO : 팔로잉한 사람의 게시물을 가져오는 로직 구현해야함
        Page<Post> postsByFollowingIds = postRepository.findPostsByFollowingIds(followingIds, pageable);

        Page<PostResponseDto> postResponseDtoPage = postsByFollowingIds.map(post -> new PostResponseDto(post, getLikeCount(post), getCommentCount(post)));


        return new PageResponse<>(postResponseDtoPage);
    }

    private PageResponse<PostResponseDto> findAllWithSearch(
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Pageable pageable
    ) {
        // LocalDate.atTime = 몇시, 몇분, 몇초인지 설정해줌
        LocalDateTime startDateTime = (startDate != null) ? startDate.atTime(0, 0, 0) : null;
        LocalDateTime endDateTime = (endDate != null) ? endDate.atTime(23, 59, 59) : null;

        Page<PostResponseDto> postResponseDtoPage = postRepository.findAllWithSearch(startDateTime, endDateTime, keyword, pageable)
                .map(post -> new PostResponseDto(post, getLikeCount(post), getCommentCount(post)));

        return new PageResponse<>(postResponseDtoPage);
    }

    private PageResponse<PostResponseDto> findAll(Pageable pageable) {
        Page<PostResponseDto> postResponseDtoPage = postRepository.findAll(pageable)
                .map(post -> new PostResponseDto(post, getLikeCount(post), getCommentCount(post)));

        return new PageResponse<>(postResponseDtoPage);
    }

    @Override
    public PostResponseDto findById(Long postId) {
        Post findPost = findPostByIdOrElseThrow(postId);

        return new PostResponseDto(findPost, getLikeCount(findPost), getCommentCount(findPost));
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


    public Post findPostByIdOrElseThrow(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException());
    }

    // 게시물 id와 멤버의 id를 비교해서 동일한지 검증하는 메서드입니다.
    private static void validatePostOwner(Long memberId, Post findPost) {
        if (!findPost.getMember().getId().equals(memberId)) {
            throw new MemberException(MemberError.MEMBER_UNAUTHORIZED);
        }
    }

    private Long getLikeCount(Post post) {
        return postRepository.likeCount(post.getId());
    }

    private Long getCommentCount(Post post) {
        return postRepository.commentCount(post.getId());
    }






}
