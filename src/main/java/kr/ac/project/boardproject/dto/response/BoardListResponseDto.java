package kr.ac.project.boardproject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BoardListResponseDto {
    private int totalPageNumber;
    private int currentPageNumber;
    private boolean previousPage;
    private boolean nextPage;
    private List<Integer> pageList;
    private List<BoardResponseDto> boardList;

}
