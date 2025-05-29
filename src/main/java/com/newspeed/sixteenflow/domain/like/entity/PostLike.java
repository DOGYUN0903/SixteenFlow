package com.newspeed.sixteenflow.domain.like.entity;

import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "post_like", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"post_id", "member_id"}) // 혹시 모를 중복 좋아요 방지
})
public class PostLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public static PostLike of(Post post, Member member) {
        PostLike like = new PostLike();
        like.post = post;
        like.member = member;
        return like;
    }
}


