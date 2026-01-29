package com.sk.skala.stockapi.data.dto; // 폴더 구조에 맞춰 dto 패키지 권장

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * [실습 1-4] DTO - 플레이어가 보유한 주식
 * Player가 보유하는 주식 정보 (주식 엔티티에 수량을 추가)
 * Builder 패턴으로 메서드 체이닝 제공
 */
@Getter
@Setter
@NoArgsConstructor  // 빈 생성자
@AllArgsConstructor // Builder 사용 시 모든 필드 생성자 필요
@Builder            // Builder 패턴 제공
public class PlayerStockDto {

    private Long stockId;      // 속성: Long stockId
    private String stockName;  // 속성: String stockName
    private Double stockPrice; // 속성: Double stockPrice
    private Integer quantity;  // 속성: Integer quantity

}