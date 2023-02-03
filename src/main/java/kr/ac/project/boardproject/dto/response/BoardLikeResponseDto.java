package kr.ac.project.boardproject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardLikeResponseDto {
    private Long countLike;
    private Boolean hasLiked;
}
