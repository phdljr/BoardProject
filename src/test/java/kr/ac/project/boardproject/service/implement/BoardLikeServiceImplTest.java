package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.BoardLikeRequestDto;
import kr.ac.project.boardproject.dto.response.BoardLikeResponseDto;
import kr.ac.project.boardproject.entity.Board;
import kr.ac.project.boardproject.entity.BoardLike;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.entity.MemberType;
import kr.ac.project.boardproject.repository.BoardLikeRepository;
import kr.ac.project.boardproject.repository.BoardRepository;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.service.BoardLikeService;
import kr.ac.project.boardproject.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardLikeServiceImplTest {

    @Autowired
    private BoardLikeService boardLikeService;

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardLikeRepository boardLikeRepository;

    private Member member;
    private Board board;
    private BoardLike boardLike;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
                .email("test@test.test")
                .memberType(MemberType.USER)
                .nickname("testNickname")
                .password("testPassword")
                .build();
        member = memberRepository.save(member);
        board = Board.builder()
                .member(member)
                .title("testTitle")
                .content("testContent")
                .build();
        board = boardRepository.save(board);
        boardLike = BoardLike.builder()
                .board(board)
                .member(member)
                .build();
        boardLike = boardLikeRepository.save(boardLike);
    }

    @Test
    @Transactional
    void getBoardLike() {
        // given

        // when
        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.getBoardLike(board.getId(), member.getId());

        // then
        assertThat(boardLikeResponseDto.getCountLike()).isEqualTo(1);
        assertThat(boardLikeResponseDto.getHasLiked()).isEqualTo(true);
    }

    @Test
    @Transactional
    void postBoardLike() {
        // given
        boardLikeRepository.delete(boardLike);
        BoardLikeRequestDto boardLikeRequestDto = BoardLikeRequestDto.builder()
                .memberId(member.getId())
                .boardId(board.getId())
                .build();

        // when
        Long boardLikeId = boardLikeService.postBoardLike(boardLikeRequestDto);
        Optional<BoardLike> findBoardLike = boardLikeRepository.findById(boardLikeId);
        if(findBoardLike.isEmpty()){
            fail("데이터를 찾지 못하였습니다.");
        }
        BoardLike boardLike = findBoardLike.get();

        // then
        assertThat(boardLike.getBoard()).isEqualTo(board);
        assertThat(boardLike.getMember()).isEqualTo(member);
    }

    @Test
    void deleteBoardLike() {
    }
}