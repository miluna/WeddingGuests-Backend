package com.miluna.wedding_guests.responses;

import com.miluna.wedding_guests.domain.WeddingGuest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeddingGuestResponse {

    private String id;
    private String name;
    private String email;
    private String telephone;

    public static WeddingGuestResponse fromAggregate(WeddingGuest weddingGuest) {
        return WeddingGuestResponse.builder()
                .id(weddingGuest.getWeddingGuestId().getValue())
                .name(weddingGuest.getName().getValue())
                .email(weddingGuest.getEmail().getValue())
                .telephone(weddingGuest.getTelephone().getValue())
                .build();
    }
}
