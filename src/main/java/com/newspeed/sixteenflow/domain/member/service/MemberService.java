package com.newspeed.sixteenflow.domain.member.service;

import com.newspeed.sixteenflow.domain.follow.dto.FollowCountDto;
import com.newspeed.sixteenflow.domain.follow.repository.FollowRepository;
import com.newspeed.sixteenflow.domain.member.dto.*;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.repository.MemberRepository;
import com.newspeed.sixteenflow.global.exception.member.MemberException;
import com.newspeed.sixteenflow.global.response.error.MemberError;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto create(MemberRequestDto requestDto) {
        String profileImageUrl = (requestDto.getProfileImageUrl() == null || requestDto.getProfileImageUrl().trim().isEmpty())
                ? "https://example.com/images/guestProfileImage.jpg" : requestDto.getProfileImageUrl();
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new MemberException(MemberError.MEMBER_EMAIL_EXIST);
        }

        if (memberRepository.existsByNickname(requestDto.getNickname())) {
            throw new MemberException(MemberError.MEMBER_NICKNAME_EXIST);
        }

        if (memberRepository.existsByPhoneNumber(requestDto.getPhoneNumber()) && requestDto.getPhoneNumber() != null) {
            throw new MemberException(MemberError.MEMBER_PHONE_NUMBER_EXIST);
        }

        Member member = MemberRequestDto.toEntity(requestDto, profileImageUrl, encodedPassword);

        Member SavedMember = memberRepository.save(member);

        return MemberResponseDto.toDto(SavedMember);
    }

    public Member findByIdOrElseThrow(Long id) {
        Member foundMember = memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberError.MEMBER_NOT_FOUND));
        if (foundMember.isDeleted()) {
            throw new MemberException(MemberError.MEMBER_DELETED);
        }

        return foundMember;
    }

    public MemberResponseDto getProfileById(Long id, Long loginId) {
        Member foundMember = findByIdOrElseThrow(id);
        FollowCountDto followCountDto = followRepository.countFollowCountsByMemberId(id);
        Long followingCount = followCountDto.followingCount();
        Long followerCount = followCountDto.followerCount();

        // 본인 프로필 조회 시
        if (id.equals(loginId)) {
            return MemberResponseDto.toDetailProfileDto(foundMember, followingCount, followerCount);
        }
        // 타인 프로필 조회 시
        return MemberResponseDto.toPublicProfileDto(foundMember, followingCount, followerCount);
    }

    @Transactional
    public MemberUpdateResponseDto update(Long id, MemberUpdateRequestDto updateDto) {
        if (updateDto.isAllFieldsNullOrBlank()) {
            throw new MemberException(MemberError.MEMBER_NO_UPDATE_FIELDS);
        }

        Member foundMember = findByIdOrElseThrow(id);

        updateEmailIfValid(foundMember, updateDto.getEmail());
        updateProfileImageUrlIfValid(foundMember, updateDto.getProfileImageUrl());
        updateNicknameIfValid(foundMember, updateDto.getNickname());
        updatePhoneNumberIfValid(foundMember, updateDto.getPhoneNumber());
        updateAddressIfValid(foundMember, updateDto.getAddress());

        return MemberUpdateResponseDto.toDto(foundMember);
    }

    @Transactional
    public void changePassword(Long id, ChangePasswordRequestDto passwordDto) {
        if (passwordDto.getOldPassword().equals(passwordDto.getNewPassword())) {
            throw new MemberException(MemberError.MEMBER_SAME_PASSWORD);
        }

        Member foundMember = findByIdOrElseThrow(id);

        if (!passwordEncoder.matches(passwordDto.getOldPassword(), foundMember.getPassword())) {
            throw new MemberException(MemberError.MEMBER_INCORRECT_PASSWORD);
        }

        foundMember.updatePassword(passwordEncoder.encode(passwordDto.getNewPassword()));
    }

    @Transactional
    public void delete(Long id, MemberDeleteRequestDto deleteDto) {
        Member foundMember = findByIdOrElseThrow(id);

        if (!passwordEncoder.matches(deleteDto.getPassword(), foundMember.getPassword())) {
            throw new MemberException(MemberError.MEMBER_INCORRECT_PASSWORD);
        }

        foundMember.delete();
    }

    public Member findByLoginEmailOrElseThrow(String email) {
        Member foundMember = memberRepository.findByEmail(email).orElseThrow(() -> new MemberException(MemberError.MEMBER_LOGIN_FAILED));
        if (foundMember.isDeleted()) {
            throw new MemberException(MemberError.MEMBER_DELETED);
        }

        return foundMember;
    }

    private void updateAddressIfValid(Member foundMember, String address) {
        if (address != null && !address.trim().isEmpty()) {
            foundMember.updateAddress(address);
        }
    }

    private void updatePhoneNumberIfValid(Member foundMember, String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            if (memberRepository.existsByPhoneNumber(phoneNumber)) {
                throw new MemberException(MemberError.MEMBER_PHONE_NUMBER_EXIST);
            }
            foundMember.updatePhoneNumber(phoneNumber);
        }
    }

    private void updateNicknameIfValid(Member foundMember, String nickname) {
        if (nickname != null && !nickname.trim().isEmpty()) {
            if (memberRepository.existsByNickname(nickname)) {
                throw new MemberException(MemberError.MEMBER_NICKNAME_EXIST);
            }
            foundMember.updateNickname(nickname);
        }
    }

    private void updateProfileImageUrlIfValid(Member foundMember, String profileImageUrl) {
        if (profileImageUrl != null && !profileImageUrl.trim().isEmpty()) {
            foundMember.updateProfileImageUrl(profileImageUrl);
        }
    }

    private void updateEmailIfValid(Member foundMember, String email) {
        if (email != null && !email.trim().isEmpty()) {
            if (memberRepository.existsByEmail(email)) {
                throw new MemberException(MemberError.MEMBER_EMAIL_EXIST);
            }
            foundMember.updateEmail(email);
        }
    }
}
