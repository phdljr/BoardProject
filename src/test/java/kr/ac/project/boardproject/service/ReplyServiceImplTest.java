package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.RegisterRequestDto;
import kr.ac.project.boardproject.dto.request.ReplyRequestDto;
import kr.ac.project.boardproject.entity.Reply;
import kr.ac.project.boardproject.repository.ReplyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
public class ReplyServiceImplTest {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private ReplyRepository replyRepository;

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
}
