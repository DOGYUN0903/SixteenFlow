package com.newspeed.sixteenflow.domain.comments.repository;

import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment, Long> {
}
