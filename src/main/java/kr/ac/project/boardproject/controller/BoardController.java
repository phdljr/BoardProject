package kr.ac.project.boardproject.controller;

import kr.ac.project.boardproject.dto.request.BoardRequestDto;
import kr.ac.project.boardproject.dto.response.BoardListResponseDto;
import kr.ac.project.boardproject.dto.response.BoardResponseDto;
import kr.ac.project.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public BoardListResponseDto getBoardList(@RequestParam int page) {
        BoardListResponseDto boardListResponseDto = boardService.getBoardList(page);
        return boardListResponseDto;
    }

    @GetMapping("/board/{boardId}")
    public BoardResponseDto getBoard(@PathVariable Long boardId) {
        BoardResponseDto boardResponseDto = boardService.getBoard(boardId);
        return boardResponseDto;
    }

    @PostMapping("/board")
    public Long postBoard(@RequestBody BoardRequestDto boardRequestDto) {
        Long result = boardService.postBoard(boardRequestDto);
        return result;
    }

    @PutMapping("/board/{boardId}")
    public Long updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto) {
        Long result = boardService.updateBoard(boardId, boardRequestDto);
        return result;
    }

    @DeleteMapping("/board/{boardId}")
    public Long deleteBoard(@PathVariable Long boardId) {
        Long result = boardService.deleteBoard(boardId);
        return result;
    }
}
