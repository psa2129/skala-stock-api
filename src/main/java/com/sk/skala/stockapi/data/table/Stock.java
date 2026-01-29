package com.sk.skala.stockapi.data.table;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Getter @Setter
@NoArgsConstructor // 빈 생성자 (JPA 필수)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 순차적 자동 증가
    private Long id;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false)
    private Double stockPrice;

    // 이름과 가격을 인자로 받는 생성자
    public Stock(String stockName, Double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }
}