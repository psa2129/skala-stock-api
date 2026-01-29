package com.sk.skala.stockapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sk.skala.stockapi.data.table.Player;

// JpaRepository를 상속받으면 별도 구현 없이 deleteById를 사용할 수 있습니다.
public interface PlayerRepository extends JpaRepository<Player, String> {
}