package com.miluna.commons.domain;

import com.miluna.commons.events.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    protected List<DomainEvent> events = new ArrayList<>();

    public void record(DomainEvent event) {
        this.events.add(event);
    }

    public List<DomainEvent> pullEvents() {
        return this.events;
    }
}
