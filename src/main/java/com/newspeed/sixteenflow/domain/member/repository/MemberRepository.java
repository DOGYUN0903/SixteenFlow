package com.newspeed.sixteenflow.domain.member.repository;

import com.newspeed.sixteenflow.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsMemberByEmail(String email);
}
