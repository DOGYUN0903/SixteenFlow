package com.newspeed.sixteenflow.domain.follow.service;

import com.newspeed.sixteenflow.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public List<Long> getFollowingsIds(Long memberId) {
        List<Long> followingsId = followRepository.findFollowingsIdsByMemberId(memberId);
        return followingsId;
    }

    public List<Long> getFollowersIds(Long memberId) {
        List<Long> followersId = followRepository.findFollowersIdsByMemberId(memberId);
        return followersId;
    }
}
