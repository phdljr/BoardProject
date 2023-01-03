package kr.ac.project.boardproject.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardLikeRequestDto {
    private Long memberId;
    private Long boardId;
}
