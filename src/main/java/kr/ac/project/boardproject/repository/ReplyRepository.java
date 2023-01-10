package kr.ac.project.boardproject.repository;

import kr.ac.project.boardproject.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByBoardId(Long boardId);

    Long countByBoardId(Long boardId);
}
