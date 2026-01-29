package com.sk.skala.stockapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sk.skala.stockapi.data.table.Stock;

/**
 * [실습 2-1] Repository - 주식 데이터 저장소
 * Stock 엔티티를 관리하기 위해 JpaRepository 상속한 인터페이스 구현
 */
public interface StockRepository extends JpaRepository<Stock, Long> {

    /**
     * 사용자 정의 메서드
     * findByStockName : 주식 이름과 동일한 주식 데이터를 조회 (존재 여부 확인 목적 -> Optional)
     */
    Optional<Stock> findByStockName(String stockName);

}