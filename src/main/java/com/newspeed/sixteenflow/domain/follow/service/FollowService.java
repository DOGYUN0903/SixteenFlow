package com.newspeed.sixteenflow.domain.follow.service;

import com.newspeed.sixteenflow.domain.follow.dto.FollowCountDto;
import com.newspeed.sixteenflow.domain.follow.dto.FollowMemberInfoDto;
import com.newspeed.sixteenflow.domain.follow.dto.FollowResponseDto;
import com.newspeed.sixteenflow.domain.follow.entity.Follow;
import com.newspeed.sixteenflow.domain.follow.repository.FollowRepository;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import com.newspeed.sixteenflow.global.exception.follow.AlreadyFollowException;
import com.newspeed.sixteenflow.global.exception.follow.CannotFollowSelfException;
import com.newspeed.sixteenflow.global.exception.follow.CannotUnFollowSelfException;
import com.newspeed.sixteenflow.global.exception.follow.FollowNotFoundException;
import com.newspeed.sixteenflow.global.exception.member.MemberException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberService memberService;

    /**
     * 특정id 멤버의 팔로잉 목록 memberId를 가져오는 메소드
     *
     * @param memberId 조회를 원하는멤버의 id
     * @return 조회된 팔로잉 목록 {@link List<Long> ids}
     */
    public List<Long> getFollowingsIds(Long memberId) {
        List<Long> followingsId = followRepository.findFollowingsIdsByMemberId(memberId);
        return followingsId;
    }

    /**
     * 특정id 멤버의 팔로워 목록 memberId를 가져오는 메소드
     *
     * @param memberId 조회를 원하는멤버의 id
     * @return 조회된 팔로워 목록 {@link List<Long> ids}
     */
    public List<Long> getFollowersIds(Long memberId) {
        List<Long> followersId = followRepository.findFollowersIdsByMemberId(memberId);
        return followersId;
    }

    /**
     * 특정id 멤버의 팔로잉, 팔로워 수를 가져오는 메소드
     *
     * @param memberId 조회를 원하는멤버의 id
     * @return 조회된 팔로잉, 팔로워 수를 담은 {@link FollowCountDto}
     */
    public FollowCountDto getFollowerFollowingCount(Long memberId) {
        return followRepository.countFollowCountsByMemberId(memberId);
    }

    /**
     * 특정 member가 다른 member를 팔로우하는 메소드
     *
     * @param followerId  팔로우를 요청한 member의 id
     * @param followingId 팔로우 당할 member의 id
     * @return 생성된 follow 객체의 응답 dto {@link FollowResponseDto}
     * @throws AlreadyFollowException 이미 팔로우 관계가 존재할 경우
     * @throws MemberException memberId가 존재하지 않을 경우 발생(memberService 내부 예외)
     */
    public FollowResponseDto follow(Long followerId, Long followingId) {
        //자기 자신 팔로우 불가
        if(followerId.equals(followingId))
            throw new CannotFollowSelfException();

        //팔로워, 팔로잉 대상 존재 확인
        Member follower = memberService.findByIdOrElseThrow(followerId);
        Member following = memberService.findByIdOrElseThrow(followingId);

        //이미 팔로우 중인지 확인
        if (followRepository.findByFollowerAndFollowing(follower, following).isPresent())
            throw new AlreadyFollowException();

        //팔로우 정보 저장및 반환
        Follow result = followRepository.save(new Follow(follower,following));
        return FollowResponseDto.toDto(result);
    }

    /**
     * 특정 member가 다른 member를 언팔로우하는 메소드
     *
     * @param followerId  언팔로우를 요청한 member의 id
     * @param followingId 언팔로우 당할 member의 id
     * @return 제거한 follow 객체의 응답 dto {@link FollowResponseDto}
     * @throws FollowNotFoundException 이미 팔로우 관계가 존재할 경우
     * @throws MemberException memberId가 존재하지 않을 경우 발생(memberService 내부 예외)
     */
    public FollowResponseDto unfollow(Long followerId, Long followingId) {
        //자기 자신 언팔로우 불가
        if(followerId.equals(followingId))
            throw new CannotUnFollowSelfException();

        //언팔로워, 언팔로잉 대상 존재 확인
        Member follower = memberService.findByIdOrElseThrow(followerId);
        Member following = memberService.findByIdOrElseThrow(followingId);

        //이미 팔로우 중인지 확인
        Follow follow = followRepository.findByFollowerAndFollowing(follower, following)
                .orElseThrow(() -> new FollowNotFoundException());

        //언팔로우
        followRepository.delete(follow);
        return FollowResponseDto.toDto(follow);
    }

    /**
     * 특정 member가 팔로잉한 member목록을 페이징 조회하는 메소드
     *
     * @param memberId  팔로우 목록을 조회할 member의 id
     * @param pageable 페이징 정보를 담은 객체(기본 page size 10)
     * @return 팔로잉 중인 회원들의 정보를 담은 Page 객체 {@link FollowMemberInfoDto}
     * @throws MemberException memberId가 존재하지 않을 경우 발생(memberService 내부 예외)
     */
    public Page<FollowMemberInfoDto> getFollowings(Long memberId, Pageable pageable) {
        memberService.findByIdOrElseThrow(memberId);
        return followRepository.findFollowingByMemberId(memberId, pageable);
    }
}
