-- 회원 테이블
CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        nickname VARCHAR(100) UNIQUE,
                        username VARCHAR(100),
                        address VARCHAR(255),
                        phone_number VARCHAR(255) UNIQUE,
                        profile_image_url VARCHAR(500),
                        is_deleted BOOLEAN,
                        created_at DATETIME,
                        modified_at DATETIME
);
