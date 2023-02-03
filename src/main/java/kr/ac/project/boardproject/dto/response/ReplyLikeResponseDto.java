package kr.ac.project.boardproject.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyLikeResponseDto {
    private Long replyId;
    private Long countLike;
    private boolean hasLiked;
}
