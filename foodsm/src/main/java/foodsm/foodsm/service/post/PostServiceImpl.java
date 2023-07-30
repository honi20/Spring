package foodsm.foodsm.service.post;

import foodsm.foodsm.domain.Member;
import foodsm.foodsm.domain.Post;
import foodsm.foodsm.dto.member.MemberResponseDTO;
import foodsm.foodsm.dto.post.PostResponseDTO;
import foodsm.foodsm.dto.post.PostWriteRequestDTO;
import foodsm.foodsm.repository.MemberRepository;
import foodsm.foodsm.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Long savePost(PostWriteRequestDTO postWriteRequestDTO,
                         String email){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        Post result = Post.builder()
                .name(postWriteRequestDTO.getName())
                .imageUrl(postWriteRequestDTO.getImageUrl())
                .category(postWriteRequestDTO.getCategory())
                .star(postWriteRequestDTO.getStar())
                .comment(postWriteRequestDTO.getComment())
                .location(postWriteRequestDTO.getLocation())
                .member(member)
                .build();

        postRepository.save(result);
        return result.getId();
    }

    @Override
    public PostResponseDTO postDetail(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 게시글입니다."));

        PostResponseDTO result = PostResponseDTO.builder()
                .post(post)
                .build();

        return result;
    }

    @Override
    public List<PostResponseDTO> findPosts() {
        List<Post> all = postRepository.findAll();
        List<PostResponseDTO> posts = new ArrayList<>();

        for (Post post: all) {
            PostResponseDTO build = PostResponseDTO.builder()
                    .post(post)
                    .build();
            posts.add(build);
        }

        return posts;
    }

//    @Override
//    public Page<PostResponseDTO> postList(Pageable pageable){
//        Page<Post> posts = postRepository.findAll(pageable);
//        List<PostResponseDTO> postDTOS = new ArrayList<>();
//
//        for (Post post : posts){
//            PostResponseDTO result = PostResponseDTO.builder()
//                    .post(post)
//                    .build();
//            postDTOS.add(result);
//        }
//        return new PageImpl<>(postDTOS, pageable, posts.getTotalElements());
//    }

}
