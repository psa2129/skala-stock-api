package com.sk.skala.stockapi.service;

import org.springframework.data.domain.*;
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

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional(readOnly = true)
    public Response getAllStocks(int offset, int count) {
        // ID 내림차순 정렬로 변경 (방금 넣은 큰 숫자 ID가 맨 처음에 나오게 함)
        Pageable pageable = PageRequest.of(offset, count, Sort.by("id").descending());
        Page<Stock> page = stockRepository.findAll(pageable);
        
        PagedList pagedList = new PagedList();
        pagedList.setTotal(page.getTotalElements());
        pagedList.setCount((long) page.getSize());
        pagedList.setOffset((long) page.getNumber());
        pagedList.setList(page.getContent()); // 이 부분이 null이면 리스트가 안 나옵니다!
        
        return Response.ok(pagedList);
    }

    public Response createStock(Stock stock) {
        // 이름 중복 체크
        stockRepository.findByStockName(stock.getStockName()).ifPresent(s -> {
            throw new RuntimeException("DATA_DUPLICATED");
        });
        
        // 중요: setId(0L)를 삭제했습니다. 사용자가 보낸 ID가 그대로 저장됩니다.
        return Response.ok(stockRepository.save(stock));
    }

    @Transactional(readOnly = true)
    public Response getStockById(Long id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DATA_NOT_FOUND"));
        return Response.ok(stock);
    }
}