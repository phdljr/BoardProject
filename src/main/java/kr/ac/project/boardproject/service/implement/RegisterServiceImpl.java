package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.RegisterRequestDto;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.entity.MemberType;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final MemberRepository memberRepository;

    @Override
    public void register(RegisterRequestDto registerRequestDto){
        checkEmailDuplicate(registerRequestDto.getEmail());
        Member member = Member.builder()
                .email(registerRequestDto.getEmail())
                .password(registerRequestDto.getPassword())
                .nickname(registerRequestDto.getNickname())
                .memberType(MemberType.USER)
                .build();
        memberRepository.save(member);
    }

    private void checkEmailDuplicate(String email){
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent())
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
    }
}
