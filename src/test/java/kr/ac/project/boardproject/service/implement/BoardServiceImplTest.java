package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.BoardRequestDto;
import kr.ac.project.boardproject.dto.response.BoardListResponseDto;
import kr.ac.project.boardproject.dto.response.BoardResponseDto;
import kr.ac.project.boardproject.entity.Board;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.entity.MemberType;
import kr.ac.project.boardproject.entity.Reply;
import kr.ac.project.boardproject.repository.BoardRepository;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.repository.ReplyRepository;
import kr.ac.project.boardproject.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;

    private Member member;
    private Board board;

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
    }

    @Test
    @DisplayName("?????? 1??? ?????? ???????????? 1??? ??????????????? ????????????.")
    @Transactional
    void updateHit(){
        // given
        Long boardId = board.getId();
        BoardResponseDto boardResponseDto = boardService.getBoard(boardId);

        assertThat(boardResponseDto.getHit()).isEqualTo(1L);
    }

    @Test
    @DisplayName("1???????????? ????????? ???????????? ????????????.")
    @Transactional
    void getBoardList() {
        // given
        insertDummyDate(15);

        // when
        BoardListResponseDto responseDto = boardService.getBoardList(1);

        // then
        assertThat(responseDto.getTotalPageNumber()).isEqualTo(2);
        assertThat(responseDto.getBoardList().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("???????????? ?????? ????????????.")
    @Transactional
    void getBoard() {
        // given
        Long boardId = board.getId();

        // when
        BoardResponseDto boardResponseDto = boardService.getBoard(boardId);

        // then
        assertThat(boardResponseDto.getId()).isEqualTo(boardId);
        assertThat(boardResponseDto.getNickname()).isEqualTo(member.getNickname());
    }

    @Test
    @DisplayName("???????????? ????????? ??? ??????????????? ??????????????? ????????????.")
    @Transactional
    void getBoardWithReplyCount() {
        //given
        insertDummyReply(15);
        Long boardId = board.getId();

        //when
        BoardResponseDto boardResponseDto = boardService.getBoard(boardId);

        //then
        assertThat(boardResponseDto.getReplyCount()).isEqualTo(15);
    }

    @Test
    @DisplayName("???????????? ????????????")
    @Transactional
    void postBoard() {
        // given
        String title = "postTitle";
        String content = "postContent";
        BoardRequestDto boardRequestDto = BoardRequestDto.builder()
                .memberId(member.getId())
                .title(title)
                .content(content)
                .build();

        // when
        Long result = boardService.postBoard(boardRequestDto);
        Optional<Board> findBoard = boardRepository.findById(result);
        if (findBoard.isEmpty()) {
            fail("????????? ?????? ??????????????????.");
        }
        Board board = findBoard.get();

        // then
        assertThat(board.getId()).isEqualTo(result);
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);
        assertThat(board.getMember()).isEqualTo(member);
    }

    @Test
    @DisplayName("???????????? ????????? ????????????.")
    @Transactional
    void updateBoard() {
        // given
        Long boardId = board.getId();
        Long memberId = member.getId();
        String updateTitle = "updateTitle";
        String updateContent = "updateContent";
        BoardRequestDto boardRequestDto = BoardRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .memberId(memberId)
                .build();

        // when
        Long response = boardService.updateBoard(boardId, boardRequestDto);

        Optional<Board> findBoard = boardRepository.findById(boardId);
        if (findBoard.isEmpty()) {
            fail("????????? ?????? ??????");
        }
        Board getBoard = findBoard.get();

        // then
        assertThat(getBoard.getId()).isEqualTo(boardId);
        assertThat(getBoard.getTitle()).isEqualTo(updateTitle);
        assertThat(getBoard.getContent()).isEqualTo(updateContent);
    }

    @Test
    @DisplayName("???????????? ??????????????? ????????????.")
    @Transactional
    void deleteBoard() {
        // given
        Long boardId = board.getId();

        // when
        Long result = boardService.deleteBoard(boardId);
        Optional<Board> findBoard = boardRepository.findById(result);

        // then
        assertThat(findBoard).isEmpty();
    }

    private void insertDummyDate(int count) {
        LongStream.rangeClosed(1, count).forEach(num -> {
            Member member = Member.builder()
                    .email("test@test.test" + num)
                    .memberType(MemberType.USER)
                    .nickname("testNickname" + num)
                    .password("testPassword" + num)
                    .build();
            member = memberRepository.save(member);
            Board board = Board.builder()
                    .member(member)
                    .title("testTitle" + num)
                    .content("testContent" + num)
                    .build();
            board = boardRepository.save(board);
        });
    }

    private void insertDummyReply(int count) {
        LongStream.rangeClosed(1, count).forEach(num -> {
            Reply reply = Reply.builder()
                    .board(board)
                    .content("test" + num)
                    .member(member)
                    .build();
            replyRepository.save(reply);
        });
    }
}