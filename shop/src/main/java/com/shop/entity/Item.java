package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "item")
@Getter @Setter
@ToString
public class Item extends BaseEntity{
    //상품 코드
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //상품명
    @Column(nullable = false,length = 50)
    private String itemNm;

    //가격
    @Column(name = "price", nullable = false)
    private int price;

    //재고수량
    @Column(nullable = false)
    private int StockNumber;

    //상품 상세 설명
    @Lob
    @Column(nullable = false)
    private String itemDetail;

    //상품 판매 상태
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;
}
