package com.miluna.wedding_guests.infrastructure;

import com.miluna.wedding_guests.domain.WeddingGuest;
import com.miluna.wedding_guests.domain.WeddingGuestEmail;

import java.util.Optional;

public interface WeddingGuestRepository {
    void save(WeddingGuest weddingGuest);
    Optional<WeddingGuest> findByEmail(WeddingGuestEmail email);
}
