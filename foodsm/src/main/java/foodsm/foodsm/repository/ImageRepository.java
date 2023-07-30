package foodsm.foodsm.repository;

import foodsm.foodsm.domain.Image;
import foodsm.foodsm.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByMember(Member member);
}
