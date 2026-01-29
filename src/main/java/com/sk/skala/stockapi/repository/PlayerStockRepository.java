package com.sk.skala.stockapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sk.skala.stockapi.data.table.Player;
import com.sk.skala.stockapi.data.table.PlayerStock;
import com.sk.skala.stockapi.data.table.Stock;

/**
 * [실습 2-3] Repository - 플레이어 보유 주식 매핑 저장소
 * PlayerStock 엔티티를 관리하기 위해 JpaRepository 상속한 인터페이스 구현
 */
public interface PlayerStockRepository extends JpaRepository<PlayerStock, Long> {

    /**
     * 사용자 정의 메서드 1: 특정 플레이어의 ID로 보유 주식 목록 전체 조회
     * 속성 순회(Property Traversal) 활용: 연관된 Player 엔티티의 playerId 필드 값과 일치하는 목록 조회
     */
    List<PlayerStock> findByPlayer_PlayerId(String playerId);

    /**
     * 사용자 정의 메서드 2: 특정 플레이어(Player)가 특정 주식(Stock)을 보유하고 있는지 검색
     * 존재 여부 확인을 위해 Optional 타입을 사용함
     */
    Optional<PlayerStock> findByPlayerAndStock(Player player, Stock stock);

}