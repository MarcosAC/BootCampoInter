package com.pokedex.apipokedex.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import static com.pokedex.apipokedex.constants.PokedexConstants.ENDPOINT_DYNAMO;
import static com.pokedex.apipokedex.constants.PokedexConstants.REGION_DYNAMO;

public class PokedexData {
    public static void main(String[] args) throws Exception{
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Pokedex");

        Item pokemon1 = new Item()
                .withPrimaryKey("id", "1")
                .withString("name", "Pikachu")
                .withString("type", "Electric")
                .withString("category", "Mouse");

        Item pokemon2 = new Item()
                .withPrimaryKey("id", "2")
                .withString("name", "Bulbasaur")
                .withString("type", "Grass Poison")
                .withString("category", "Seed");

        Item pokemon3 = new Item()
                .withPrimaryKey("id", "3")
                .withString("name", "Squirtle")
                .withString("type", "Water")
                .withString("category", "Tyne Turtle");

        Item pokemon4 = new Item()
                .withPrimaryKey("id", "4")
                .withString("name", "Charmander")
                .withString("type", "Fire")
                .withString("category", "Lizard");

        PutItemOutcome outcome1 = table.putItem(pokemon1);
        PutItemOutcome outcome2 = table.putItem(pokemon2);
        PutItemOutcome outcome3 = table.putItem(pokemon3);
        PutItemOutcome outcome4 = table.putItem(pokemon4);
    }
}
