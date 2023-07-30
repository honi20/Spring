package foodsm.foodsm.dto.post;

import foodsm.foodsm.domain.Star;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteRequestDTO {
    private String imageUrl;
    private String name;
    private String category;
    private String star;
    private String comment;
    private String location;
}
