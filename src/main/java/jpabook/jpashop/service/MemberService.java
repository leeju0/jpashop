package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    //회원가입
    @Transactional //쓰기에는 readOnly X
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member); //예외없으면, 회원 저장하고
        return member.getId(); //회원 아이디 반환 (영속성 컨텍스트에 의해 항상 값 존재)
    }

    private void validateDuplicateMember(Member member) {//중복 검증 메소드
        //exception!! 중복이면 예외발생
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.: ");

        }

    }

    //전체 회원 조회

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //한 회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
