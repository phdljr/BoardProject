package kr.ac.project.boardproject.repository;

import kr.ac.project.boardproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
