package com.newspeed.sixteenflow.domain.like.service;

import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import com.newspeed.sixteenflow.domain.comments.repository.CommentRepository;
import com.newspeed.sixteenflow.domain.comments.service.CommentService;
import com.newspeed.sixteenflow.domain.like.dto.CommentLikeResponseDto;
import com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto.CommentLikeSearchDetailDto;
import com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto.CommentLikeSearchListResponseDto;
import com.newspeed.sixteenflow.domain.like.entity.CommentLike;
import com.newspeed.sixteenflow.domain.like.repository.CommentLikeRepository;
import com.newspeed.sixteenflow.domain.like.repository.PostLikeRepository;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final MemberService memberservice;
    private final PostLikeRepository postLikeRepository;


    public CommentLikeResponseDto toggleLike(Long memberId, Long commentId) {

        //TODO: JWT의 사용자 ID와 넘어온 memberId가 일치하는지 확인

        //이미 존재하는 댓글, 멤버 인가?
        Comment comment = commentService.findByIdOrElseThrow(commentId);
        Member member = memberservice.findByIdOrElseThrow(memberId);

        Optional<CommentLike> isExisting = commentLikeRepository.findByMemberAndComment(member, comment);

        boolean like; // 명시적
        if (isExisting.isPresent()) { //이미 좋아요인 경우 취소 존재하므로 isPresent가 true로 나옴.
            commentLikeRepository.delete(isExisting.get()); //DB 하드 delete
            like = false; //취소를 하는 것임으로 false로 지정
        } else { //만일 DB에 존재하지 않음 ==
            CommentLike commentLike = CommentLike.of(comment,member);
            commentLikeRepository.save(commentLike); //DB에 저장
            like = true; //좋아요 상태로 변경
        }
        int likeCount = commentLikeRepository.countByCommentId(commentId);
        return new CommentLikeResponseDto(commentId, likeCount, like);
    }

    public CommentLikeSearchListResponseDto getLikedMembersByComment(Long commentId) {
        //존재하는 댓글인가?
        Comment comment = commentService.findByIdOrElseThrow(commentId);

        List<CommentLikeSearchDetailDto> likedMembers = commentLikeRepository.findAllLikedMembersByCommentId(commentId);
        int likeCount = commentLikeRepository.countByCommentId(commentId);
        return new CommentLikeSearchListResponseDto(commentId, likeCount, likedMembers);
    }
}
