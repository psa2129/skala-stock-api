package com.sk.skala.stockapi.data.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StockOrder {
    @NotBlank(message = "플레이어 ID는 필수입니다.")
    private String playerId;

    @NotNull(message = "주식 ID는 필수입니다.")
    private Long stockId;

    @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
    private int stockQuantity;

    public String getPlayerId() { return playerId; }
    public void setPlayerId(String playerId) { this.playerId = playerId; }

    public Long getStockId() { return stockId; }
    public void setStockId(Long stockId) { this.stockId = stockId; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    // ------------------------------------------
}