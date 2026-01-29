package com.sk.skala.stockapi.controller;

import org.springframework.web.bind.annotation.*;
import com.sk.skala.stockapi.data.common.Response;
import com.sk.skala.stockapi.data.table.Stock;
import com.sk.skala.stockapi.service.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // [GET] /api/stocks - 주식 목록 조회
    @GetMapping
    public Response getStockList(@RequestParam(defaultValue = "0") int offset, 
                                 @RequestParam(defaultValue = "10") int count) {
        return stockService.getAllStocks(offset, count);
    }

    // [POST] /api/stocks - 주식 추가
    @PostMapping
    public Response createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

    // [GET] /api/stocks/{id} - 주식 상세 조회
    @GetMapping("/{id}")
    public Response getStockById(@PathVariable Long id) {
        return stockService.getStockById(id);
    }
}