package com.newspeed.sixteenflow.domain.post.repository;

import com.newspeed.sixteenflow.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
