package kr.ac.project.boardproject.controller;

import kr.ac.project.boardproject.dto.request.RegisterRequestDto;
import kr.ac.project.boardproject.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDto registerRequestDto) {
        registerService.register(registerRequestDto);
        return "OK";
    }
}
