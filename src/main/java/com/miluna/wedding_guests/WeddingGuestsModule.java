package com.miluna.wedding_guests;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.miluna.commons.CommonsComponent;
import com.miluna.commons.DaggerCommonsComponent;
import com.miluna.commons.infrastructure.EventBus;
import com.miluna.wedding_guests.application.create.WeddingGuestCreator;
import com.miluna.wedding_guests.application.finder.WeddingGuestFinder;
import com.miluna.wedding_guests.infrastructure.WeddingGuestRepository;
import com.miluna.wedding_guests.infrastructure.dynamodb.DynamoDbWeddingGuestRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class WeddingGuestsModule {

    private final CommonsComponent commonsComponent = DaggerCommonsComponent.builder().build();

    @Provides
    @Singleton
    public WeddingGuestRepository repository() {
        AmazonDynamoDB amazonDynamoDB = commonsComponent.dynamoDb();
        return new DynamoDbWeddingGuestRepository(amazonDynamoDB);
    }

    @Provides
    @Singleton
    public WeddingGuestCreator creator(WeddingGuestRepository repository) {
        EventBus eventBus = commonsComponent.eventbus();
        return new WeddingGuestCreator(repository, eventBus);
    }

    @Provides
    @Singleton
    public WeddingGuestFinder finder(WeddingGuestRepository repository) {
        return new WeddingGuestFinder(repository);
    }

}
