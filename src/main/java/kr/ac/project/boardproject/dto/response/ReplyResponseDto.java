package kr.ac.project.boardproject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReplyResponseDto {
    private Long replyId;
    private Long memberId;
    private Long boardId;
    private String content;
    private String nickname;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
}
