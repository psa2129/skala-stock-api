package com.sk.skala.stockapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sk.skala.stockapi.data.table.Player;

/**
 * [실습 2-2] Repository - 플레이어 데이터 저장소
 * Player 엔티티를 관리하기 위해 JpaRepository 상속한 인터페이스 구현
 */
public interface PlayerRepository extends JpaRepository<Player, String> {
    // 사용자 정의 메서드 : 필요 없음
}