package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id") //테이블명_id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //order와 멤버는 다대1관계
    @JoinColumn(name = "member_id") //내가 연관관계 주인이다 외래키 : member_id
    private Member member; //주문 회원

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //ORDER, CANCEL

    //아래 3개는 연관관계 메소드 - 양방향일때 양쪽 세팅할거를 한쪽에서 다 해결하게 해줌
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);

    }

    //주문 생성 메서드
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }


    //비즈니스로직 - 주문취소
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) { //배송완료된 경우
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");

        }
        //취소가능한경우
        this.setStatus(OrderStatus.CANCEL); //상태 변경
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel(); //재고수 원복
        }

    }

    //조회 로직 - 전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
