package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.BoardRequestDto;
import kr.ac.project.boardproject.dto.response.BoardListResponseDto;
import kr.ac.project.boardproject.dto.response.BoardResponseDto;
import kr.ac.project.boardproject.entity.Board;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.repository.BoardRepository;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.repository.ReplyRepository;
import kr.ac.project.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

    private final int PAGE_SIZE = 10;

    @Override
    @Transactional
    public BoardListResponseDto getBoardList(int pageNumber) {
        PageRequest pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "registerDate"));
        Page<Board> boardListPage = boardRepository.findAll(pageable);

        BoardListResponseDto responseDto = new BoardListResponseDto(boardListPage);
        return responseDto;
    }

    @Override
    @Transactional
    public BoardResponseDto getBoard(Long boardId) {
        updateHit(boardId);
        Optional<Board> findBoard = boardRepository.findById(boardId);
        Board board = findBoard.get();

        Long replyCount = replyRepository.countByBoardId(boardId);

        BoardResponseDto responseDto = BoardResponseDto.builder()
                .id(board.getId())
                .nickname(board.getMember().getNickname())
                .title(board.getTitle())
                .content(board.getContent())
                .registerDate(board.getRegisterDate())
                .hit(board.getHit())
                .replyCount(replyCount)
                .build();

        return responseDto;
    }

    @Override
    public Long postBoard(BoardRequestDto boardRequestDto) {
        Member member = findMember(boardRequestDto.getMemberId());

        Board board = Board.builder()
                .member(member)
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .build();

        board = boardRepository.save(board);
        return board.getId();
    }

    @Override
    @Transactional
    public Long updateBoard(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = findBoard(boardId);
        board.updateBoard(boardRequestDto.getTitle(), boardRequestDto.getContent());
        return board.getId();
    }

    @Override
    public Long deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
        return boardId;
    }

    private Board findBoard(Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        validate(findBoard);

        Board board = findBoard.get();
        return board;
    }

    private Member findMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        validate(findMember);

        Member member = findMember.get();
        return member;
    }

    private void validate(Optional<?> findEntity) {
        if (findEntity.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 데이터입니다.");
        }
    }

    private void updateHit(Long boardId) {
        if(!boardRepository.existsById(boardId)){
            throw new IllegalArgumentException();
        }
        boardRepository.updatehit(boardId);
    }
}
