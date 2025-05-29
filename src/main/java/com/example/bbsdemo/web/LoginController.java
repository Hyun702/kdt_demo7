package com.example.bbsdemo.web;


import com.example.bbsdemo.domain.Member.MemberDAO;
import com.example.bbsdemo.domain.entity.Member;
import com.example.bbsdemo.web.api.product.ApiResponse;
import com.example.bbsdemo.web.api.product.ApiResponseCode;
import com.example.bbsdemo.web.form.login.LoginForm;
import com.example.bbsdemo.web.form.login.LoginMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor

public class LoginController {

  private final MemberDAO memberDAO;

  //로그인 화면
  @GetMapping("/login")
  public String loginFrom(Model model){
    model.addAttribute("loginForm", new LoginForm());
    return "login/loginForm";
  }

  //로그인 처리
  @PostMapping("/login")
  public String login(
      @Valid @ModelAttribute LoginForm loginForm,
      BindingResult bindingResult,
      HttpServletRequest request
  ){
    log.info("loginForm={}", loginForm);

    // 로그인 바인딩 오류 체크
    if (bindingResult.hasErrors()){
      return "login/loginForm";
    }

    // 회원 존재 유무 체크
    if (!memberDAO.isExist(loginForm.getEmail())){
      bindingResult.rejectValue("email", null, "회원이 존재하지 않습니다");
      return "login/loginForm";
    }

    // 비밀번호 일치 여부 체크
    Optional<Member> optionalMember = memberDAO.findByEmail(loginForm.getEmail());
    Member member = optionalMember.get();
    log.info("member={}",member);

    if (!loginForm.getPasswd().equals(member.getPasswd())){
      bindingResult.rejectValue("passwd", null, "비밀번호가 맞지 않습니다.");
      return "login/loginForm";
    }

    HttpSession session =request.getSession(true);

    LoginMember loginMember = new LoginMember(
        member.getMemberId(),
        member.getEmail(),
        member.getNickname()
    );

    session.setAttribute("loginMember",loginMember);

    return "redirect:/";

  }

  // 로그아웃 처리
  @DeleteMapping("logout")
  @ResponseBody
  public ResponseEntity<ApiResponse<String>> logout(HttpServletRequest request){

    ResponseEntity<ApiResponse<String>> res = null;

    HttpSession session = request.getSession(false);

    if (session != null){
      session.invalidate();
      ApiResponse<String> stringApiResponse = ApiResponse.of(ApiResponseCode.SUCCESS, "세션 제거 완료!");
      res = ResponseEntity.ok(stringApiResponse);
    }

    return res;

  }

}
