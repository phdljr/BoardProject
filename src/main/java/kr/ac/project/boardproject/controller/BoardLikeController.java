package kr.ac.project.boardproject.controller;

import kr.ac.project.boardproject.dto.request.BoardLikeRequestDto;
import kr.ac.project.boardproject.dto.response.BoardLikeResponseDto;
import kr.ac.project.boardproject.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    @GetMapping("/board-like/{boardId}/{memberId}")
    public BoardLikeResponseDto getBoardLike(@PathVariable Long boardId, @PathVariable Long memberId){
        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.getBoardLike(boardId, memberId);
        return boardLikeResponseDto;
    }

    @PostMapping("/board-like")
    public Long postBoardLike(@RequestBody BoardLikeRequestDto boardLikeRequestDto){
        Long result = boardLikeService.postBoardLike(boardLikeRequestDto);
        return result;
    }

    @DeleteMapping("/board-like/{boardLikeId}")
    public Long deleteBoardLike(@PathVariable Long boardLikeId){
        Long result = boardLikeService.deleteBoardLike(boardLikeId);
        return result;
    }
}
