package foodsm.foodsm.validator;

import foodsm.foodsm.dto.member.MemberSaveRequestDTO;
import foodsm.foodsm.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<MemberSaveRequestDTO> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberSaveRequestDTO dto, Errors errors) {
        if(memberRepository.existsByEmail(dto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}