package com.newspeed.sixteenflow.domain.member.entity;

import com.newspeed.sixteenflow.global.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String profileImageUrl;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String address;

    @Column(unique = true)
    private String phoneNumber;

    private boolean isDeleted = false;

    @Builder
    public Member(String email, String profileImageUrl, String password, String username, String nickname, String address, String phoneNumber) {
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
