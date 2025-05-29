package com.newspeed.sixteenflow.domain.like.service;

import com.newspeed.sixteenflow.domain.like.dto.PostLikeResponseDto;
import com.newspeed.sixteenflow.domain.like.entity.PostLike;
import com.newspeed.sixteenflow.domain.like.repository.PostLikeRepository;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.repository.MemberRepository;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.domain.post.repository.PostRepository;
import com.newspeed.sixteenflow.global.exception.like.PostLikeException;
import com.newspeed.sixteenflow.global.response.error.LikeError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository; //memberId
    private final PostRepository postRepository; //postId

    //좋아요 누르기 (toggle)/ 취소 == HardDelete, 만일 existing되어 있는경우는 좋아요가 눌러진 상태
    public PostLikeResponseDto toggleLike(Long memberId, Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostLikeException());

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new PostLikeException());


        Optional<PostLike> isExisting = postLikeRepository.findByMemberAndPost(member, post);

        boolean like; // 좋아요 필요가 있나? 명시적? 한번 확인 할것(DB에는 안들어가지 않는가)
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
}
