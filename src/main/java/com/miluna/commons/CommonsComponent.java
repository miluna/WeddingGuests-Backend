package com.miluna.commons;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.miluna.commons.infrastructure.EventBus;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { CommonsModule.class })
public interface CommonsComponent {
    EventBus eventbus();
    AmazonDynamoDB dynamoDb();
}
