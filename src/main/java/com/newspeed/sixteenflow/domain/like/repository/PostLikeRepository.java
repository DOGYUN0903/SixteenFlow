package com.newspeed.sixteenflow.domain.like.repository;

import com.newspeed.sixteenflow.domain.like.entity.PostLike;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByMemberAndPost(Member member, Post post);
    int countByPostId(Long postId);

}
