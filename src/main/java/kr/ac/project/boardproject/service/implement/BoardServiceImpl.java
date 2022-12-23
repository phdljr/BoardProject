package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.response.BoardListResponseDto;
import kr.ac.project.boardproject.repository.BoardRepository;
import kr.ac.project.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardListResponseDto getBoardList(int page) {
        return null;
    }
}
