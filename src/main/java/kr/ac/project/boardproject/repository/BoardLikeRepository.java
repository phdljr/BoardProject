package kr.ac.project.boardproject.repository;

import kr.ac.project.boardproject.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Long countByBoardId(Long boardId);

    boolean existsByBoardIdAndMemberId(Long boardId, Long memberId);

    Optional<BoardLike> findByBoardIdAndMemberId(Long boardId, Long memberId);
}
