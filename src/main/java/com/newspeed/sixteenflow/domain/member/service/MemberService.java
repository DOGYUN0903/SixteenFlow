package com.newspeed.sixteenflow.domain.member.service;

import com.newspeed.sixteenflow.domain.member.dto.MemberRequestDto;
import com.newspeed.sixteenflow.domain.member.dto.MemberResponseDto;
import com.newspeed.sixteenflow.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

//    public MemberResponseDto createMember(MemberRequestDto requestDto) {
//
//    }
}
