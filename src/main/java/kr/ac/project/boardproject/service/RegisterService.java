package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.RegisterRequestDto;

public interface RegisterService {
    void register(RegisterRequestDto registerRequestDto);

    String checkEmailDuplicate(String email);

    String checkNicknameDuplicate(String nickname);
}
