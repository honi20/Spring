package foodsm.foodsm.controller;

import foodsm.foodsm.dto.member.MemberSaveRequestDTO;
import foodsm.foodsm.service.GlobalService;
import foodsm.foodsm.validator.CheckEmailValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GlobalController {

    private final GlobalService globalService;
    private final CheckEmailValidator checkEmailValidator;

    /* 중복 검사 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
    }

    /**
     * Home 화면
     */
    @GetMapping("/")
    public String Home() {
        return "home";
    }

    /**
     * 예외처리 페이지
     */
    @GetMapping("/denied")
    public String doDenied() {
        return "denied";
    }

    /**
     * 회원 가입
     */
    @GetMapping("/signup")
    public String signupForm() {
        return "member/memberForm";
    }

    /**
     * 회원 가입 post
     */
    @PostMapping("/signup")
    public String signup(@Valid MemberSaveRequestDTO memberSaveRequestDTO, Errors errors, Model model) {
        /* 검증 */
        if (errors.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 유지 */
            model.addAttribute("dto", memberSaveRequestDTO);
            /* 유효성 검사를 통과하지 못한 필드와 메세지 핸들링 */
            globalService.messageHandling(errors, model);
            /* 회원가입 페이지로 리턴 */
            return "/member/memberForm";
        }
        globalService.join(memberSaveRequestDTO);

        return "redirect:/";
    }

    /**
     * 로그인 페이지
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}