package com.newspeed.sixteenflow.domain.member.repository;

import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.global.exception.member.MemberException;
import com.newspeed.sixteenflow.global.response.error.MemberError;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsMemberByEmail(String email);

    default Member findMemberByIdOrElseThrow(Long memberId) {
        return findById(memberId).orElseThrow(() -> new MemberException(MemberError.MEMBER_NOT_FOUND));
    }
}
