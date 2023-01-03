package kr.ac.project.boardproject.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ReplyLikeRequestDto {
    private Long memberId;
    private Long replyId;
}
