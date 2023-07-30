package foodsm.foodsm.repository;

import foodsm.foodsm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    /**
     * 이메일로 회원 찾기
     * @param email
     * @return
     */
    Optional<Member> findByEmail(String email);

    /**
     * 유효성 검사 - 중복 체크
     */
    boolean existsByEmail(String email);
}
