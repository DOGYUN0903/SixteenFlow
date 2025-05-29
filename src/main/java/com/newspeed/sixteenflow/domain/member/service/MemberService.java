package com.newspeed.sixteenflow.domain.member.service;

import com.newspeed.sixteenflow.domain.member.dto.MemberRequestDto;
import com.newspeed.sixteenflow.domain.member.dto.MemberResponseDto;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.repository.MemberRepository;
import com.newspeed.sixteenflow.global.config.PasswordEncoder;
import com.newspeed.sixteenflow.global.exception.member.MemberException;
import com.newspeed.sixteenflow.global.response.error.MemberError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto create(MemberRequestDto requestDto) {
        String phoneNumber = requestDto.getPhoneNumber();
        String profileImageUrl = (requestDto.getProfileImageUrl() == null)
                ? "https://example.com/images/guestProfileImage.jpg" : requestDto.getProfileImageUrl();

        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new MemberException(MemberError.MEMBER_EMAIL_EXIST);
        }

        if (memberRepository.existsByNickname(requestDto.getNickname())) {
            throw new MemberException(MemberError.MEMBER_NICKNAME_EXIST);
        }

        if (memberRepository.existsByPhoneNumber(phoneNumber) && phoneNumber != null) {
            throw new MemberException(MemberError.MEMBER_PHONE_NUMBER_EXIST);
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .profileImageUrl(profileImageUrl)
                .address(requestDto.getAddress())
                .username(requestDto.getUsername())
                .nickname(requestDto.getNickname())
                .password(encodedPassword)
                .phoneNumber(phoneNumber)
                .build();

        Member SavedMember = memberRepository.save(member);

        return MemberResponseDto.builder()
                .id(SavedMember.getId())
                .email(SavedMember.getEmail())
                .profileImageUrl(profileImageUrl)
                .username(SavedMember.getUsername())
                .nickname(SavedMember.getNickname())
                .address(SavedMember.getAddress())
                .phoneNumber(SavedMember.getPhoneNumber())
                .createdAt(SavedMember.getCreatedAt())
                .build();
    }

    public Member findByIdOrElseThrow(Long Id) {
        return memberRepository.findById(Id).orElseThrow(() -> new MemberException(MemberError.MEMBER_NOT_FOUND));
    }

    public MemberResponseDto findById(Long Id) {
        Member foundMember = findByIdOrElseThrow(Id);
        // todo: 팔로우 조회

        // 본인 프로필 조회 시
//        return MemberResponseDto.builder()
//                .email(foundMember.getEmail())
//                .profileImageUrl(foundMember.getProfileImageUrl())
//                .username(foundMember.getUsername())
//                .nickname(foundMember.getNickname())
//                .address(foundMember.getAddress())
//                .phoneNumber(foundMember.getPhoneNumber())
//                .followingCount(5L)
//                .followerCount(5L)
//                .createdAt(foundMember.getCreatedAt())
//                .modifiedAt(foundMember.getModifiedAt())
//                .build();

        // 타인 프로필 조회 시
        return MemberResponseDto.builder()
                .profileImageUrl(foundMember.getProfileImageUrl())
                .nickname(foundMember.getNickname())
                .followingCount(5L)
                .followerCount(5L)
                .createdAt(foundMember.getCreatedAt())
                .modifiedAt(foundMember.getModifiedAt())
                .build();
    }
}
