package com.newspeed.sixteenflow.domain.follow.repository;

import com.newspeed.sixteenflow.domain.follow.dto.FollowCountDto;
import com.newspeed.sixteenflow.domain.follow.entity.Follow;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import jakarta.persistence.criteria.From;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT f.following.id FROM Follow f WHERE f.follower.id = :memberId")
    List<Long> findFollowingsIdsByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT f.follower.id FROM Follow f WHERE f.following.id = :memberId")
    List<Long> findFollowersIdsByMemberId(@Param("memberId") Long memberId);

    @Query("""
            SELECT new com.newspeed.sixteenflow.domain.follow.dto.FollowCountDto(
            :memberId,
            (SELECT COUNT(f) FROM Follow f WHERE f.follower.id = :memberId),
            (SELECT COUNT(f) FROM Follow f WHERE f.follower.id = :memberId)
            )
            """)
    FollowCountDto countFollowCountsByMemberId(@Param("memberId") Long memberId);
}
