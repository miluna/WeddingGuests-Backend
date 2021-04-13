package com.miluna.wedding_guests.infrastructure.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.miluna.wedding_guests.domain.*;
import com.miluna.wedding_guests.infrastructure.WeddingGuestRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DynamoDbWeddingGuestRepository implements WeddingGuestRepository {

    private final AmazonDynamoDB amazonDynamoDB;

    public DynamoDbWeddingGuestRepository(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
    }

    @Override
    public void save(WeddingGuest weddingGuest) {
        Map<String, AttributeValue> attributes = this.mapDomain(weddingGuest);
        PutItemRequest request = new PutItemRequest("wedding_guests", attributes);
        amazonDynamoDB.putItem(request);
    }

    @Override
    public Optional<WeddingGuest> findByEmail(WeddingGuestEmail email) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("email", new AttributeValue(email.getValue()));
        GetItemResult result = amazonDynamoDB.getItem("wedding_guests", attributes);
        return Optional.ofNullable(this.mapItem(result.getItem()));
    }

    private WeddingGuest mapItem(Map<String, AttributeValue> item) {
        return WeddingGuest.builder()
                .weddingGuestId(new WeddingGuestId(item.get("weddingGuestId").getS()))
                .name(new WeddingGuestName(item.get("name").getS()))
                .email(new WeddingGuestEmail(item.get("email").getS()))
                .telephone(new WeddingGuestTelephone(item.get("telephone").getS()))
                .build();
    }

    private Map<String, AttributeValue> mapDomain(WeddingGuest source) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put("weddingGuestId", new AttributeValue(source.getWeddingGuestId().getValue()));
        attributes.put("name", new AttributeValue(source.getName().getValue()));
        attributes.put("email", new AttributeValue(source.getEmail().getValue()));
        attributes.put("telephone", new AttributeValue(source.getTelephone().getValue()));
        return attributes;
    }
}
