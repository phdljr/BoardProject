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

    private final Long MEMBER_NONE_EXIST = -1L;

    @Override
    public List<ReplyLikeResponseDto> read(Long boardId, Long memberId) {
        List<ReplyLikeResponseDto> replyLikeResponseDtos = new ArrayList<>();
        List<Long> replyIds = new ArrayList<>();

        List<Reply> findReplies = replyRepository.findByBoardId(boardId);
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
        validateMember(replyLikeRequestDto.getMemberId());

        Optional<Member> findMember = memberRepository.findById(replyLikeRequestDto.getMemberId());
        validateOptional(findMember);

        Member member = findMember.get();

        Optional<Reply> findReply = replyRepository.findById(replyLikeRequestDto.getReplyId());
        validateOptional(findReply);

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
        Optional<ReplyLike> findReplyLike = replyLikeRepository.findByReplyIdAndMemberId(replyId, memberId);
        validateOptional(findReplyLike);
        ReplyLike replyLike = findReplyLike.get();
        replyLikeRepository.delete(replyLike);

        return createReplyLikeResponseDto(replyId, memberId);
    }

    private ReplyLikeResponseDto createReplyLikeResponseDto(Long replyId, Long memberId) {
        Long likeCount = replyLikeRepository.countByReplyId(replyId);
        boolean hasLiked = false;
        if(memberId != MEMBER_NONE_EXIST){
            hasLiked = replyLikeRepository.existsByReplyIdAndMemberId(replyId, memberId);
        }
        ReplyLikeResponseDto replyLikeResponseDto = ReplyLikeResponseDto.builder()
                .replyId(replyId)
                .countLike(likeCount)
                .hasLiked(hasLiked)
                .build();

        return replyLikeResponseDto;
    }

    private void validateMember(Long memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("로그인 정보가 없습니다.");
        }
    }

    private void validateOptional(Optional<?> findEntity){
        if(findEntity.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 데이터입니다.");
        }
    }
}
