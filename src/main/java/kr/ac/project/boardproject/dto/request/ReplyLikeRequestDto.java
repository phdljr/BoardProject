package kr.ac.project.boardproject.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyLikeRequestDto {
    private Long memberId;
    private Long replyId;
}
