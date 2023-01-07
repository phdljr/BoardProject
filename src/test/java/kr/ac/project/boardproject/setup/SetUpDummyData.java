package kr.ac.project.boardproject.setup;

import kr.ac.project.boardproject.entity.*;
import kr.ac.project.boardproject.repository.BoardLikeRepository;
import kr.ac.project.boardproject.repository.BoardRepository;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
public class SetUpDummyData {

    @Autowired
    protected MemberRepository memberRepository;
    @Autowired
    protected BoardRepository boardRepository;
    @Autowired
    protected ReplyRepository replyRepository;

    protected Member member;
    protected Board board;
    protected Reply reply;
    @Autowired
    private BoardLikeRepository boardLikeRepository;

    @Test
    public void createDummyData() {
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
        reply = Reply.builder()
                .board(board)
                .member(member)
                .content("testReplyContent")
                .build();
        reply = replyRepository.save(reply);
    }

    @Test
    public void createDummyDataList() {
        LongStream.range(1, 16).forEach(i -> {
            Member member = Member.builder()
                    .email("test@test.test" + i)
                    .memberType(MemberType.USER)
                    .nickname("test" + i)
                    .password("tttt" + i)
                    .build();
            member = memberRepository.save(member);

            Board board = Board.builder()
                    .member(member)
                    .title("test" + i)
                    .content("" + i)
                    .build();
            board = boardRepository.save(board);

            BoardLike boardLike = BoardLike.builder()
                    .member(member)
                    .board(board)
                    .build();
            boardLikeRepository.save(boardLike);

            Board finalBoard = board;
            Member finalMember = member;
            LongStream.range(1, 6).forEach(j -> {
                Reply reply = Reply.builder()
                        .board(finalBoard)
                        .member(finalMember)
                        .content("test" + j)
                        .build();
                replyRepository.save(reply);
            });
        });
    }
}
