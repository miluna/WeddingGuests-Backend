package com.miluna.commons.events;

import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
public abstract class DomainEvent {
    protected String id;
    protected Timestamp date;
    protected String author;

    public DomainEvent(String id) {
        this.id = id;
        this.date = new Timestamp(System.currentTimeMillis());
        this.author = null;
    }

    public DomainEvent(String id, String author) {
        this.id = id;
        this.date = new Timestamp(System.currentTimeMillis());
        this.author = author;
    }
}
