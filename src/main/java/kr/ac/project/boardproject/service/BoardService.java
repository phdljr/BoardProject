package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.response.BoardListResponseDto;

public interface BoardService {
    BoardListResponseDto getBoardList(int page);
}
