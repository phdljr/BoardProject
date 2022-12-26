package kr.ac.project.boardproject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BoardResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private Long hit;
    private LocalDateTime registerDate;
}
