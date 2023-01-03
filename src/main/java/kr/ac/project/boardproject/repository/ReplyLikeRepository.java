package kr.ac.project.boardproject.repository;

import kr.ac.project.boardproject.entity.ReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {
    Optional<ReplyLike> findByReplyIdAndMemberId(Long replyId, Long memberId);

    boolean existsByReplyIdAndMemberId(Long replyId, Long memberId);

    Long countByReplyId(Long replyId);
}
