package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 스캔에 의해 스프링 빈으로 등록.
@RequiredArgsConstructor
public class MemberRepository {

    private  final EntityManager em; //엔티티매니저를 생성자로 인젝션 한것이다 결론적으로



    //저장 : persist하면 영속성 컨텍스트에 멤버객체를 넣는다. 그럼 트랜잭션이 커밋되는 시점에 db에 인설트 쿼리가 날라간다
    public void save(Member member){
        em.persist(member);
    }
    //단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //리스트 조회 :jpql사용, sql과 동일하지만, from의 대상이 테이블이 아닌 엔티티인게 jpql
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //이름으로 조회

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
