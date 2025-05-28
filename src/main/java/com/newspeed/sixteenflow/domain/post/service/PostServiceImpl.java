package com.newspeed.sixteenflow.domain.post.service;

import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.repository.MemberRepository;
import com.newspeed.sixteenflow.domain.post.dto.PostListResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.PostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.domain.post.repository.PostRepository;
import com.newspeed.sixteenflow.global.exception.post.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public CreatePostResponseDto createPost(Long memberId, CreatePostRequestDto requestDto) {
        // FIXME: 현재는 임시로 ResponseStatusException 사용 중.
        // 추후 member 도메인에서 커스텀 예외(MemberNotFoundException 등) 정의되면 교체할 것.
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Post post = new Post(
                requestDto.getContent(),
                requestDto.getImageUrl(),
                findMember
        );

        return new CreatePostResponseDto(postRepository.save(post));
    }

    @Override
    public PostListResponseDto findAllPost() {
        // TODO: 현재는 좋아요 수(postLike)와 댓글 수(postComment)를 null로 설정하고 있음.
        // 추후 PostLike, Comment 데이터와 연동하여 실제 수치를 계산해 넣을 예정.
        List<PostResponseDto> postDto = postRepository.findAll().stream()
                .map(post -> new PostResponseDto(post, null, null)) // FIXME: 좋아요/댓글 수 미연동 상태
                .toList();
        return new PostListResponseDto(postDto);
    }

    @Override
    public PostResponseDto findPostById(Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException());

        // TODO: 현재는 좋아요 수(postLike)와 댓글 수(postComment)를 null로 설정하고 있음.
        // 추후 PostLike, Comment 데이터와 연동하여 실제 수치를 계산해 넣을 예정.
        return new PostResponseDto(findPost, null, null); // FIXME: 좋아요/댓글 수 미연동 상태
    }

    @Transactional
    @Override
    public UpdatePostResponseDto updatePost(Long postId, UpdatePostRequestDto requestDto) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException());

        // TODO: 추후에 작성자를 확인하는 인가 로직 추가 예정

        findPost.update(requestDto.getContent(), requestDto.getImageUrl());

        return new UpdatePostResponseDto(findPost);
    }

    @Transactional
    @Override
    public void deletePost(Long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException());

        // TODO: 추후에 작성자를 확인하는 인가 로직 추가 예정

        postRepository.delete(findPost);
    }


}
