package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.BoardLikeRequestDto;
import kr.ac.project.boardproject.dto.response.BoardLikeResponseDto;
import kr.ac.project.boardproject.entity.Board;
import kr.ac.project.boardproject.entity.BoardLike;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.repository.BoardLikeRepository;
import kr.ac.project.boardproject.repository.BoardRepository;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.service.BoardLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService {

    private final BoardLikeRepository boardLikeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    private final Long MEMBER_NONE_EXIST = -1L;

    @Override
    public BoardLikeResponseDto getBoardLike(Long boardId, Long memberId) {
        BoardLikeResponseDto boardLikeResponseDto = createBoardLikeResponseDto(boardId, memberId);
        return boardLikeResponseDto;
    }

    @Override
    public BoardLikeResponseDto postBoardLike(BoardLikeRequestDto boardLikeRequestDto) {
        Long boardId = boardLikeRequestDto.getBoardId();
        Long memberId = boardLikeRequestDto.getMemberId();

        boolean hasLiked = boardLikeRepository.existsByBoardIdAndMemberId(boardId, memberId);
        validateLike(hasLiked);

        Board board = findBoard(boardId);
        Member member = findMember(memberId);
        BoardLike boardLike = BoardLike.builder()
                .member(member)
                .board(board)
                .build();
        boardLikeRepository.save(boardLike);

        BoardLikeResponseDto boardLikeResponseDto = createBoardLikeResponseDto(boardId, memberId);
        return boardLikeResponseDto;
    }

    @Override
    public BoardLikeResponseDto deleteBoardLike(Long boardId, Long memberId) {
        Optional<BoardLike> findBoardLike = boardLikeRepository.findByBoardIdAndMemberId(boardId, memberId);
        BoardLike boardLike = validate(findBoardLike);

        boardLikeRepository.delete(boardLike);

        BoardLikeResponseDto boardLikeResponseDto = createBoardLikeResponseDto(boardId, memberId);
        return boardLikeResponseDto;
    }

    private BoardLikeResponseDto createBoardLikeResponseDto(Long boardId, Long memberId) {
        Long countLike = boardLikeRepository.countByBoardId(boardId);
        boolean hasLiked = false;
        if(memberId != MEMBER_NONE_EXIST){
            hasLiked = boardLikeRepository.existsByBoardIdAndMemberId(boardId, memberId);
        }

        BoardLikeResponseDto boardLikeResponseDto = BoardLikeResponseDto.builder()
                .countLike(countLike)
                .hasLiked(hasLiked)
                .build();
        return boardLikeResponseDto;
    }

    private Board findBoard(Long boardId) {
        Optional<Board> findBoard = boardRepository.findById(boardId);
        Board board = validate(findBoard);
        return board;
    }

    private Member findMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = validate(findMember);
        return member;
    }

    private <T> T validate(Optional<T> findEntity) {
        if (findEntity.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 데이터입니다.");
        }
        return findEntity.get();
    }

    private void validateLike(boolean hasLiked) {
        if (hasLiked) {
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
        }
    }
}
