package com.miluna.wedding_guests.application.create;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.miluna.commons.responses.ErrorResponse;
import com.miluna.commons.responses.LambdaResponse;
import com.miluna.wedding_guests.DaggerWeddingGuestsComponent;
import com.miluna.wedding_guests.WeddingGuestsComponent;
import com.miluna.wedding_guests.application.finder.WeddingGuestFinder;
import com.miluna.wedding_guests.domain.WeddingGuestEmail;
import com.miluna.wedding_guests.domain.WeddingGuestId;
import com.miluna.wedding_guests.domain.WeddingGuestName;
import com.miluna.wedding_guests.domain.WeddingGuestTelephone;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class CreateWeddingGuestHandler implements RequestHandler<CreateWeddingGuestRequest, LambdaResponse> {

    private final WeddingGuestCreator creator;
    private final WeddingGuestFinder finder;

    public CreateWeddingGuestHandler() {
        WeddingGuestsComponent build = DaggerWeddingGuestsComponent.builder().build();
        this.creator = build.creator();
        this.finder = build.finder();
    }

    @Override
    public LambdaResponse handleRequest(CreateWeddingGuestRequest request, Context context) {
        AtomicReference<LambdaResponse> response = new AtomicReference<>();

        WeddingGuestId id = new WeddingGuestId(UUID.randomUUID().toString());
        WeddingGuestName name = new WeddingGuestName(request.getName());
        WeddingGuestEmail email = new WeddingGuestEmail(request.getEmail());
        WeddingGuestTelephone phone = new WeddingGuestTelephone(request.getTelephone());

        finder.findByEmail(email)
                .ifPresentOrElse((e) -> {
                    response.set(new LambdaResponse(400, new ErrorResponse("EMAIL_EXISTS"), null));
                }, () -> {
                    creator.create(id, name, email, phone);
                    response.set(new LambdaResponse(201, null, null));
                });
        return response.get();
    }
}
