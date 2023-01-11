package kr.ac.project.boardproject.repository;

import kr.ac.project.boardproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByIdOrderByRegisterDate(Pageable pageable);

    @Modifying
    @Query("update Board b set b.hit = b.hit + 1 where b.id = :id")
    int updatehit(@Param("id") Long id);
}
