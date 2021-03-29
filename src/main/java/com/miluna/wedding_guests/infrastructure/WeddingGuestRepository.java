package com.miluna.wedding_guests.infrastructure;

import com.miluna.wedding_guests.domain.WeddingGuest;

public interface WeddingGuestRepository {
    void save(WeddingGuest weddingGuest);
}
