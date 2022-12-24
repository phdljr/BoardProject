package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.RegisterRequestDto;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class RegisterServiceImplTest {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입이 잘 되는지 확인한다")
    @Transactional
    void register(){
        //given - 딱히 없어도 될듯. 아니면 이메일 중복 체크할 때 사용될수도?


        //when - email이 test@email.com이고 pw가 test1234인 dto를 받아서 회원가입한다.
        String email = "test@email.com";
        String password = "1234";
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .email(email)
                .password(password)
                .build();
        registerService.register(registerRequestDto);

        //then - MemberRepository에서 주어진 값으로 Member를 찾아서 값을 비교한다.
        Optional<Member> findMember = memberRepository.findByEmail(email);
        assertThat(findMember).isNotEmpty();
        assertThat(findMember.get().getEmail()).isEqualTo(email);
    }
    @Test
    @DisplayName("중복 회원가입이 안되는지 확인한다")
    @Transactional
    void 중복register(){
        String email = "test@email.com";
        String password = "1234";
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .email(email)
                .password(password)
                .build();
        registerService.register(registerRequestDto);
        registerService.register(registerRequestDto);
    }


}