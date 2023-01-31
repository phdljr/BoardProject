package kr.ac.project.boardproject.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    private Long memberId;
    private String title;
    private String content;
}
