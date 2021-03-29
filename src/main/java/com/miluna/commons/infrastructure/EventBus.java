package com.miluna.commons.infrastructure;

import com.miluna.commons.events.DomainEvent;

import java.util.List;

public interface EventBus {
    void publish(List<DomainEvent> events);
}
