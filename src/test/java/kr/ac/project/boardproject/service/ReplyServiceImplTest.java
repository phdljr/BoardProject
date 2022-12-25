package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.RegisterRequestDto;
import kr.ac.project.boardproject.dto.request.ReplyRequestDto;
import kr.ac.project.boardproject.dto.response.ReplyResponseDto;
import kr.ac.project.boardproject.entity.Reply;
import kr.ac.project.boardproject.repository.ReplyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ReplyServiceImplTest {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @Transactional
    void read() {
        //given
        Long memberId = 1L;
        Long boardId = 1L;
        String content = "test";

        ReplyRequestDto replyRequestDto = ReplyRequestDto.builder()
                .memberId(memberId)
                .boardId(boardId)
                .content(content)
                .build();
        replyService.create(replyRequestDto);
        content = "test2";
        ReplyRequestDto replyRequestDto2 = ReplyRequestDto.builder()
                .memberId(memberId)
                .boardId(boardId)
                .content(content)
                .build();
        replyService.create(replyRequestDto2);

        //when
        List<ReplyResponseDto> replyList = replyService.read(1L);

        //then
        Assertions.assertThat(replyList.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void create() {
        //given
        Long memberId = 1L;
        Long boardId = 1L;
        String content = "test";

        ReplyRequestDto replyRequestDto = ReplyRequestDto.builder()
                .memberId(memberId)
                .boardId(boardId)
                .content(content)
                .build();

        //when
        replyService.create(replyRequestDto);

        //then
        Assertions.assertThat(replyRepository.findById(1L)).isNotEmpty();
    }

    @Test
    @Transactional
    void update() {
        //given
        Long memberId = 1L;
        Long boardId = 1L;
        String content = "test";

        ReplyRequestDto replyRequestDto = ReplyRequestDto.builder()
                .memberId(memberId)
                .boardId(boardId)
                .content(content)
                .build();
        replyService.create(replyRequestDto);

        //when
        content = "testfortest";
        ReplyRequestDto replyRequestDto2 = ReplyRequestDto.builder()
                .memberId(memberId)
                .boardId(boardId)
                .content(content)
                .build();
        replyService.update(1L, replyRequestDto2);

        //then
        Optional<Reply> findReply = replyRepository.findById(1L);
        Reply reply = findReply.get();
        Assertions.assertThat("testfortest").isEqualTo(reply.getContent());

    }

    @Test
    @Transactional
    void delete() {
        //given
        Long memberId = 1L;
        Long boardId = 1L;
        String content = "test";

        ReplyRequestDto replyRequestDto = ReplyRequestDto.builder()
                .memberId(memberId)
                .boardId(boardId)
                .content(content)
                .build();
        replyService.create(replyRequestDto);

        //when
        replyService.delete(1L);

        //then
        Assertions.assertThat(replyRepository.findById(1L)).isEmpty();
    }
}
