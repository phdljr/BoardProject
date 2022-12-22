package kr.ac.project.boardproject.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequestDto {
    private String email;
    private String password;
    private String nickname;

}
