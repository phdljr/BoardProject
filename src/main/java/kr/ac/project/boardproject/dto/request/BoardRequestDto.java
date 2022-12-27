package kr.ac.project.boardproject.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardRequestDto {
    private Long memberId;
    private String title;
    private String content;
}
