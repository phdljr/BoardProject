package kr.ac.project.boardproject.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardLikeResponseDto {
    private Long countLike;
    private Boolean hasLiked;
}
