package com.newspeed.sixteenflow.domain.member.service;

import com.newspeed.sixteenflow.domain.member.dto.*;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.repository.MemberRepository;
import com.newspeed.sixteenflow.global.config.PasswordEncoder;
import com.newspeed.sixteenflow.global.exception.member.MemberException;
import com.newspeed.sixteenflow.global.response.error.MemberError;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Member findByIdOrElseThrow(Long id) {
        Optional<Member> foundMember = memberRepository.findByIdAndIsDeleted(id, false);
        return foundMember.orElseThrow(() -> new MemberException(MemberError.MEMBER_NOT_FOUND));
    }

    public MemberResponseDto findById(Long id) {
        Member foundMember = findByIdOrElseThrow(id);
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

    @Transactional
    public MemberResponseDto update(Long id, MemberUpdateRequestDto updateDto) {
        Member foundMember = findByIdOrElseThrow(id);

        if (updateDto.getEmail() != null) {
            if (memberRepository.existsByEmail(updateDto.getEmail())) {
                throw new MemberException(MemberError.MEMBER_EMAIL_EXIST);
            }
            foundMember.updateEmail(updateDto.getEmail());
        }

        if (updateDto.getProfileImageUrl() != null) {
            foundMember.updateProfileImageUrl(updateDto.getProfileImageUrl());
        }

        if (updateDto.getNickname() != null) {
            if (memberRepository.existsByNickname(updateDto.getNickname())) {
                throw new MemberException(MemberError.MEMBER_NICKNAME_EXIST);
            }
            foundMember.updateNickname(updateDto.getNickname());
        }


        if (updateDto.getPhoneNumber() != null) {
            if (memberRepository.existsByPhoneNumber(updateDto.getPhoneNumber())) {
                throw new MemberException(MemberError.MEMBER_PHONE_NUMBER_EXIST);
            }
            foundMember.updatePhoneNumber(updateDto.getPhoneNumber());
        }

        if (updateDto.getAddress() != null) {
            foundMember.updateAddress(updateDto.getAddress());
        }

        return MemberResponseDto.builder()
                .email(foundMember.getEmail())
                .profileImageUrl(foundMember.getProfileImageUrl())
                .nickname(foundMember.getNickname())
                .address(foundMember.getAddress())
                .phoneNumber(foundMember.getPhoneNumber())
                .modifiedAt(foundMember.getModifiedAt())
                .build();
    }

    @Transactional
    public void changePassword(Long id, ChangePasswordRequestDto passwordDto) {
        Member foundMember = findByIdOrElseThrow(id);

        if (!passwordEncoder.matches(passwordDto.getOldPassword(), foundMember.getPassword())) {
            throw new MemberException(MemberError.MEMBER_INCORRECT_PASSWORD);
        }

        if (passwordDto.getOldPassword().equals(passwordDto.getNewPassword())) {
            throw new MemberException(MemberError.MEMBER_SAME_PASSWORD);
        }

        String encodedPassword = passwordEncoder.encode(passwordDto.getNewPassword());
        foundMember.updatePassword(encodedPassword);
    }

    @Transactional
    public void delete(Long id, MemberDeleteRequestDto deleteDto) {
        Member foundMember = findByIdOrElseThrow(id);

        if (!passwordEncoder.matches(deleteDto.getPassword(), foundMember.getPassword())) {
            throw new MemberException(MemberError.MEMBER_INCORRECT_PASSWORD);
        }

        foundMember.delete();
    }
}
