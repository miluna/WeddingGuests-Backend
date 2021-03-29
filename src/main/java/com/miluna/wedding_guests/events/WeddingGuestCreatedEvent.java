package com.miluna.wedding_guests.events;

import com.miluna.commons.events.DomainEvent;
import com.miluna.wedding_guests.responses.WeddingGuestResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class WeddingGuestCreatedEvent extends DomainEvent {
    private WeddingGuestResponse data;

    public WeddingGuestCreatedEvent(WeddingGuestResponse response) {
        super(response.getId());
        this.data = response;
    }
}
