package com.example.bbsdemo.web;

import com.example.bbsdemo.domain.MemberSVC.MemberSVC;
import com.example.bbsdemo.domain.entity.Member;
import com.example.bbsdemo.web.form.member.JoinForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberSVC memberSVC;

  // 회원가입 창
  @GetMapping("/join")
  public String joinFrom(Model model){

    model.addAttribute("joinForm", new JoinForm());

    return "member/joinForm";
  }

  // 회원가입
  @PostMapping("/join")
  public String join(
      @Valid @ModelAttribute JoinForm joinForm,
      BindingResult bindingResult
  ){
    // 유효성 체크

    // 필드오류
    if (bindingResult.hasErrors()){
      log.info("bindingResult={}",bindingResult);
      return "member/joinForm";
    }

    // 비밀번호와 비밀번호 확인 불일치의 경우
    if (!joinForm.getPasswd().equals(joinForm.getPasswdChk())){
      bindingResult.reject("passwdErr","비밀번호와 비밀번호 확인값이 일치하지 않습니다.");
    }
    if (bindingResult.hasErrors()){
      log.info("bindingResult={}",bindingResult);
      return "member/joinForm";
    }

    // 가입 정상처리
    Member member = new Member();
    BeanUtils.copyProperties(joinForm,member);

    return "redirect:/login";
  }

}
