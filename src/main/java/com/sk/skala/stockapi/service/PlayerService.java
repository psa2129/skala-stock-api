package com.sk.skala.stockapi.service;

import org.springframework.stereotype.Service;
import com.sk.skala.stockapi.data.common.Response;
import com.sk.skala.stockapi.data.table.Player;
import com.sk.skala.stockapi.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor // Repository 주입을 위한 생성자 자동 생성
public class PlayerService {

    private final PlayerRepository playerRepository;

    // 플레이어 생성 및 구매/판매 데이터 저장
    public Response createPlayer(Player player) {
        playerRepository.save(player);
        Response response = new Response();
        response.setResult(0);
        response.setMessage("성공");
        return response;
    }

    // [중요] 플레이어 삭제 메서드 추가: 이 메서드가 없어서 에러가 났던 것입니다.
    public Response deletePlayer(Player player) {
        if (player.getPlayerId() != null) {
            // ID를 기준으로 실제 데이터를 삭제합니다.
            playerRepository.deleteById(player.getPlayerId());
        }
        
        Response response = new Response();
        response.setResult(0);
        response.setMessage("정상적으로 삭제되었습니다.");
        return response;
    }

    // 전체 목록 조회
    public Response getAllPlayers(int offset, int count) {
        List<Player> players = playerRepository.findAll();
        Response response = new Response();
        response.setResult(0);
        response.setBody(players);
        return response;
    }
}