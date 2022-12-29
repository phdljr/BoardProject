package kr.ac.project.boardproject.controller;

import kr.ac.project.boardproject.dto.request.LoginRequestDto;
import kr.ac.project.boardproject.dto.response.LoginResponseDto;
import kr.ac.project.boardproject.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        LoginResponseDto loginResponseDto = loginService.login(loginRequestDto);
        return loginResponseDto;
    }
}
