-- 팔로우 테이블
CREATE TABLE follow (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        follower_id BIGINT NOT NULL,
                        following_id BIGINT NOT NULL
);