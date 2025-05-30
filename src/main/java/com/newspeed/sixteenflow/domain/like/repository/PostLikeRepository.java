package com.newspeed.sixteenflow.domain.like.repository;

import com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto.PostLikeSearchDetailDto;
import com.newspeed.sixteenflow.domain.like.entity.PostLike;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    // 특정 게시글에 대해 해당 멤버가 이미 좋아요를 눌렀는지 여부를 확인
    Optional<PostLike> findByMemberAndPost(Member member, Post post);
    // 특정 게시글에 달린 좋아요 수를 세는 메서드
    int countByPostId(Long postId);

    // postId에 좋아요한 회원들의 profileImageUrl과 nickname을 조회 (JPQL 쿼리)
    @Query("SELECT new com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto.PostLikeSearchDetailDto" +
            "(m.profileImageUrl, m.nickname) " +
            // PostLike entity를 기준으로 Member entity와 Join하여 회원 정보 조회
            "FROM PostLike pl JOIN pl.member m " +
            // 특정 postId에 해당하는 PostLike 데이터만 받음
            "WHERE pl.post.id = :postId")
    //PostLikeSearchDetailDto 리스트로 반환
    List<PostLikeSearchDetailDto> findAllLikedMembersByPostId(@Param("postId") Long postId);
}
