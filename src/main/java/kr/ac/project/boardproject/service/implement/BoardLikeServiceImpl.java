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

    @Override
    public BoardLikeResponseDto getBoardLike(Long boardId, Long memberId) {
        Long countLike = boardLikeRepository.countByBoardId(boardId);
        boolean hasLiked = boardLikeRepository.existsByBoardIdAndMemberId(boardId, memberId);

        BoardLikeResponseDto boardLikeResponseDto = BoardLikeResponseDto.builder()
                .countLike(countLike)
                .hasLiked(hasLiked)
                .build();
        return boardLikeResponseDto;
    }

    @Override
    public Long postBoardLike(BoardLikeRequestDto boardLikeRequestDto) {
        Long boardId = boardLikeRequestDto.getBoardId();
        Long memberId = boardLikeRequestDto.getMemberId();

        boolean hasLiked = boardLikeRepository.existsByBoardIdAndMemberId(boardId, memberId);
        if(hasLiked){
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
        }

        Board board = findBoard(boardId);
        Member member = findMember(memberId);
        BoardLike boardLike = BoardLike.builder()
                .member(member)
                .board(board)
                .build();
        boardLike = boardLikeRepository.save(boardLike);
        return boardLike.getId();
    }

    @Override
    public Long deleteBoardLike(Long boardId) {
        return null;
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
}
