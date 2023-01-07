package kr.ac.project.boardproject.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyLikeResponseDto {
    private Long replyId;
    private Long likeCount;
    private boolean hasLiked;
}
