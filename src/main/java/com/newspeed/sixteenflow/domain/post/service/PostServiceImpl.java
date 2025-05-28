package com.newspeed.sixteenflow.domain.post.service;

import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.repository.MemberRepository;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    public CreatePostResponseDto createPost(Long memberId, CreatePostRequestDto requestDto) {
        // FIXME: 현재는 임시로 ResponseStatusException 사용 중.
        // 추후 member 도메인에서 커스텀 예외(MemberNotFoundException 등) 정의되면 교체할 것.
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Post post = new Post(
                requestDto.getContent(),
                requestDto.getImageUrl(),
                findMember
        );

        return new CreatePostResponseDto(postRepository.save(post));
    }
}
