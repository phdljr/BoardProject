package kr.ac.project.boardproject.controller;

import kr.ac.project.boardproject.dto.request.BoardRequestDto;
import kr.ac.project.boardproject.dto.response.BoardListResponseDto;
import kr.ac.project.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public BoardListResponseDto getBoardList(@RequestParam int page){
        BoardListResponseDto boardListResponseDto = boardService.getBoardList(page);
        return boardListResponseDto;
    }

    @GetMapping("/board/{boardId}")
    public Long getBoard(@PathVariable Long boardId, BoardRequestDto boardRequestDto){
        Long result = boardService.updateBoard(boardId, boardRequestDto);
        return result;
    }
}
