package kr.ac.project.boardproject.repository;

import kr.ac.project.boardproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmailAndPassword(String email, String password);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String email);
}
