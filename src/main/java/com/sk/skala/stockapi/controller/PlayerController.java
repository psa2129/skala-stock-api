package com.sk.skala.stockapi.controller;

import org.springframework.web.bind.annotation.*;
import com.sk.skala.stockapi.data.common.Response;
import com.sk.skala.stockapi.data.table.Player;
import com.sk.skala.stockapi.service.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // POST /api/players/buy
    @PostMapping("/buy")
    public Response buyPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    // POST /api/players/sell
    @PostMapping("/sell")
    public Response sellPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    // DELETE /api/players (반드시 서비스의 deletePlayer를 호출해야 리스트에서 삭제됩니다!)
    @DeleteMapping
    public Response deletePlayer(@RequestBody Player player) {
        return playerService.deletePlayer(player);
    }

    // GET /api/players (목록 조회)
    @GetMapping
    public Response getPlayerList(@RequestParam(defaultValue = "0") int offset, 
                                  @RequestParam(defaultValue = "10") int count) {
        return playerService.getAllPlayers(offset, count);
    }
}