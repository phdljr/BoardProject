package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.RegisterRequestDto;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.entity.MemberType;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final MemberRepository memberRepository;

    @Override
    public void register(RegisterRequestDto registerRequestDto){
        Member member = Member.builder()
                .email(registerRequestDto.getEmail())
                .password(registerRequestDto.getPassword())
                .nickname(registerRequestDto.getNickname())
                .memberType(MemberType.USER)
                .build();
        memberRepository.save(member);
    }
}
