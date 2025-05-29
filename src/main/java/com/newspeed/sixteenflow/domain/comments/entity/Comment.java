package com.newspeed.sixteenflow.domain.comments.entity;

import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;




@Getter
@Entity
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column
    private String content;

//    생성자
    public Comment( String content, Member member, Post post) {
        this.content = content;
        this.member = member;
        this.post = post;


    }

    public void changeContent(String content) {
        this.content = content;
    }
}
