package kr.ac.project.boardproject.controller;


import kr.ac.project.boardproject.dto.request.ReplyLikeRequestDto;
import kr.ac.project.boardproject.service.ReplyLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyLikeController {
    private final ReplyLikeService replyLikeService;

    @PostMapping("/replylike")
    public List<?> createReplyLike(@RequestBody ReplyLikeRequestDto replyLikeRequestDto){
        replyLikeService.create(replyLikeRequestDto);
    }
    @DeleteMapping("/replylike")
    public List<?> deleteReplyLike(@RequestBody ReplyLikeRequestDto replyLikeRequestDto){
        replyLikeService.delete(replyLikeRequestDto);
    }
}
