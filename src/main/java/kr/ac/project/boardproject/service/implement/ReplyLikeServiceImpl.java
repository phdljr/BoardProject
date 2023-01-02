package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.ReplyLikeRequestDto;
import kr.ac.project.boardproject.dto.response.ReplyLikeResponseDto;
import kr.ac.project.boardproject.entity.Member;
import kr.ac.project.boardproject.entity.Reply;
import kr.ac.project.boardproject.entity.ReplyLike;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.repository.ReplyLikeRepository;
import kr.ac.project.boardproject.repository.ReplyRepository;
import kr.ac.project.boardproject.service.ReplyLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyLikeServiceImpl implements ReplyLikeService {

    private final ReplyLikeRepository replyLikeRepository;
    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<ReplyLikeResponseDto> read(Long boardId, Long memberId) {
        List<ReplyLikeResponseDto> replyLikeResponseDtos = new ArrayList<>();
        List<Long> replyIds = new ArrayList<>();

        List<Reply> findReplies = replyRepository.findByBoard_Id(boardId);
        findReplies.forEach(reply -> {
            replyIds.add(reply.getId());
        });

        replyIds.forEach(replyId -> {
            ReplyLikeResponseDto replyLikeResponseDto = createReplyLikeResponseDto(replyId, memberId);
            replyLikeResponseDtos.add(replyLikeResponseDto);
        });

        return replyLikeResponseDtos;
    }

    @Override
    public ReplyLikeResponseDto create(ReplyLikeRequestDto replyLikeRequestDto) {
        isMember(replyLikeRequestDto.getMemberId());

        Optional<Member> findMember = memberRepository.findById(replyLikeRequestDto.getMemberId());
        if (findMember.isEmpty())
            throw new IllegalArgumentException("댓글 좋아요를 할 사용자를 찾을 수 없습니다.");

        Member member = findMember.get();

        Optional<Reply> findReply = replyRepository.findById(replyLikeRequestDto.getReplyId());
        if (findReply.isEmpty())
            throw new IllegalArgumentException("댓글 좋아요를 할 댓글을 찾을 수 없습니다.");

        Reply reply = findReply.get();


        ReplyLike replyLike = ReplyLike.builder()
                .reply(reply)
                .member(member)
                .build();

        replyLikeRepository.save(replyLike);

        return createReplyLikeResponseDto(replyLikeRequestDto.getReplyId(), replyLikeRequestDto.getMemberId());
    }

    @Override
    public ReplyLikeResponseDto delete(Long replyId, Long memberId) {
        Optional<ReplyLike> findReplyLike = replyLikeRepository.findByReply_IdAndMember_Id(replyId, memberId);
        if (findReplyLike.isEmpty())
            throw new IllegalArgumentException("좋아요를 취소할 댓글이 없습니다.");
        ReplyLike replyLike = findReplyLike.get();
        replyLikeRepository.delete(replyLike);

        return createReplyLikeResponseDto(replyId, memberId);
    }

    private ReplyLikeResponseDto createReplyLikeResponseDto(Long replyId, Long memberId) {
        Long likeCount = replyLikeRepository.countByReply_Id(replyId);
        boolean hasLiked = replyLikeRepository.existsByReply_IdAndMember_Id(replyId, memberId);
        ReplyLikeResponseDto replyLikeResponseDto = ReplyLikeResponseDto.builder()
                .replyId(replyId)
                .likeCount(likeCount)
                .hasLiked(hasLiked)
                .build();

        return replyLikeResponseDto;
    }

    private void isMember(Long memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("로그인 정보가 없습니다.");
        }
    }
}
