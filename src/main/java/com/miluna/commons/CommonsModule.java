package com.miluna.commons;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.miluna.commons.infrastructure.AwsSnsBus;
import com.miluna.commons.infrastructure.EventBus;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CommonsModule {

    @Provides
    @Singleton
    public EventBus eventBus() {
        return new AwsSnsBus();
    }

    @Provides
    @Singleton
    public AmazonDynamoDB dynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .build();
    }
}
