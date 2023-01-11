package kr.ac.project.boardproject.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    private Long memberId;
    private String title;
    private String content;
}
