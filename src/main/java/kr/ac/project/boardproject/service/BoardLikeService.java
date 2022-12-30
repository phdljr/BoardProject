package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.BoardLikeRequestDto;
import kr.ac.project.boardproject.dto.response.BoardLikeResponseDto;

public interface BoardLikeService {
    BoardLikeResponseDto getBoardLike(Long boardId, Long memberId);
    Long postBoardLike(BoardLikeRequestDto boardLikeRequestDto);
    Long deleteBoardLike(Long boardId);
}
