package br.com.programming.store.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long orderId;

    private Long voucherId;

    private Integer estimatedTime;

    private String fromAddress;

    private String providerAddress;

    @Enumerated(EnumType.STRING)
    private PurchaseState state;

    public Purchase() {}

    public Purchase(Long id, Long voucherId, Integer estimatedTime, String fromAddress, String providerAddress) {
        this.orderId = id;
        this.voucherId = voucherId;
        this.estimatedTime = estimatedTime;
        this.fromAddress = fromAddress;
        this.providerAddress = providerAddress;
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getVoucherId() {
        return this.voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
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

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public PurchaseState getState() {
        return state;
    }

    public void setState(PurchaseState state) {
        this.state = state;
    }
}
