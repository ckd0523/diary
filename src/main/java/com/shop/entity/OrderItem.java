package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //지연 로딩
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY) //지연 로딩
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

//    private LocalDateTime regTime;
//    private LocalDateTime updateTime;

}
