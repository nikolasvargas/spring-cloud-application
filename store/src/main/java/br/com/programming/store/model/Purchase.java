package br.com.programming.store.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Purchase {

    @Id
    private Long orderId;

    private Integer estimatedTime;

    private String fromAddress;

    public Purchase() {}

    public Purchase(Long id, Integer estimatedTime, String fromAddress) {
        this.orderId = id;
        this.estimatedTime = estimatedTime;
        this.fromAddress = fromAddress;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getEstimatedTime() {
        return this.estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
}
