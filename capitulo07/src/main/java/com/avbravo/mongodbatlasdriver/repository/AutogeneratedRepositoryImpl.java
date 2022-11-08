package com.avbravo.mongodbatlasdriver.repository;
// <editor-fold defaultstate="collapsed" desc="imports">
import com.avbravo.mongodbatlasdriver.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.mongodb.client.MongoDatabase;;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import com.jmoordb.core.model.Autosequence;
import com.jmoordb.core.util.MessagesUtil;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import com.jmoordb.core.model.Autosequence;
import com.jmoordb.core.util.MessagesUtil;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import com.jmoordb.core.model.Autosequence;
import com.jmoordb.core.util.MessagesUtil;
// </editor-fold>
@ApplicationScoped


public class  AutogeneratedRepositoryImpl  implements AutogeneratedRepository {
// <editor-fold defaultstate="collapsed" desc="inject">
   @Inject 
   MongoClient mongoClient;
/*
   Microprofile Config
*/
   @Inject 
   Config config;
   @Inject 
   @ConfigProperty(name = "mongodb.database")
   String mongodbDatabase;

   String mongodbCollection = "autoincrementable";

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Long generate(String database, String collection)">

   @Override
   public Long generate(String database, String collection) {
       try {
             Optional<Autosequence> autosequenceOptional = findById(database + "_" + collection);
             if (!autosequenceOptional.isPresent()) {
                  Long l = Long.valueOf("0");
                Autosequence autosequence = new Autosequence(database + "_" + collection, l);
                //Autosequence autosequence = new Autosequence(database + "_" + collection, 0L);
                save(autosequence);
             }
             Optional<Autosequence> autosequenceIncrementOptional = findOneAndUpdate(database + "_" + collection);
             if (!autosequenceIncrementOptional.isPresent()) {
                return -1L;
             } else {
                return autosequenceIncrementOptional.get().getSecuence();
             }
       } catch (Exception e) {
         MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
       }
      return -1L;
   }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Optional<Autosequence> findOneAndUpdate(String databasecollection)">

   public Optional<Autosequence> findOneAndUpdate(String databasecollection) {
       try {
             Long  increment = new Long(1);            // Integer increment = 1;
             Document doc = new Document("databasecollection", databasecollection);
             Document inc = new Document("$inc", new Document("sequence", increment));
             FindOneAndUpdateOptions findOneAndUpdateOptions = new FindOneAndUpdateOptions();
             findOneAndUpdateOptions.upsert(true);
             findOneAndUpdateOptions.returnDocument(ReturnDocument.AFTER);
             MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
             MongoCollection<Document> collection = database.getCollection(mongodbCollection);
             Document iterable = collection.findOneAndUpdate(doc, inc, findOneAndUpdateOptions);

             Autosequence autosequence = new Autosequence(databasecollection, iterable.getLong("sequence"));
            //Autosequence autosequence = get(Autosequence::new, iterable);
             return Optional.of(autosequence);
       } catch (Exception e) {
         MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
       }
      return Optional.empty();
   }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Autosequence get(Supplier<? extends Autosequence> s, Document document)">

   public Autosequence get(Supplier<? extends Autosequence> s, Document document) {
      Autosequence autosequence = s.get();
       try {
             autosequence.setSecuence(document.getLong("secuence"));
             autosequence.setDatabasecollection(document.getString("databasecollection"));
       } catch (Exception e) {
         MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
       }
      return autosequence;
   }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Optional<Autosequence> save(Autosequence autosequence)">

   public Optional<Autosequence> save(Autosequence autosequence) {
       try {
             MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
             MongoCollection<Document> collection = database.getCollection(mongodbCollection);
             InsertOneResult insertOneResult = collection.insertOne(Document.parse(autosequence.toJson(autosequence)));
             if (insertOneResult.getInsertedId() != null) {
                return Optional.of(autosequence);
             }
       } catch (Exception e) {
         MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
       }
      return Optional.empty();
   }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Optional<Autosequence> findById(String databasecollection)">

   public Optional<Autosequence> findById(String databasecollection) {
       try {
             MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
             MongoCollection<Document> collection = database.getCollection(mongodbCollection);
             Document doc = collection.find(eq("databasecollection", databasecollection)).first();
             Autosequence autosequence = get(Autosequence::new, doc);
             return Optional.of(autosequence);
       } catch (Exception e) {
         MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
       }
      return Optional.empty();
   }
// </editor-fold>

}