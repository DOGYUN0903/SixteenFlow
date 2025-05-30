package com.newspeed.sixteenflow.domain.follow.service;

import com.newspeed.sixteenflow.domain.follow.dto.FollowCountDto;
import com.newspeed.sixteenflow.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

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
}
