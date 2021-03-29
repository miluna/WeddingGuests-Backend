package com.miluna.wedding_guests.application.create;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.miluna.commons.responses.LambdaResponse;
import com.miluna.wedding_guests.DaggerWeddingGuestsComponent;
import com.miluna.wedding_guests.domain.WeddingGuestEmail;
import com.miluna.wedding_guests.domain.WeddingGuestId;
import com.miluna.wedding_guests.domain.WeddingGuestName;
import com.miluna.wedding_guests.domain.WeddingGuestTelephone;

import java.util.List;
import java.util.UUID;

public class BatchCreateWeddingGuestHandler implements RequestHandler<List<CreateWeddingGuestRequest>, LambdaResponse> {

    private final WeddingGuestCreator creator;

    public BatchCreateWeddingGuestHandler() {
        this.creator = DaggerWeddingGuestsComponent.builder().build().creator();
    }

    @Override
    public LambdaResponse handleRequest(List<CreateWeddingGuestRequest> request, Context context) {
        request.forEach(g -> {
            creator.create(
                    new WeddingGuestId(UUID.randomUUID().toString()),
                    new WeddingGuestName(g.getName()),
                    new WeddingGuestEmail(g.getEmail()),
                    new WeddingGuestTelephone(g.getTelephone())
            );
        });
        return new LambdaResponse(201, null, null);
    }
}
