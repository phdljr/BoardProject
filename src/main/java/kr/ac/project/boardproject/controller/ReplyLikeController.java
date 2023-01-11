package kr.ac.project.boardproject.controller;


import kr.ac.project.boardproject.dto.request.ReplyLikeRequestDto;
import kr.ac.project.boardproject.dto.response.ReplyLikeResponseDto;
import kr.ac.project.boardproject.service.ReplyLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyLikeController {
    private final ReplyLikeService replyLikeService;

    @GetMapping("/reply-like/{boardId}/{memberId}")
    public List<ReplyLikeResponseDto> readReplyLike(@PathVariable Long boardId, @PathVariable Long memberId) {
        return replyLikeService.read(boardId, memberId);
    }

    @PostMapping("/reply-like")
    public ReplyLikeResponseDto createReplyLike(@RequestBody ReplyLikeRequestDto replyLikeRequestDto) {
        return replyLikeService.create(replyLikeRequestDto);
    }

    @DeleteMapping("/reply-like/{replyId}/{memberId}")
    public ReplyLikeResponseDto deleteReplyLike(@PathVariable Long replyId, @PathVariable Long memberId) {
        return replyLikeService.delete(replyId, memberId);
    }
}
