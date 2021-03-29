package com.miluna.wedding_guests.domain;

import com.miluna.commons.value_objects.StringValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeddingGuestName extends StringValueObject {
    public WeddingGuestName(String value) {
        super(value);
    }

    public WeddingGuestName() {
    }
}
