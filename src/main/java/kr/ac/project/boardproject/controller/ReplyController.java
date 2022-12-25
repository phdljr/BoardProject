package kr.ac.project.boardproject.controller;

import kr.ac.project.boardproject.dto.request.ReplyRequestDto;
import kr.ac.project.boardproject.dto.response.ReplyResponseDto;
import kr.ac.project.boardproject.entity.Reply;
import kr.ac.project.boardproject.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply/{boardId}")
    public List<ReplyResponseDto> readReply(@PathVariable Long boardId) {
        List<ReplyResponseDto> replyResponseDto = replyService.read(boardId);
        return replyResponseDto;
    }

    @PostMapping("/reply")
    public String createReply(ReplyRequestDto replyRequestDto) {
        replyService.create(replyRequestDto);
        return "OK";
    }

    @PutMapping("/reply/{id}")
    public String updateReply(@PathVariable Long id, ReplyRequestDto replyRequestDto) {
        replyService.update(id, replyRequestDto);
        return "OK";
    }

    @DeleteMapping("/reply/{id}")
    public String deleteReply(@PathVariable Long id) {
        replyService.delete(id);
        return "OK";
    }
}
