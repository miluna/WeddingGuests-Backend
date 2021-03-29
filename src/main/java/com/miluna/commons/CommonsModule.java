package com.miluna.commons;

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
}
