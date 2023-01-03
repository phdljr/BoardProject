package kr.ac.project.boardproject.service.implement;

import kr.ac.project.boardproject.dto.request.ReplyLikeRequestDto;
import kr.ac.project.boardproject.dto.response.ReplyLikeResponseDto;
import kr.ac.project.boardproject.entity.*;
import kr.ac.project.boardproject.repository.BoardRepository;
import kr.ac.project.boardproject.repository.MemberRepository;
import kr.ac.project.boardproject.repository.ReplyLikeRepository;
import kr.ac.project.boardproject.repository.ReplyRepository;
import kr.ac.project.boardproject.service.ReplyLikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
class ReplyLikeServiceImplTest {

    @Autowired
    private ReplyLikeService replyLikeService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ReplyLikeRepository replyLikeRepository;

    private Member member;
    private Board board;
    private Reply reply;

    @BeforeEach
    public void setup() {
        member = Member.builder()
                .email("test@test.com")
                .password("test")
                .nickname("test")
                .memberType(MemberType.USER)
                .build();
        member = memberRepository.save(member);

        board = Board.builder()
                .member(member)
                .content("test")
                .build();
        board = boardRepository.save(board);

        reply = Reply.builder()
                .member(member)
                .board(board)
                .content("test")
                .build();
        reply = replyRepository.save(reply);

        for (int i = 1; i <= 10; i++) {
            Member likeMember = Member.builder()
                    .email("test@test.com" + i)
                    .password("test" + i)
                    .nickname("test" + i)
                    .memberType(MemberType.USER)
                    .build();
            likeMember = memberRepository.save(likeMember);

            ReplyLike replyLike = ReplyLike.builder()
                    .member(likeMember)
                    .reply(reply)
                    .build();
            replyLikeRepository.save(replyLike);
        }
    }

    @Test
    @DisplayName("댓글 좋아요 읽기가 잘 되는지 확인한다.")
    @Transactional
    void read() {
        //given

        //when
        List<ReplyLikeResponseDto> dtos = replyLikeService.read(board.getId(), member.getId());
        //then
        dtos.forEach(dto -> {
            Long replyId = dto.getReplyId();
            Long likeCount = dto.getLikeCount();
            boolean hasLiked = dto.isHasLiked();
            assertThat(replyId).isEqualTo(reply.getId());
            assertThat(likeCount).isEqualTo(10);
            assertThat(hasLiked).isFalse();
        });
    }

    @Test
    @DisplayName("댓글 좋아요 생성이 잘 되는지 확인한다.")
    @Transactional
    void create() {
        //given
        ReplyLikeRequestDto replyLikeRequestDto = ReplyLikeRequestDto.builder()
                .replyId(reply.getId())
                .memberId(member.getId())
                .build();

        //when
        ReplyLikeResponseDto replyLikeResponseDto = replyLikeService.create(replyLikeRequestDto);

        //then
        Optional<ReplyLike> findReplyLike = replyLikeRepository.findByReplyIdAndMemberId(reply.getId(), member.getId());
        if (findReplyLike.isEmpty()) {
            fail("댓글 좋아요를 찾지 못함.");
        }
        ReplyLike replyLike = findReplyLike.get();

        assertThat(replyLike.getMember()).isEqualTo(reply.getMember());
        assertThat(replyLike.getReply().getId()).isEqualTo(replyLikeResponseDto.getReplyId());
        assertThat(replyLikeResponseDto.getLikeCount()).isEqualTo(11);
        assertThat(replyLikeResponseDto.isHasLiked()).isTrue();
    }

    @Test
    @DisplayName("댓글 좋아요 취소가 잘 되는지 확인한다.")
    @Transactional
    void delete() {
        //given
        ReplyLike replyLike = ReplyLike.builder()
                .reply(reply)
                .member(member)
                .build();
        replyLikeRepository.save(replyLike);

        //when
        ReplyLikeResponseDto replyLikeResponseDto = replyLikeService.delete(reply.getId(), member.getId());

        //then
        assertThat(replyLikeRepository.existsByReplyIdAndMemberId(reply.getId(), member.getId())).isFalse();
        assertThat(replyLikeResponseDto.getLikeCount()).isEqualTo(10);
        assertThat(replyLikeResponseDto.isHasLiked()).isFalse();
    }
}