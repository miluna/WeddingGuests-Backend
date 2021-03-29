package com.miluna.wedding_guests;

import com.miluna.wedding_guests.application.create.WeddingGuestCreator;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { WeddingGuestsModule.class })
public interface WeddingGuestsComponent {
    WeddingGuestCreator creator();
}
