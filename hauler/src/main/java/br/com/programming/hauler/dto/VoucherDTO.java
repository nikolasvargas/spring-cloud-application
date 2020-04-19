package br.com.programming.hauler.dto;

import java.time.LocalDate;

public class VoucherDTO {

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

    public void setForecastDate(LocalDate expectedDelivery) {
        this.expectedDelivery= expectedDelivery;
    }
}
