package com.miluna.wedding_guests.domain;

import com.miluna.commons.value_objects.StringValueObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeddingGuestId extends StringValueObject {
    public WeddingGuestId(String value) {
        super(value);
    }

    public WeddingGuestId() {
    }
}
