package foodsm.foodsm.controller;

import foodsm.foodsm.dto.member.MemberResponseDTO;
import foodsm.foodsm.dto.post.PostResponseDTO;
import foodsm.foodsm.dto.post.PostWriteRequestDTO;
import foodsm.foodsm.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    /**
     * 게시글 작성
     */
    @GetMapping("/write")
    public String writeForm(){
        return "post/write";
    }

    /**
     * 게시글 작성 post
     */
    @PostMapping("/write")
    public String write(PostWriteRequestDTO postWriteRequestDTO, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        postService.savePost(postWriteRequestDTO, userDetails.getUsername());

        return "redirect:/";
    }

    /**
     * 게시글 전제 조회
     */
    @GetMapping("/list")
    public String members(Model model) {
        List<PostResponseDTO> posts = postService.findPosts();
        model.addAttribute("posts", posts);

        return "/post/list";
    }


    /**
     * 게시글 상세 조회
     */
    @GetMapping("/{id}")
    public String postDetail(@PathVariable Long id, Model model){
        PostResponseDTO result = postService.postDetail(id);
        model.addAttribute("dto", result);
        model.addAttribute("id", id);

        return "post/detail";
    }


}
