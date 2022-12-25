package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.ReplyRequestDto;
import kr.ac.project.boardproject.dto.response.ReplyResponseDto;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.entity.MemberType;
import kr.ac.project.boardproject.entity.Reply;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.repository.ReplyRepository;
import kr.ac.project.boardproject.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public List<ReplyResponseDto> read(Long boardId) {
        List<Reply> findReplies = replyRepository.findByBoardId(boardId);
        // dto로 변환
        List<ReplyResponseDto> replyResponseDtos = new ArrayList<>();
        findReplies.forEach(reply -> {
            ReplyResponseDto resultDto = ReplyResponseDto.builder()
                    .replyId(reply.getId())
                    .boardId(reply.getBoard().getId())
                    .content(reply.getContent())
                    .memberId(reply.getMember().getId())
                    .registerDate(reply.getRegisterDate())
                    .modifyDate(reply.getModifyDate())
                    .build();
            replyResponseDtos.add(resultDto);
        });
        return replyResponseDtos;
    }

    @Override
    @Transactional
    public void create(ReplyRequestDto replyRequestDto) {
        Optional<Member> findMember = memberRepository.findById(replyRequestDto.getMemberId());
        if (findMember.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Member member = findMember.get();


        Optional<Board> findBoard = boardRepository.findById(replyRequestDto.getBoardId());
        if (findBoard.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Board board = findBoard.get();


        Reply reply = Reply.builder()
                .board(board)
                .content(replyRequestDto.getContent())
                .member(member)
                .build();

        Reply save = replyRepository.save(reply);
    }

    @Override
    @Transactional
    public void update(Long id, ReplyRequestDto replyRequestDto) {
        Optional<Reply> findReply = replyRepository.findById(id);
        if (findReply.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Reply reply = findReply.get();

        Optional<Member> findMember = memberRepository.findById(replyRequestDto.getMemberId());
        if (findMember.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Member member = findMember.get();

        // 수정 요청한 멤버와 댓글을 작성한 멤버가 다르고 관리자가 아니라면 예외 처리
        if (isNotSameMember(replyRequestDto, reply) && isUser(member)) {
            throw new IllegalArgumentException();
        }

        reply.updateReply(replyRequestDto.getContent());

    }

    public boolean isNotSameMember(ReplyRequestDto requestMember, Reply reply) {
        if (requestMember.getMemberId() != reply.getMember().getId())
            return true;
        else
            return false;

    }

    public boolean isUser(Member member) {
        if (member.getMemberType() == MemberType.USER)
            return true;
        else
            return false;
    }

    @Override
    public void delete(Long id) {
        Optional<Reply> findReply = replyRepository.findById(id);
        if (findReply.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Reply reply = findReply.get();

        replyRepository.delete(reply);

    }
}
