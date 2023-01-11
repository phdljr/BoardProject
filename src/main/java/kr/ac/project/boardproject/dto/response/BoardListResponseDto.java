package kr.ac.project.boardproject.dto.response;

import kr.ac.project.boardproject.entity.Board;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class BoardListResponseDto {
    private int totalPageNumber;
    private int currentPageNumber;
    private boolean previousPage;
    private boolean nextPage;
    private List<Integer> pageList;
    private List<BoardResponseDto> boardList;

    public BoardListResponseDto(Page<Board> page) {
        makePageList(page);
    }

    private void makePageList(Page<Board> page) {
        int pageSize = page.getSize();

        totalPageNumber = page.getTotalPages();
        currentPageNumber = page.getNumber() + 1;
        boardList = makeBoardListDto(page);

        int tempEnd = (int)Math.ceil(currentPageNumber / (float)pageSize) * pageSize; // 23페이지라면 20페이지로 맞춰주기
        int start = tempEnd - (pageSize - 1);
        int end = Math.min(totalPageNumber, tempEnd);
        previousPage = start > 1;
        nextPage = totalPageNumber > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    private List<BoardResponseDto> makeBoardListDto(Page<Board> page) {
        return boardList = page.getContent().stream().map(board ->
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
