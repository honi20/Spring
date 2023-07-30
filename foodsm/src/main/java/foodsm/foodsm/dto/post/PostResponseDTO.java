package foodsm.foodsm.dto.post;

import foodsm.foodsm.domain.Member;
import foodsm.foodsm.domain.Post;
import foodsm.foodsm.domain.Star;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String imageUrl;
    private String name;
    private String category;
    private String star;
    private String comment;
    private String location;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    @Builder
    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.imageUrl = post.getImageUrl();
        this.name = post.getName();
        this.category = post.getCategory();
        this.star = post.getStar();
        this.comment = post.getComment();
        this.location = post.getLocation();
        this.username = post.getMember().getUsername();
        this.email = post.getMember().getEmail();
        this.createdAt = post.getCreatedAt();
    }
}
