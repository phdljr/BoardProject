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
import org.junit.jupiter.api.DisplayName;
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

    // 맴버, 게시글, 댓글, 게시글 좋아요가 하나씩 있는 상태
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
    @DisplayName("게시판 좋아요를 조회한다.")
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
    @DisplayName("로그인을 안한 상태에서 게시판 좋아요를 조회한다.")
    @Transactional
    void getBoardLikeNoLogin() {
        // given

        // when
        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.getBoardLike(board.getId(), -1L);

        // then
        assertThat(boardLikeResponseDto.getCountLike()).isEqualTo(1);
        assertThat(boardLikeResponseDto.getHasLiked()).isEqualTo(false);
    }

    @Test
    @DisplayName("게시판 좋아요를 생성한다.")
    @Transactional
    void postBoardLike() {
        // given
        boardLikeRepository.delete(boardLike);
        BoardLikeRequestDto boardLikeRequestDto = BoardLikeRequestDto.builder()
                .memberId(member.getId())
                .boardId(board.getId())
                .build();

        // when
        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.postBoardLike(boardLikeRequestDto);

        // then
        assertThat(boardLikeResponseDto.getHasLiked()).isEqualTo(true);
        assertThat(boardLikeResponseDto.getCountLike()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시판 좋아요를 삭제한다.")
    @Transactional
    void deleteBoardLike() {
        // given

        // when
        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.deleteBoardLike(board.getId(), member.getId());

        // then
        assertThat(boardLikeResponseDto.getHasLiked()).isEqualTo(false);
        assertThat(boardLikeResponseDto.getCountLike()).isEqualTo(0);
    }
}