package foodsm.foodsm.dto.member;

import foodsm.foodsm.domain.Member;
import foodsm.foodsm.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDTO {
    private String email;
    private String username;
    private Role role;

    @Builder
    public MemberResponseDTO(Member member){
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.role = member.getRole();
    }
}
