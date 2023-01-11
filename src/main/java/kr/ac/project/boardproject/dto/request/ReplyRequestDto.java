package kr.ac.project.boardproject.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyRequestDto {
    private Long memberId;
    private Long boardId;
    private String content;
}
