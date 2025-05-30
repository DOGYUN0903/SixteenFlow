package com.newspeed.sixteenflow.domain.like.repository;

import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto.CommentLikeSearchDetailDto;
import com.newspeed.sixteenflow.domain.like.entity.CommentLike;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    //특정 댓글에 대해 해당 멤버가 이미 좋아요를 눌렀는지 여부를 확인
    Optional<CommentLike> findByMemberAndComment(Member member, Comment comment);

    //특정 댓글에 달린 좋아요 수를 세는 메서드
    int countByCommentId(Long commentId);

    //commentId에 좋아요한 회원들의 profileImageUrl과 nickname을 조회 (JPQL 쿼리)
    @Query("SELECT new com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto.CommentLikeSearchDetailDto" +
            "(m.profileImageUrl, m.nickname) " +
            // CommentLike entity를 기준으로 Member entity와 Join하여 회원 정보 조회
            "FROM CommentLike cl JOIN cl.member m " +
            // 특정 commentId에 해당하는 CommentLike 데이터만 받음
            "WHERE cl.comment.id = :commentId")
    //CommentLikeSearchDetailDto 리스트로 반환
    List<CommentLikeSearchDetailDto> findAllLikedMembersByCommentId(@Param("commentId") Long commentId);

}
