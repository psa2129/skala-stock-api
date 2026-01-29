package com.sk.skala.stockapi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.skala.stockapi.data.common.Response;
import com.sk.skala.stockapi.data.table.Stock;
import com.sk.skala.stockapi.service.StockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stocks") // 컨트롤러의 기본 URL 경로 지정
public class StockController {

    private final StockService stockService;

    // 롬복 에러 방지용 직접 생성자 주입
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * // 전체 주식 목록 조회 API
     * 쿼리 파라미터로 offset, count를 받음 (기본값 0, 10)
     */
    @GetMapping("/list")
    public Response getAllStocks(
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "10") Integer count) {
        return stockService.getAllStocks(offset, count);
    }

    /**
     * // 개별 주식 상세 조회 API
     * 경로 변수 {id}를 통해 서비스에서 해당 주식 정보 반환
     */
    @GetMapping("/{id}")
    public Response getStockById(@PathVariable Long id) {
        return stockService.getStockById(id);
    }

    /**
     * // 주식 등록 API
     * JSON 바디로 Stock 엔티티를 입력받아 새 주식 등록
     */
    @PostMapping
    public Response createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

    /**
     * // 주식 정보 수정 API
     * 서비스에서 해당 주식 정보를 업데이트
     */
    @PutMapping
    public Response updateStock(@RequestBody Stock stock) {
        return stockService.updateStock(stock);
    }

    /**
     * // 주식 삭제 API
     * 서비스로 삭제할 주식 정보를 전달
     */
    @DeleteMapping
    public Response deleteStock(@RequestBody Stock stock) {
        return stockService.deleteStock(stock);
    }
}