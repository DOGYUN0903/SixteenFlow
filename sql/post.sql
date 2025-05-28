-- 게시글 테이블
CREATE TABLE post (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      content TEXT NOT NULL,
                      member_id BIGINT NOT NULL,
                      image_url VARCHAR(500),
                      created_at DATETIME,
                      modified_at DATETIME
);