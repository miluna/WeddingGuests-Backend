package com.miluna.wedding_guests.application.finder;

import com.miluna.wedding_guests.domain.WeddingGuest;
import com.miluna.wedding_guests.domain.WeddingGuestEmail;
import com.miluna.wedding_guests.infrastructure.WeddingGuestRepository;

import javax.inject.Inject;
import java.util.Optional;

public class WeddingGuestFinder {

    private final WeddingGuestRepository repository;

    @Inject
    public WeddingGuestFinder(WeddingGuestRepository repository) {
        this.repository = repository;
    }

    public Optional<WeddingGuest> findByEmail(WeddingGuestEmail email) {
        return repository.findByEmail(email);
    }
}
