package kr.ac.project.boardproject.dto.response;

import kr.ac.project.boardproject.entity.Board;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class BoardListResponseDto {
    private int totalPageNumber;
    private int currentPageNumber;
    private boolean previousPage;
    private boolean nextPage;
    private List<Integer> pageList;
    private List<BoardResponseDto> boardList;

    public BoardListResponseDto(int totalPageNumber, int currentPageNumber, int pageSize, List<Board> boardList) {
        this.totalPageNumber = totalPageNumber;
        this.currentPageNumber = currentPageNumber + 1;
        this.boardList =  makeBoardListDto(boardList);

        int tempEnd = (int)Math.ceil(currentPageNumber / (float)pageSize) * pageSize; // 23페이지라면 20페이지로 맞춰주기
        int start = tempEnd - (pageSize - 1);
        int end = Math.min(totalPageNumber, tempEnd);
        previousPage = start > 1;
        nextPage = totalPageNumber > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    private List<BoardResponseDto> makeBoardListDto(List<Board> boardList) {
        return boardList.stream()
                .map(board -> new BoardResponseDto(board))
                .collect(Collectors.toList());
    }
}
