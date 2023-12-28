package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    //상품저장
    public void save(Item item){//상품은 jpa에 저장할때까지 id값이 없다.
        if(item.getId() == null){
            em.persist(item); //그러므로, 새로운 아이템이면 신규로 등록
        }else{
            em.merge(item); //이미 db에 등록된걸 가져오는경우, 다음과같이 상품 update한다.
        }
    }

    //단건조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    //상품리스트 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList(); //여러개 조회시에는 JPQL사용해야함
    }




}
