package com.sk.skala.stockapi.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sk.skala.stockapi.data.common.PagedList;
import com.sk.skala.stockapi.data.common.Response;
import com.sk.skala.stockapi.data.dto.PlayerStockDto;
import com.sk.skala.stockapi.data.dto.PlayerStockListDto;
import com.sk.skala.stockapi.data.table.Player;
import com.sk.skala.stockapi.repository.PlayerRepository;
import com.sk.skala.stockapi.repository.PlayerStockRepository;
import com.sk.skala.stockapi.repository.StockRepository;

@Service
@Transactional
public class PlayerService {

    private final StockRepository stockRepository;
    private final PlayerRepository playerRepository;
    private final PlayerStockRepository playerStockRepository;

    // 직접 생성자 작성 (롬복 없이 초기화 에러 해결)
    public PlayerService(StockRepository stockRepository, 
                         PlayerRepository playerRepository, 
                         PlayerStockRepository playerStockRepository) {
        this.stockRepository = stockRepository;
        this.playerRepository = playerRepository;
        this.playerStockRepository = playerStockRepository;
    }

    @Transactional(readOnly = true)
    public Response getAllPlayers(int offset, int count) {
        Pageable pageable = PageRequest.of(offset, count);
        Page<Player> page = playerRepository.findAll(pageable);

        PagedList pagedList = new PagedList();
        pagedList.setTotal(page.getTotalElements());
        pagedList.setCount((long) page.getSize());
        pagedList.setOffset((long) page.getNumber());
        pagedList.setList(page.getContent());

        return Response.ok(pagedList);
    }

    @Transactional(readOnly = true)
    public Response getPlayerById(String playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        List<PlayerStockDto> stockDtos = playerStockRepository.findByPlayer_PlayerId(playerId)
                .stream()
                .map(ps -> {
                    PlayerStockDto dto = new PlayerStockDto();
                    dto.setStockId(ps.getStock().getId());
                    dto.setStockName(ps.getStock().getStockName());
                    dto.setStockPrice(ps.getStock().getStockPrice());
                    dto.setQuantity(ps.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());

        PlayerStockListDto responseDto = new PlayerStockListDto();
        responseDto.setPlayerId(player.getPlayerId());
        responseDto.setPlayerMoney(player.getPlayerMoney());
        responseDto.setStocks(stockDtos);

        return Response.ok(responseDto);
    }
}