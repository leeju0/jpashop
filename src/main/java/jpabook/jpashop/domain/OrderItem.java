package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="order_item")
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; //주문가격
    private int count; //주문수량


    //생성 메소드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); //주문상품 엔티티를 생성하고, 주문한 수량만큼 상품 재고를 줄인다.
        return orderItem;
    }
    
    //비즈니스로직
    public void cancel() {
        getItem().addStock(count); //아이템 가져와서 재고수를 원복해주어야함
    }

    public int getTotalPrice() { //주문가격과 수량을 곱한 총 주문가격
        return getOrderPrice() * getCount();
    }
}
