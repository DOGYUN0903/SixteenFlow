-- 댓글 좋아요 테이블
CREATE TABLE comment_like (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              member_id BIGINT NOT NULL,
                              comment_id BIGINT NOT NULL
);
