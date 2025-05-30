package com.newspeed.sixteenflow.domain.comments.service;
import com.newspeed.sixteenflow.domain.comments.dto.CommentResponse;
import com.newspeed.sixteenflow.domain.comments.dto.CreateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.dto.UpdateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import com.newspeed.sixteenflow.domain.comments.repository.CommentRepository;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.domain.post.service.PostService;
import com.newspeed.sixteenflow.global.common.PageResponse;
import com.newspeed.sixteenflow.global.exception.comment.CommentException;
import com.newspeed.sixteenflow.global.exception.member.MemberException;
import com.newspeed.sixteenflow.global.response.error.CommentError;
import com.newspeed.sixteenflow.global.response.error.MemberError;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//
@Service
public class CommentService {
    //속성
    private final CommentRepository commentRepository;

    private final PostService postService;

    private final MemberService memberService;



    //생성자

    public CommentService(CommentRepository commentRepository, PostService postService, MemberService memberService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.memberService = memberService;
    }


//기능

    /**
     * 댓글  생성
     */
    public CommentResponse createComment(Long postId, Long memberId, CreateCommentRequest createRequest){
        //1. 게시글 조회
        Member foundMember = memberService.findByIdOrElseThrow(memberId); //알아서 예외처리
        Post foundPost = postService.findPostByIdOrElseThrow(postId); //알아서 예외처리


        //2. 댓글 생성
        String toWriteContent = createRequest.getContent();

        if(toWriteContent ==null || toWriteContent.equals("")){
            throw new CommentException(CommentError.COMMENT_CONTENT_EMPTY);
        }

        Comment newComment = new Comment(toWriteContent, foundMember, foundPost );

        //4. 저장
        Comment savedComment = commentRepository.save(newComment);

        //5.  DTO 반환
        return new CommentResponse(savedComment);
    }

    /**
     * 게시글 댓글 전체 조회
     */
    public  PageResponse<CommentResponse> findAllComments(Long postId, Pageable pageable){

        //null값 예외 처리
        Post findPost = postService.findPostByIdOrElseThrow(postId);//포스트 아이디 오류 처리를 위함
//1.게시글 조회
        List <Comment> foundByPostId = commentRepository.findByPostId(findPost.getId());

        //객체를 담을 리스트 초기화
        List <CommentResponse> getCommentList = new ArrayList<>();

        for (Comment getOneComment : foundByPostId){
            getCommentList.add(new CommentResponse(getOneComment));
        }
        // 3. 페이징 처리된 댓글 조회
        Page<Comment> commentPage = commentRepository.findPageByPostId(postId, pageable);
        Page<CommentResponse> map = commentPage.map(CommentResponse::new);

        // 4. 엔티티 → DTO로 변환
        return  new PageResponse<>(map);
    }



    /**
     * 게시글 댓글 단건 조회
     */
    public CommentResponse findComment(Long id){
        //1. 게시글 조회
        Comment comment = findByIdOrElseThrow(id);

        //2.리스폰디티오에 담아서 반환
       return new CommentResponse(comment);
    }

    public Comment findByIdOrElseThrow(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentException(CommentError.COMMENT_NOT_FOUND));
    }

    /**
     *  댓글 수정
     */
    public CommentResponse updateComment(Long id, Long memberId, UpdateCommentRequest updateRequest){
        Comment findComment = findByIdOrElseThrow(id);

        Long commentWriterId = findComment.getMember().getId();
        Long postWriterId = findComment.getPost().getId();

        if (!(commentWriterId.equals(memberId) || postWriterId.equals(memberId) )){
            throw new MemberException(MemberError.MEMBER_UNAUTHORIZED);
        }

        //업데이트할 내용
        String updateContent = updateRequest.getContent();

        if (updateContent.equals(findComment.getContent())){
            throw new CommentException(CommentError.COMMNET_UPDATE_COMMENT_SAME);
        }
        findComment.changeContent(updateRequest.getContent());

        //저장
        commentRepository.save(findComment);

        //반환
        return new CommentResponse(findComment);
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(Long memberId,Long id){

        Comment findComment = findByIdOrElseThrow(id);

        Long commentWriterId = findComment.getMember().getId();
        Long postWriterId = findComment.getPost().getId();

        if (!(commentWriterId.equals(memberId) || postWriterId.equals(memberId) )){
            throw new MemberException(MemberError.MEMBER_UNAUTHORIZED);
        }


        // 내가 작성한 댓글만 삭제할 수 있는 로직
//        toDelete.getMember().getId();
       //현재 댓글의 작성자 id

        commentRepository.delete(findComment);

    }


}

