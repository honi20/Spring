package foodsm.foodsm.repository;

import foodsm.foodsm.domain.Member;
import foodsm.foodsm.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
//    Page<Post> findByTitle(String keyword, Pageable pageable);

//    Optional<Post> findByEmail(String email);
}
