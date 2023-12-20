package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    //값 타입은 변경 불가능하게 설계해야한다.
    // 1. setter제거하고, 생성자에서 값을 모두 초기화하는 클래스로 만들기
    // 2. 임베디드 타입은 자바 기본생성자를 public -> protected 로 설정

    private String city;
    private String street;
    private String zipcode;
    protected Address() {

    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
