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

    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        // 이메일은 유니크 키 이므로 중복불가
        if (memberRepository.existsMemberByEmail(requestDto.getEmail())) {
            throw new MemberException(MemberError.MEMBER_EMAIL_EXIST);
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        Member member = Member.builder()
                .email(requestDto.getEmail())
                .profileImageUrl(requestDto.getProfileImageUrl())
                .address(requestDto.getAddress())
                .username(requestDto.getUsername())
                .nickname(requestDto.getNickname())
                .password(encodedPassword)
                .phoneNumber(requestDto.getPhoneNumber())
                .build();

        Member SavedMember = memberRepository.save(member);

        return MemberResponseDto.builder()
                .id(SavedMember.getId())
                .email(SavedMember.getEmail())
                .profileImageUrl(SavedMember.getProfileImageUrl())
                .username(SavedMember.getUsername())
                .nickname(SavedMember.getNickname())
                .address(SavedMember.getAddress())
                .phoneNumber(SavedMember.getPhoneNumber())
                .createdAt(SavedMember.getCreatedAt())
                .build();
    }
}
