package br.com.programming.store.dto;

import java.time.LocalDate;

public class VoucherInfoDTO {
    private Long number;

    private LocalDate expectedDelivery;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }
}
