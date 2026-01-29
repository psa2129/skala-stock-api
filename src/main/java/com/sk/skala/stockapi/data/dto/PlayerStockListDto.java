package com.sk.skala.stockapi.data.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * [실습 1-5] DTO - 플레이어 보유 주식 목록 조회 응답
 * Player가 보유한 주식 목록 조회 응답 - Builder 패턴으로 메서드 체이닝 제공
 */
@Getter
@Setter
@NoArgsConstructor  // 빈 생성자
@AllArgsConstructor // 전체 인자 생성자
@Builder            // Builder 패턴으로 메서드 체이닝 제공
public class PlayerStockListDto {

    private String playerId; // 속성: String playerId

    private Double playerMoney; // 속성: Double playerMoney

    private List<PlayerStockDto> stocks; // 속성: List<PlayerStockDto> stocks

}