package foodsm.foodsm.controller;

import foodsm.foodsm.dto.image.ImageResponseDTO;
import foodsm.foodsm.dto.member.MemberPasswordUpdateDTO;
import foodsm.foodsm.dto.member.MemberResponseDTO;
import foodsm.foodsm.dto.member.MemberUsernameUpdateDTO;
import foodsm.foodsm.service.GlobalService;
import foodsm.foodsm.service.image.ImageService;
import foodsm.foodsm.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final GlobalService globalService;
    private final MemberService memberService;
    private final ImageService imageService;

    /**
     * 회원 목록 조회
     */
    @GetMapping("/list")
    public String members(Model model) {
        List<MemberResponseDTO> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/member/memberList";
    }

    /**
     * 회원 정보 조회
     */
    @GetMapping("/info")
    public String memberInfo(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDTO member = memberService.findMember(userDetails.getUsername());
        ImageResponseDTO image = imageService.findImage(userDetails.getUsername());

        model.addAttribute("member", member);
        model.addAttribute("image", image);

        return "/member/info";
    }

    /**
     * 회원 이름 변경
     */
    @GetMapping("/update/username")
    public String updateUsernameForm(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDTO member = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", member);

        return "/member/updateUsername";
    }

    /**
     * 회원 이름 변경 post
     */
    @PostMapping("/update/username")
    public String updateUsername(@Valid MemberUsernameUpdateDTO memberUsernameUpdateDTO, Errors errors, Model model, Authentication authentication) {
        if (errors.hasErrors()) {
            model.addAttribute("member", memberUsernameUpdateDTO);
            globalService.messageHandling(errors, model);
            return "/member/updateUsername";
        }

        memberService.updateMemberUsername(memberUsernameUpdateDTO);

        return "redirect:/member/info";
    }

    /**
     * 회원 비밀번호 변경
     */
    @GetMapping("/update/password")
    public String updatePasswordForm() {
        return "/member/updatePassword";
    }

    /**
     * 회원 비밀번호 변경 post
     */
    @PostMapping("/update/password")
    public String updatePassword(@Valid MemberPasswordUpdateDTO memberPasswordUpdateDTO, Model model, Authentication authentication) {
        // new password 비교
        if (!Objects.equals(memberPasswordUpdateDTO.getNewPassword(), memberPasswordUpdateDTO.getConfirmPassword())) {
            model.addAttribute("dto", memberPasswordUpdateDTO);
            model.addAttribute("differentPassword", "비밀번호가 같지 않습니다.");
            return "/member/updatePassword";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Long result = memberService.updateMemberPassword(memberPasswordUpdateDTO, userDetails.getUsername());

        // 현재 비밀번호가 틀렸을 경우
        if (result == null) {
            model.addAttribute("dto", memberPasswordUpdateDTO);
            model.addAttribute("wrongPassword", "비밀번호가 맞지 않습니다.");
            return "/member/updatePassword";
        }

        return "redirect:/member/info";
    }

    /**
     * 회원 탈퇴
     */
    @GetMapping("/withdrawal")
    public String memberWithdrawalForm() {
        return "/member/withdrawal";
    }

    /**
     * 회원 탈퇴 post
     */
    @PostMapping("/withdrawal")
    public String memberWithdrawal(@RequestParam String password, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean result = memberService.withdrawal(userDetails.getUsername(), password);

        if (result) {
            return "redirect:/logout";
        } else {
            model.addAttribute("wrongPassword", "비밀번호가 맞지 않습니다.");
            return "/member/withdrawal";
        }
    }
}