package com.newspeed.sixteenflow.domain.like.repository;

import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto.CommentLikeSearchDetailDto;
import com.newspeed.sixteenflow.domain.like.entity.CommentLike;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    //특정 댓글에 대해 해당 멤버가 이미 좋아요를 눌렀는지 여부를 확인
    Optional<CommentLike> findByMemberAndComment(Member member, Comment comment);

    //특정 댓글에 달린 좋아요 수를 세는 메서드
    int countByCommentId(Long commentId);

    @Query(
            //필요한 필드만 선택해서 반환
            "SELECT new com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto.CommentLikeSearchDetailDto" +
                    // Member Entity에서 가져올 필드 지정: 프로필 이미지 URL, 닉네임
                    "(m.profileImageUrl, m.nickname) " +
                    // CommentLike Entity를 기준으로 Member Entity와 조인
                    "FROM CommentLike cl JOIN cl.member m " +
                    // 특정 댓글 ID에 해당하는 좋아요 데이터만 조회
                    "WHERE cl.comment.id = :commentId")
    Page<CommentLikeSearchDetailDto> findAllLikedMembersByCommentId(
            @Param("commentId") Long commentId,
            Pageable pageable); // 페이징 조건 적용
}
