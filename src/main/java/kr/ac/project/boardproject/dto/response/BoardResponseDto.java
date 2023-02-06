package kr.ac.project.boardproject.dto.response;

import kr.ac.project.boardproject.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private Long hit;
    private Long replyCount;
    private LocalDateTime registerDate;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.nickname = board.getMember().getNickname();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.hit = board.getHit();
        this.registerDate = board.getRegisterDate();
    }

    public BoardResponseDto(Board board, Long replyCount) {
        this.id = board.getId();
        this.nickname = board.getMember().getNickname();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.hit = board.getHit();
        this.replyCount = replyCount;
        this.registerDate = board.getRegisterDate();
    }
}
