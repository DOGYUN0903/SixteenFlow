package com.newspeed.sixteenflow.domain.post.entity;

import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public Post(String content, String imageUrl, Member member) {
        this.content = content;
        this.imageUrl = imageUrl;
        this.member = member;
    }


    public void update(String content, String imageUrl) {
        if (content != null) {
            this.content = content;
        }
        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }
    }
}