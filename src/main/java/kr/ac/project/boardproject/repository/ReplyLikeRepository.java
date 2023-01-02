package kr.ac.project.boardproject.repository;

import kr.ac.project.boardproject.entity.ReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {
    Optional<ReplyLike> findByReply_IdAndMember_Id(Long replyId, Long memberId);

    boolean existsByReply_IdAndMember_Id(Long replyId, Long memberId);

    Long countByReply_Id(Long replyId);
}
