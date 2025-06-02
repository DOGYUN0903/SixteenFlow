package com.newspeed.sixteenflow.domain.like.repository;

import com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto.PostLikeSearchDetailDto;
import com.newspeed.sixteenflow.domain.like.entity.PostLike;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("""
            SELECT new com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto.PostLikeSearchDetailDto
                        (m.profileImageUrl, m.nickname)
                        FROM PostLike pl JOIN pl.member m
                        WHERE pl.post.id = :postId""")

    Page<PostLikeSearchDetailDto> findAllLikedMembersByPostId(
            @Param("postId") Long postId,
            Pageable pageable);
}
