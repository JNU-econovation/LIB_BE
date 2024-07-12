package com.lib.member.service;

import com.lib.category.Category;
import com.lib.category.CategoryRepository;
import com.lib.member.Member;
import com.lib.member.MemberRepository;
import com.lib.member.dto.MemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Transactional//save나 수정하기 등을 할때 필요함 (추가적으로 공부하기)
    //로그인쪽 컨트롤러로 옮겨보기
    public void save(MemberRegisterRequest member) {
        Category category = categoryRepository.findByCategoryType(member.getCategory());
        Member newMember = Member.builder().memberLoginId(member.getId()).pw(member.getPassword()).nickname(member.getNickname()).category(category).resetCycle(member.getResetCycle()).goalNumber(member.getGoalNumber()).build();
        memberRepository.save(newMember);
    }


}
