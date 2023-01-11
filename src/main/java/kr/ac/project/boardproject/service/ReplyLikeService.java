package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.ReplyLikeRequestDto;
import kr.ac.project.boardproject.dto.response.ReplyLikeResponseDto;

import java.util.List;

public interface ReplyLikeService {
    List<ReplyLikeResponseDto> read(Long boardId, Long memberId);

    ReplyLikeResponseDto create(ReplyLikeRequestDto replyLikeRequestDto);

    ReplyLikeResponseDto delete(Long replyId, Long memberId);
}
