package com.newspeed.sixteenflow.domain.like.service;

import com.newspeed.sixteenflow.domain.like.dto.PostLikeResponseDto;
import com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto.PostLikeSearchDetailDto;
import com.newspeed.sixteenflow.domain.like.entity.PostLike;
import com.newspeed.sixteenflow.domain.like.repository.PostLikeRepository;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.domain.post.service.PostService;
import com.newspeed.sixteenflow.global.common.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostService postService;
    private final MemberService memberservice;

    //좋아요 누르기 (toggle)/ 취소 == HardDelete, 만일 existing되어 있는경우는 좋아요가 눌러진 상태
    public PostLikeResponseDto toggleLike(Long memberId, Long postId) {

    // TODO: JWT의 사용자 ID와 넘어온 memberId가 일치하는지 확인

        Post post = postService.findPostByIdOrElseThrow(postId);
        Member member = memberservice.findByIdOrElseThrow(memberId);


        Optional<PostLike> isExisting = postLikeRepository.findByMemberAndPost(member, post);

        boolean like; // 명시적
        if (isExisting.isPresent()) { //이미 좋아요인 경우 취소 존재하므로 isPresent가 true로 나옴.
            postLikeRepository.delete(isExisting.get());
            like = false;

        } else { //좋아요를 하지 않은 경우
            PostLike postLike = PostLike.of(post, member);
            postLikeRepository.save(postLike); //DB에 저장
            like = true; //좋아요 상태를 만들어 줌.
        }

        int likeCount = postLikeRepository.countByPostId(postId);
        return new PostLikeResponseDto(postId, likeCount, like);
    }

    //좋아요 전체 조회기능 구현
    public PageResponse<PostLikeSearchDetailDto> getLikedMembersByPost(Long postId, Pageable pageable) {
        //댓글 존재 여부 확인
        postService.findPostByIdOrElseThrow(postId); // 게시글 존재 여부 검증
        //좋아요 누른 사용자 목록을 페이지 단위로 조회
        Page<PostLikeSearchDetailDto> likedMembers = postLikeRepository.findAllLikedMembersByPostId(postId, pageable);
        return new PageResponse<>(likedMembers);
    }

}
