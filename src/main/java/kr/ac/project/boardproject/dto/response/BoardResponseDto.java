package kr.ac.project.boardproject.dto.response;

import kr.ac.project.boardproject.entity.Board;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<BoardResponseDto> makeBoardListDto(Page<Board> page) {
        return page.getContent().stream().map(board ->
                BoardResponseDto.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .nickname(board.getMember().getNickname())
                        .registerDate(board.getRegisterDate())
                        .hit(board.getHit())
                        .build()
        ).collect(Collectors.toList());
    }
}
