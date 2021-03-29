package com.miluna.wedding_guests.application.create;

import com.miluna.commons.infrastructure.EventBus;
import com.miluna.wedding_guests.domain.*;
import com.miluna.wedding_guests.infrastructure.WeddingGuestRepository;

import javax.inject.Inject;

public class WeddingGuestCreator {

    private final WeddingGuestRepository repository;
    private final EventBus eventBus;

    @Inject
    public WeddingGuestCreator(WeddingGuestRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(WeddingGuestId weddingGuestId,
                       WeddingGuestName name,
                       WeddingGuestEmail email,
                       WeddingGuestTelephone telephone) {
        WeddingGuest weddingGuest = WeddingGuest.create(weddingGuestId, name, email, telephone);
        this.repository.save(weddingGuest);
        this.eventBus.publish(weddingGuest.pullEvents());
    }
}
