package com.sk.skala.stockapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sk.skala.stockapi.data.table.Stock;
import com.sk.skala.stockapi.repository.StockRepository;
import com.sk.skala.stockapi.data.common.Response;
import com.sk.skala.stockapi.data.common.PagedList;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    // 생성자 주입 (Lombok 에러 방지용 직접 작성)
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional(readOnly = true)
    public Response getAllStocks(int offset, int count) {
        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(offset, count, Sort.by("id").ascending());
        Page<Stock> page = stockRepository.findAll(pageable);
        
        // 알려주신 PagedList 구조(total, count, offset, list)에 맞춰 데이터 세팅
        PagedList pagedList = new PagedList();
        pagedList.setTotal(page.getTotalElements());
        pagedList.setCount(page.getSize());
        pagedList.setOffset(page.getNumber());
        pagedList.setList(page.getContent());
        
        return Response.ok(pagedList);
    }

    @Transactional(readOnly = true)
    public Response getStockById(Long id) {
        // ID로 주식 조회
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DATA_NOT_FOUND"));
        return Response.ok(stock);
    }

    public Response createStock(Stock stock) {
        // 이름 중복 체크 및 저장
        stockRepository.findByStockName(stock.getStockName()).ifPresent(s -> {
            throw new RuntimeException("DATA_DUPLICATED");
        });
        stock.setId(0L);
        return Response.ok(stockRepository.save(stock));
    }

    public Response updateStock(Stock stock) {
        // 존재 여부 확인 후 수정
        stockRepository.findById(stock.getId())
                .orElseThrow(() -> new RuntimeException("DATA_NOT_FOUND"));
        return Response.ok(stockRepository.save(stock));
    }

    public Response deleteStock(Stock stock) {
        // 존재 여부 확인 후 삭제
        stockRepository.findById(stock.getId())
                .orElseThrow(() -> new RuntimeException("DATA_NOT_FOUND"));
        stockRepository.delete(stock);
        return Response.ok();
    }
}