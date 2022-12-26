package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.BoardRequestDto;
import kr.ac.project.boardproject.dto.response.BoardListResponseDto;
import kr.ac.project.boardproject.dto.response.BoardResponseDto;

public interface BoardService {
    BoardListResponseDto getBoardList(int pageNumber);
    BoardResponseDto getBoard(Long boardId);
    Long updateBoard(Long boardId, BoardRequestDto boardRequestDto);
    Long deleteBoard(Long boardId);
}
