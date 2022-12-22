package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.LoginRequestDto;
import kr.ac.project.boardproject.dto.response.LoginResponseDto;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        Optional<Member> findMember = memberRepository.findByEmailAndPassword(email, password);
        if(findMember.isEmpty()){
            throw new IllegalArgumentException();
        }

        Member member = findMember.get();
        LoginResponseDto responseDto = LoginResponseDto.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .memberType(member.getMemberType())
                .build();

        return responseDto;
    }
}
