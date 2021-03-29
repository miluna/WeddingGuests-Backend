package com.miluna.commons.infrastructure;

import com.miluna.commons.events.DomainEvent;

import java.util.List;


public class AwsSnsBus implements EventBus {
    @Override
    public void publish(List<DomainEvent> events) {
        // TODO: should publish the event data to SNS here
    }
}
