package kr.ac.project.boardproject.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private Long hit;
    private Long replyCount;
    private LocalDateTime registerDate;
}
