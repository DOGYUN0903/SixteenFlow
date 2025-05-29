package com.newspeed.sixteenflow.domain.post.repository;

import com.newspeed.sixteenflow.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query("SELECT COUNT(p) FROM PostLike p where p.post.id = :postId")
//    Long likeCount(@Param("postId") Long postId);
//
//    @Query("SELECT COUNT(c) FROM Comment c where c.post.id = :postId")
//    Long commentCount(@Param("postId") Long postId);


}
