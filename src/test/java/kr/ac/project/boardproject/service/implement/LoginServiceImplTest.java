package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.LoginRequestDto;
import kr.ac.project.boardproject.dto.response.LoginResponseDto;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.service.LoginService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LoginServiceImplTest {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("로그인이 잘 되는지 확인한다.")
    @Transactional
    void login() {
        // Given
        Member member = Member.builder()
                .nickname("testNickname")
                .password("testPassword")
                .build();
        member = memberRepository.save(member);

        // When
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .build();
        LoginResponseDto responseDto = loginService.login(loginRequestDto);

        // Then
        assertThat(responseDto.getNickname()).isEqualTo(member.getNickname());
        assertThat(responseDto.getNickname()).isNotEqualTo("testnickname");
    }
}