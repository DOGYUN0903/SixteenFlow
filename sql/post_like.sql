-- 게시글 좋아요 테이블
CREATE TABLE post_like (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           member_id BIGINT NOT NULL,
                           post_id BIGINT NOT NULL
);