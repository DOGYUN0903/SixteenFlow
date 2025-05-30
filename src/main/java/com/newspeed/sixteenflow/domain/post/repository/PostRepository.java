package com.newspeed.sixteenflow.domain.post.repository;

import com.newspeed.sixteenflow.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT COUNT(p) FROM PostLike p where p.post.id = :postId")
    Long likeCount(@Param("postId") Long postId);

    @Query("SELECT COUNT(c) FROM Comment c where c.post.id = :postId")
    Long commentCount(@Param("postId") Long postId);

    @Query("SELECT p FROM Post p where p.member.id In :followingIds order by p.modifiedAt desc")
    Page<Post> findPostsByFollowingIds(List<Long> followingIds, Pageable pageable);
}
