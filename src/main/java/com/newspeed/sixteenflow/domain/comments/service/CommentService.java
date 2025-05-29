package com.newspeed.sixteenflow.domain.comments.service;

import com.newspeed.sixteenflow.domain.comments.dto.CreateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.dto.Response;
import com.newspeed.sixteenflow.domain.comments.dto.UpdateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import com.newspeed.sixteenflow.domain.comments.repository.CommentRepository;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.repository.MemberRepository;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//
@Service
public class CommentService {
    //속성
    private final CommentRepository commentRepostiory;

    private final PostRepository postRepository;

    private final MemberRepository memberRepository;


    //생성자

    public CommentService(CommentRepository commentRepostiory, PostRepository postRepository, MemberRepository memberRepository) {
        this.commentRepostiory = commentRepostiory;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }


//기능

    /**
     * 댓글  생성
     */
    public Response createComment(Long postId, CreateCommentRequest createRequest){
        //1. 게시글 조회
        Post foundPost = postRepository.findById(createRequest.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));


        //2. 댓글 생성
        String toWriteContent = createRequest.getContent();
        Member foundMember = memberRepository.findById(createRequest.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));


        Comment newComment = new Comment(toWriteContent, foundMember, foundPost );

        //3. Post에 댓글 추가, 다른 파트 확인 후 코드 수정 예정

//        post.getComments().add(newComment); // Post 엔티티에 comment가 리스트로 존재해야 하는 건 아닌지?

        //4. 저장
        Comment savedComment = commentRepostiory.save(newComment);

        //5.  DTO 반환

        return new Response(savedComment);
    }

    /**
     * 게시글 댓글 전체 조회
     */
//    public List<Comment> findAllComments(Long postId){
//        //1.게시글 조회
//        Post foundPost =postRepository.findById(postId)
//                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
//
//        //2. 게시글 내 댓글 리스트 게터로 받아오기
//         return foundPost.getListComments(); //post에서 comment 리스트를 만들어주고 게터 생성 필요
//    }

    /**
     * 게시글 댓글 단건 조회
     */
    public Response findComment(Long id){
        //1. 게시글 조회
        Comment comment = commentRepostiory.findById(id)
                .orElseThrow();

        //2.리스폰디티오에 담아서 반환
       return new Response(comment);
    }

    /**
     *  댓글 수정
     */


    public Response updateComment(Long id, UpdateCommentRequest updateRequest){
        Comment findOne = commentRepostiory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        findOne.changeContent(updateRequest.getContent());

        commentRepostiory.save(findOne);

        return new Response(findOne);
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(Long id){
        Comment toDelete = commentRepostiory.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        toDelete.getMember().getId();
        commentRepostiory.delete(toDelete);
    }
}

