package br.com.programming.store.controller.dto;

public class OrderInfoDTO {

    private Long id;

    private Integer estimatedTime;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEstimatedTime() {
        return this.estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

}