package kr.ac.project.boardproject.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReplyRequestDto {
    private Long memberId;
    private Long boardId;
    private String content;
}
