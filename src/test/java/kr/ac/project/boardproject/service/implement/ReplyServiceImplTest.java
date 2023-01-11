package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.ReplyRequestDto;
import kr.ac.project.boardproject.dto.response.ReplyResponseDto;
import kr.ac.project.boardproject.entity.Board;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.entity.MemberType;
import kr.ac.project.boardproject.entity.Reply;
import kr.ac.project.boardproject.repository.BoardRepository;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.repository.ReplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReplyServiceImplTest {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;

    private Board dummyBoard;
    private Member dummyMember;

    @BeforeEach
    public void setup() {
        dummyMember = Member.builder()
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .memberType(MemberType.USER)
                .build();
        dummyMember = memberRepository.save(dummyMember);

        dummyBoard = Board.builder()
                .member(dummyMember)
                .content("test")
                .build();
        dummyBoard = boardRepository.save(dummyBoard);
    }

    @Test
    @Transactional
    void read() {
        Reply testReply1 = Reply.builder()
                .board(dummyBoard)
                .member(dummyMember)
                .content("test1")
                .build();

        replyRepository.save(testReply1);

        Reply testReply2 = Reply.builder()
                .board(dummyBoard)
                .member(dummyMember)
                .content("test2")
                .build();

        replyRepository.save(testReply2);

        //when
        List<ReplyResponseDto> replyList = replyService.read(dummyBoard.getId());
        ReplyResponseDto replyResponseDto = replyList.get(0);

        //then
        assertThat(replyList.size()).isEqualTo(2);
        assertThat(replyResponseDto.getReplyId()).isEqualTo(testReply1.getId());
        assertThat(replyResponseDto.getContent()).isEqualTo(testReply1.getContent());
        assertThat(replyResponseDto.getNickname()).isEqualTo(testReply1.getMember().getNickname());
    }

    @Test
    @Transactional
    void create() {
        //given
        ReplyRequestDto replyRequestDto = ReplyRequestDto.builder()
                .memberId(dummyMember.getId())
                .boardId(dummyBoard.getId())
                .content("test")
                .build();

        //when
        Long replyId = replyService.create(replyRequestDto);

        //then
        assertThat(replyRepository.findById(replyId)).isNotEmpty();
    }

    @Test
    @Transactional
    void update() {
        //given
        Reply testReply = Reply.builder()
                .board(dummyBoard)
                .member(dummyMember)
                .content("test")
                .build();

        replyRepository.save(testReply);


        //when
        String content = "testfortest";
        ReplyRequestDto replyRequestDto2 = ReplyRequestDto.builder()
                .memberId(dummyMember.getId())
                .boardId(dummyBoard.getId())
                .content(content)
                .build();
        replyService.update(testReply.getId(), replyRequestDto2);

        //then
        Optional<Reply> findReply = replyRepository.findById(testReply.getId());
        if(findReply.isEmpty()){
            throw new IllegalArgumentException();
        }
        Reply reply = findReply.get();
        assertThat(content).isEqualTo(reply.getContent());

    }

    @Test
    @Transactional
    void delete() {
        //given
        Long memberId = dummyMember.getId();
        Long boardId = dummyBoard.getId();
        String content = "test";

        ReplyRequestDto replyRequestDto = ReplyRequestDto.builder()
                .memberId(memberId)
                .boardId(boardId)
                .content(content)
                .build();
        Long replyId = replyService.create(replyRequestDto);

        //when
        replyService.delete(replyId);

        //then
        assertThat(replyRepository.findById(replyId)).isEmpty();
    }
}
