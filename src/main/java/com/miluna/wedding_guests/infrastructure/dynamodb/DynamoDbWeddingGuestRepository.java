package com.miluna.wedding_guests.infrastructure.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.miluna.wedding_guests.domain.*;
import com.miluna.wedding_guests.infrastructure.WeddingGuestRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DynamoDbWeddingGuestRepository implements WeddingGuestRepository {

    private final AmazonDynamoDB amazonDynamoDB;
    private final Table table;

    public DynamoDbWeddingGuestRepository(AmazonDynamoDB amazonDynamoDB) {
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        this.table = dynamoDB.getTable("wedding_guests");
        this.amazonDynamoDB = amazonDynamoDB;
    }

    @Override
    public void save(WeddingGuest weddingGuest) {
        Item item = this.mapDomain(weddingGuest);
        table.putItem(item);
    }

    @Override
    public Optional<WeddingGuest> findByEmail(WeddingGuestEmail email) {
        Optional<Item> result = Optional.empty();


        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put(":val", new AttributeValue().withS(email.getValue()));
        ScanRequest request = new ScanRequest()
                .withTableName("wedding_guests")
                .withFilterExpression("email = :val")
                .withExpressionAttributeValues(attributes)
                .withLimit(1);
        ScanResult scanResult = amazonDynamoDB.scan(request);

        if (scanResult.getItems().isEmpty()) return Optional.empty();
        Optional<Map<String, AttributeValue>> first = scanResult.getItems().stream().findFirst();
        return first.map(this::mapItem);
    }

    private WeddingGuest mapItem(Map<String, AttributeValue> map) {
        return WeddingGuest.builder()
                .weddingGuestId(new WeddingGuestId(map.get("weddingGuestId").getS()))
                .name(new WeddingGuestName(map.get("name").getS()))
                .email(new WeddingGuestEmail(map.get("email").getS()))
                .telephone(new WeddingGuestTelephone(map.get("telephone").getS()))
                .build();
    }

    private Item mapDomain(WeddingGuest source) {
        return new Item()
                .withPrimaryKey("weddingGuestId", source.getWeddingGuestId().getValue())
                .withString("name", source.getName().getValue())
                .withString("email", source.getEmail().getValue())
                .withString("telephone", source.getTelephone().getValue());
    }
}
