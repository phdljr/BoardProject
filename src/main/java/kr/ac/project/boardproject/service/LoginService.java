package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.LoginRequestDto;
import kr.ac.project.boardproject.dto.response.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
