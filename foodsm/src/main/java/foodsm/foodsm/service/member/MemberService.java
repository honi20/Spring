package foodsm.foodsm.service.member;

import foodsm.foodsm.dto.member.MemberPasswordUpdateDTO;
import foodsm.foodsm.dto.member.MemberResponseDTO;
import foodsm.foodsm.dto.member.MemberUsernameUpdateDTO;

import java.util.List;

public interface MemberService {

    /**
     * 회원 목록 조회
     */
    List<MemberResponseDTO> findMembers();

    /**
     * 회원 정보 조회
     */
    MemberResponseDTO findMember(String email);

    /**
     * 회원 이름 변경
     */
    Long updateMemberUsername(MemberUsernameUpdateDTO memberUsernameUpdateDTO);

    /**
     * 회원 비밀번호 변경
     */
    Long updateMemberPassword(MemberPasswordUpdateDTO memberPasswordUpdateDTO, String email);

    /**
     * 회원 탈퇴
     */
    boolean withdrawal(String email, String password);
}
