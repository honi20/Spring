package foodsm.foodsm.service.post;

import foodsm.foodsm.dto.member.MemberResponseDTO;
import foodsm.foodsm.dto.post.PostResponseDTO;
import foodsm.foodsm.dto.post.PostWriteRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    /**
     * 게시글 작성
     */
    Long savePost(PostWriteRequestDTO postWriteRequestDTO, String email);

    /**
     * 게시글 상세 조회
     */
    PostResponseDTO postDetail(Long id);

    /**
     * 회원 목록 조회
     */
    List<PostResponseDTO> findPosts();


}
