package kr.ac.project.boardproject.service;

import kr.ac.project.boardproject.dto.request.ReplyRequestDto;
import kr.ac.project.boardproject.dto.response.ReplyResponseDto;

import java.util.List;

public interface ReplyService {
    List<ReplyResponseDto> read(Long boardId);
    Long create(ReplyRequestDto replyRequestDto);
    void update(Long id, ReplyRequestDto replyRequestDto);
    void delete(Long id);
}
