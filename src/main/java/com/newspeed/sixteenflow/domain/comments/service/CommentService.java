package com.newspeed.sixteenflow.domain.comments.service;

import com.newspeed.sixteenflow.domain.comments.dto.CreateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.dto.Response;
import com.newspeed.sixteenflow.domain.comments.dto.UpdateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import com.newspeed.sixteenflow.domain.comments.repository.CommentRepository;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.repository.MemberRepository;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.domain.post.repository.PostRepository;
import com.newspeed.sixteenflow.domain.post.service.PostService;
import com.newspeed.sixteenflow.global.exception.commnet.CommentException;
import com.newspeed.sixteenflow.global.response.error.CommentError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Response createComment(Long postId,Long memberId,  CreateCommentRequest createRequest){
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
        return new Response(savedComment);
    }

    /**
     * 게시글 댓글 전체 조회
     */
    public  List<Response> findAllComments(Long postId){

        //null값 예외 처리
        postService.findPostByIdOrElseThrow(postId); //포스트 아이디 오류 처리를 위함
        //1.게시글 조회
        List<Comment> foundByPostId = commentRepository.findByPostId(postId);


        //객체를 담을 배열 초기화
        List <Response> getCommentList = new ArrayList<>();

        for (Comment getOneComment : foundByPostId){
            getCommentList.add(new Response(getOneComment));
        }
        return getCommentList;
    }



    /**
     * 게시글 댓글 단건 조회
     */
    public Response findComment(Long id){
        //1. 게시글 조회
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentException(CommentError.COMMENT_NOT_FOUND));

        //2.리스폰디티오에 담아서 반환
       return new Response(comment);
    }

    /**
     *  댓글 수정
     */
    public Response updateComment(Long id, UpdateCommentRequest updateRequest){
        Comment findOne = commentRepository.findById(id)
                .orElseThrow(() -> new CommentException(CommentError.COMMENT_NOT_FOUND));

        //업데이트할 내용
        String updateContent = updateRequest.getContent();

        if( updateContent ==null || updateContent.equals("")){
            throw new CommentException(CommentError.COMMENT_CONTENT_EMPTY);
        }

        if (updateContent.equals(findOne.getContent())){
            throw new CommentException(CommentError.COMMNET_UPDATE_COMMENT_SAME);
        }
        findOne.changeContent(updateRequest.getContent());

        //저장
        commentRepository.save(findOne);

        //반환
        return new Response(findOne);
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(Long id){
        Comment toDelete = commentRepository.findById(id)
                .orElseThrow(() -> new CommentException(CommentError.COMMENT_DELETE_NOT_FOUND));

        // 내가 작성한 댓글만 삭제할 수 있는 로직
//        toDelete.getMember().getId();
        toDelete.getMember().getId(); //현재 댓글의 작성자 id

        commentRepository.delete(toDelete);

    }


}

