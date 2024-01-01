package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class mine {
    EntityManager em = emf.createEntityManger;
    EntityTransaction transaction = em.getTransaction();
    //엔티티 매니저는 데이터 변경 시 트랜잭션을 시작해야 한다.
    transaction.begin();

    //회원 엔티티 영속상태
    em.persist(member);

    //회원 엔티티 준영속 상태
    em.detach(member)

    //커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
    transcation.commit();


}
