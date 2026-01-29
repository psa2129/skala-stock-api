package com.sk.skala.stockapi.data.table;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player")
@Getter @Setter
@NoArgsConstructor
public class Player {
    @Id
    private String playerId;

    private String playerPassword;
    private Double playerMoney;

    // ID와 초기 투자금을 인자로 받는 생성자
    public Player(String playerId, Double playerMoney) {
        this.playerId = playerId;
        this.playerMoney = playerMoney;
    }
}