package com.miluna.wedding_guests.domain;

import com.miluna.commons.domain.AggregateRoot;
import com.miluna.wedding_guests.events.WeddingGuestCreatedEvent;
import com.miluna.wedding_guests.responses.WeddingGuestResponse;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeddingGuest extends AggregateRoot {
    private WeddingGuestId weddingGuestId;
    private WeddingGuestName name;
    private WeddingGuestEmail email;
    private WeddingGuestTelephone telephone;

    public static WeddingGuest create(WeddingGuestId weddingGuestId,
                              WeddingGuestName name,
                              WeddingGuestEmail email,
                              WeddingGuestTelephone telephone) {
        WeddingGuest weddingGuest = WeddingGuest.builder()
                .weddingGuestId(weddingGuestId)
                .name(name)
                .email(email)
                .telephone(telephone)
                .build();
        WeddingGuestResponse response = WeddingGuestResponse.fromAggregate(weddingGuest);
        WeddingGuestCreatedEvent event = new WeddingGuestCreatedEvent(response);
        weddingGuest.record(event);
        return weddingGuest;
    }
}
