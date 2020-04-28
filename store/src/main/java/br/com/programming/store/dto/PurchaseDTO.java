package br.com.programming.store.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PurchaseDTO {
    private List<PurchaseItemDTO> items;
    private AddressDTO address;

    @JsonIgnore
    private Long purchaseId;

    public List<PurchaseItemDTO> getItems() {
        return items;
    }
    public void setItems(List<PurchaseItemDTO> items) {
        this.items = items;
    }
    public AddressDTO getAddress() {
        return address;
    }
    public void setAddress(AddressDTO address) {
        this.address = address;
    }
    public Long getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
}
