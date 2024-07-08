
package com.lib.member.controller;

import com.lib.category.Category;
import com.lib.category.CategoryRepository;
import com.lib.member.Member;
import com.lib.member.MemberRepository;
import com.lib.member.dto.LoginRequest;
import com.lib.member.dto.LoginResponse;
import com.lib.member.dto.MemberRegisterRequest;
import com.lib.member.service.MemberService;
import com.lib.utils.ApiResponse;
import com.lib.utils.ApiResponseGenerator;
import com.lib.utils.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberRepository memberRepository;
    private final SecurityService securityService;
    private final MemberService memberService;

    //로그인
    @PostMapping("/members/login")
    public ApiResponse<ApiResponse.CustomBody<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        String id=loginRequest.getId();
        String pw=loginRequest.getPassword();
        //멤버 확인
        Member loginMember = memberRepository.findByMemberLoginId(id);

        boolean passwordMatch=pw.equals(loginRequest.getPassword());
        if (!passwordMatch) {
            //return ApiResponseGenerator.fail("Invaild password", HttpStatus.UNAUTHORIZED);
        }
        //토큰 받아서 주는 로직
        String token=securityService.createToken(loginMember.getId(),(2 * 1000 * 60));

        LoginResponse loginResponse=new LoginResponse(token);
        return ApiResponseGenerator.success(loginResponse, HttpStatus.OK);
    }

    //회원가입
    @PostMapping("/members/join")
    public ApiResponse<?> join(@RequestBody MemberRegisterRequest member) {
        memberService.save(member);
        return ApiResponseGenerator.success(HttpStatus.OK);
    }

}
