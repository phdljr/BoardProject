package kr.ac.project.boardproject.controller;

import kr.ac.project.boardproject.dto.response.BoardListResponseDto;
import kr.ac.project.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public BoardListResponseDto boardList(@RequestParam int page){
        BoardListResponseDto boardListResponseDto = boardService.getBoardList(page);
        return boardListResponseDto;
    }
}
