package com.newspeed.sixteenflow.domain.follow.entity;

import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private Member follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private Member following;

    public Follow() {
    }

    public Follow(Member follower, Member following) {
        this.follower = follower;
        this.following = following;
    }
}
