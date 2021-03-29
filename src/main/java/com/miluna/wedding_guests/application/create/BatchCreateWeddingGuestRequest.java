package com.miluna.wedding_guests.application.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchCreateWeddingGuestRequest {
    private String name;
    private String email;
    private String telephone;
}
