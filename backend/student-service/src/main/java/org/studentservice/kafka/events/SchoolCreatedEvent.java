package org.studentservice.kafka.events;

public class SchoolCreatedEvent {
    private String tenentId;

    public SchoolCreatedEvent() {}

    public String getTenentId() {
        return tenentId;
    }

    public void setTenentId(String tenentId) {
        this.tenentId = tenentId;
    }
}
