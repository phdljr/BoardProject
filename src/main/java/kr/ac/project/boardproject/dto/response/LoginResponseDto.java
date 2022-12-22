package kr.ac.project.boardproject.dto.response;

import kr.ac.project.boardproject.entity.MemberType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDto {
    private Long memberId;
    private String nickname;
    private MemberType memberType;
}
