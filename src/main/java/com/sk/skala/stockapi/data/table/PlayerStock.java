package com.sk.skala.stockapi.data.table;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_stock")
@Getter @Setter
@NoArgsConstructor
public class PlayerStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 설정
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private Integer quantity;

    // 플레이어, 주식, 수량을 인자로 받는 생성자
    public PlayerStock(Player player, Stock stock, Integer quantity) {
        this.player = player;
        this.stock = stock;
        this.quantity = quantity;
    }
}