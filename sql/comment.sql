-- 댓글 테이블
CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         content TEXT NOT NULL,
                         member_id BIGINT NOT NULL,
                         post_id BIGINT NOT NULL,
                         created_at DATETIME,
                         modified_at DATETIME
);