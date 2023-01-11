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
    public void register(RegisterRequestDto registerRequestDto) {
        checkEmailDuplicate(registerRequestDto.getEmail());
        checkNicknameDuplicate(registerRequestDto.getNickname());
        Member member = Member.builder()
                .email(registerRequestDto.getEmail())
                .password(registerRequestDto.getPassword())
                .nickname(registerRequestDto.getNickname())
                .memberType(MemberType.USER)
                .build();
        memberRepository.save(member);
    }

    @Override
    public String checkEmailDuplicate(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isPresent())
            throw new IllegalArgumentException("중복된 이메일입니다.");
        else
            return "OK";
    }

    @Override
    public String checkNicknameDuplicate(String nickname) {
        Optional<Member> optionalMember = memberRepository.findByNickname(nickname);
        if (optionalMember.isPresent())
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        else
            return "OK";
    }
}
