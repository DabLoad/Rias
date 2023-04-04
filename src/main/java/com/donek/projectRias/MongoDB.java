package com.donek.projectRias;
import com.mongodb.*;
import com.mongodb.client.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.bson.Document;

public class MongoDB {
    static ConnectionString connectionString = new ConnectionString("db data");
    static MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    public static MongoClient mongoClient = MongoClients.create(settings);
    public static MongoDatabase database = mongoClient.getDatabase("VerifRoleDB");

    public static void main(String[] args) {
        MongoCollection<Document> collection = MongoDB.database.getCollection("VerifRoleId");

        Document searchQuery = new Document();
        searchQuery.put("GuildId", 123);
        FindIterable<Document> cursor = collection.find(searchQuery);
        try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
            while (cursorIterator.hasNext()) {
                System.out.println(cursorIterator.next().get("RoleId"));
            }
        }
    }
    public static String getCurrentRole(ButtonInteractionEvent event) {
        MongoCollection<Document> collection = MongoDB.database.getCollection("VerifRoleId");
        Document searchQuery = new Document();
        searchQuery.put("GuildId", event.getGuild().getId());
        FindIterable<Document> cursor = collection.find(searchQuery);
        try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
            while (cursorIterator.hasNext()) {
                return (String) cursorIterator.next().get("RoleId");
            }
        }
        return null;
    }
    public static void addRole(SlashCommandInteractionEvent event){
        MongoCollection<Document> collection = MongoDB.database.getCollection("VerifRoleId");
        String currentRole = null;
        Document searchQuery = new Document();
        searchQuery.put("GuildId", event.getGuild().getId());
        FindIterable<Document> cursor = collection.find(searchQuery);
        try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
            while (cursorIterator.hasNext()) {
                currentRole = (String) cursorIterator.next().get("RoleId");
            }
        }
        if (currentRole!=null){
            Document query = new Document();
            query.put("RoleId", currentRole);

            Document newDocument = new Document();
            newDocument.put("RoleId", event.getOption("ver_role").getAsRole().getId());

            Document updateObject = new Document();
            updateObject.put("$set", newDocument);

            collection.updateOne(query, updateObject);
        } else {
            Document document = new Document();
            document.put("GuildId", event.getGuild().getId());
            document.put("RoleId", event.getOption("ver_role").getAsRole().getId());
            collection.insertOne(document);
        }
    }
}
