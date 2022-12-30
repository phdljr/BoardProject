package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.ReplyLikeRequestDto;

import java.util.List;

public interface ReplyLikeService {
    List<?> create(ReplyLikeRequestDto replyLikeRequestDto);

    List<?> delete(ReplyLikeRequestDto replyLikeRequestDto);
}
