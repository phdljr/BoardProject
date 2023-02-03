package kr.ac.project.boardproject.dto.response;

import kr.ac.project.boardproject.entity.MemberType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private Long memberId;
    private String nickname;
    private MemberType memberType;
}
