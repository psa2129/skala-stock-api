package com.sk.skala.stockapi.controller;

import org.springframework.web.bind.annotation.*;
import com.sk.skala.stockapi.data.common.Response;
import com.sk.skala.stockapi.data.dto.StockOrder;
import com.sk.skala.stockapi.service.SessionHandler;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/players") // OAS Editor의 'players' 주소와 완벽 일치
public class PlayerController {

    private final SessionHandler sessionHandler;

    // 생성자 (롬복 대신 수동 작성하여 의존성 주입)
    public PlayerController(SessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }

    // 1. [POST] /api/players/login (로그인)
    @PostMapping("/login")
    public Response login() {
        return Response.ok("로그인 성공 테스트입니다.");
    }

    // 2. [GET] /api/players/list (목록 조회)
    @GetMapping("/list")
    public Response getPlayerList() {
        return Response.ok("플레이어 목록 조회 성공!");
    }

    // 3. [GET] /api/players/{playerId} (상세 조회)
    @GetMapping("/{playerId}")
    public Response getPlayerDetail(@PathVariable String playerId) {
        return Response.ok(playerId + "님의 상세 정보 조회 성공!");
    }

    // 4. [PUT] /api/players (정보 수정)
    @PutMapping
    public Response updatePlayer() {
        return Response.ok("플레이어 정보 수정 성공!");
    }

    // 5. [DELETE] /api/players (회원 탈퇴)
    @DeleteMapping
    public Response deletePlayer() {
        return Response.ok("플레이어 삭제 성공!");
    }

    // 6. [POST] /api/players (회원 생성)
    @PostMapping
    public Response createPlayer() {
        return Response.ok("신규 플레이어 생성 성공!");
    }

    // 7. [POST] /api/players/buy (매수)
    @PostMapping("/buy")
    public Response buyStock(@Valid @RequestBody StockOrder order) {
        // 테스트를 위해 세션 체크를 잠시 우회하고 성공 응답 반환
        return Response.ok("매수 주문 성공! (수량: " + order.getStockQuantity() + ")");
    }

    // 8. [POST] /api/players/sell (매도)
    @PostMapping("/sell")
    public Response sellStock(@Valid @RequestBody StockOrder order) {
        return Response.ok("매도 주문 성공! (수량: " + order.getStockQuantity() + ")");
    }
}