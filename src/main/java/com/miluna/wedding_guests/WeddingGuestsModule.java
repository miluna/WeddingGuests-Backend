package com.miluna.wedding_guests;

import com.miluna.commons.CommonsComponent;
import com.miluna.commons.DaggerCommonsComponent;
import com.miluna.commons.infrastructure.EventBus;
import com.miluna.wedding_guests.application.create.WeddingGuestCreator;
import com.miluna.wedding_guests.infrastructure.WeddingGuestRepository;
import com.miluna.wedding_guests.infrastructure.dynamodb.DynamoDbWeddingGuestRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class WeddingGuestsModule {

    @Provides
    @Singleton
    public WeddingGuestRepository repository() {
        return new DynamoDbWeddingGuestRepository();
    }

    @Provides
    @Singleton
    public WeddingGuestCreator creator(WeddingGuestRepository repository) {
        EventBus eventBus = DaggerCommonsComponent.builder().build().eventbus();
        return new WeddingGuestCreator(repository, eventBus);
    }

}
