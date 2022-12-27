package kr.ac.project.boardproject.repository;

import kr.ac.project.boardproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByIdOrderByRegisterDate(Pageable pageable);
}
