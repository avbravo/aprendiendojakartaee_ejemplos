package com.avbravo.mongodbatlasdriver.repository;
// <editor-fold defaultstate="collapsed" desc="imports">

import com.avbravo.mongodbatlasdriver.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
/**
* MongoDB
*/
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;
import org.bson.BsonDocument;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
/**
* Java
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.function.Supplier;
import com.jmoordb.core.util.MessagesUtil;
import com.jmoordb.core.model.Pagination;
import com.jmoordb.core.model.Sorted;
import com.jmoordb.core.util.JmoordbCoreDateUtil;
import java.util.HashSet;
import com.avbravo.mongodbatlasdriver.model.Planeta;


// </editor-fold>
@ApplicationScoped
public class PlanetaRepositoryImpl  implements PlanetaRepository{
// <editor-fold defaultstate="collapsed" desc="inject">

    @Inject
    MongoClient mongoClient;
/**
* Microprofile Config
*/
    @Inject
    Config config;
    @Inject
    @ConfigProperty(name = "mongodb.database")
    String mongodbDatabase;

    String mongodbCollection = "planeta";
/**
* AutogeneratedRepository
*/
    @Inject
    com.avbravo.mongodbatlasdriver.repository.AutogeneratedRepository autogeneratedRepository;
/**
* Supplier
*/
    @Inject
    com.avbravo.mongodbatlasdriver.model.PlanetaSupplier planetaSupplier;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Optional<Planeta> save(Planeta planeta)">

    @Override
    public Optional<Planeta> save(Planeta planeta) {
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               planeta.setIdplaneta(autogeneratedRepository.generate(mongodbDatabase, mongodbCollection));
               if (findByPk(planeta.getIdplaneta()).isPresent()) { 
                   MessagesUtil.warning("There is already a record with that id");
                  return Optional.of(planeta);
               }
               InsertOneResult insertOneResult = collection.insertOne(planetaSupplier.toDocument(planeta));
               if (insertOneResult.getInsertedId() != null) {
                  return Optional.of(planeta);
               }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Boolean update(Planeta planeta)">

    @Override
    public Boolean update(Planeta planeta) {
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               if (!findByPk(planeta.getIdplaneta()).isPresent()) { 
                   MessagesUtil.warning("Not found a record with that id");
                    return Boolean.FALSE;
               }
               Bson filter = Filters.empty();
               filter = Filters.eq("idplaneta",planeta.getIdplaneta());
               //Jsonb jsonb = JsonbBuilder.create();
               //UpdateResult result = collection.updateOne(filter, Document.parse(jsonb.toJson(planeta)));
               //UpdateResult result = collection.updateOne(filter, Document.parse(planetaSupplier.toJson(planeta)));
               UpdateResult result = collection.updateOne(filter,planetaSupplier.toDocument(planeta));
               if (result.getModifiedCount() > 0) {
                  return Boolean.TRUE;
               }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return Boolean.FALSE;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="public List<Planeta> findAllPaginationSorted(Pagination pagination, Sorted sorted)">

    @Override
    public List<Planeta> findAllPaginationSorted(Pagination pagination, Sorted sorted) {
        List<Planeta> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               		cursor = collection.find()
					.skip(pagination.skip())
			.limit(pagination.limit())
			.sort(sorted.getSort())
		.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(planetaSupplier.get(Planeta::new, cursor.next()));
                  }
               } finally {
                     cursor.close();
               } 
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return list;

     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="public List<Planeta> findAll()">

    @Override
    public List<Planeta> findAll() {
        List<Planeta> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               		cursor = collection.find()
				.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(planetaSupplier.get(Planeta::new, cursor.next()));
                  }
               } finally {
                     cursor.close();
               } 
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return list;

     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="public List<Planeta> findAllPagination(Pagination pagination)">

    @Override
    public List<Planeta> findAllPagination(Pagination pagination) {
        List<Planeta> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               		cursor = collection.find()
					.skip(pagination.skip())
			.limit(pagination.limit())
		.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(planetaSupplier.get(Planeta::new, cursor.next()));
                  }
               } finally {
                     cursor.close();
               } 
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return list;

     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="public List<Planeta> findAllSorted(Sorted sorted)">

    @Override
    public List<Planeta>  findAllSorted(Sorted sorted) {
        List<Planeta> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               		cursor = collection.find()
					.sort(sorted.getSort())
		.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(planetaSupplier.get(Planeta::new, cursor.next()));
                  }
               } finally {
                     cursor.close();
               } 
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return list;

     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="public Optional<Planeta> findByPk(Long id )">

    public Optional<Planeta> findByPk(Long id ) {
        try {
            MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
            MongoCollection<Document> collection = database.getCollection(mongodbCollection);
            Document doc = collection.find(eq("idplaneta", id)).first();
            if(doc == null){
               return Optional.empty();
            }
            Planeta planeta = planetaSupplier.get(Planeta::new, doc);
            return Optional.of(planeta);
       } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
       }
       return Optional.empty();
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Long deleteByPK(Long id )">

    @Override
    public Long deleteByPk(Long id){
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter = Filters.eq("idplaneta",id);

		com.mongodb.client.result.DeleteResult deleteResult = collection.deleteOne(filter);

               return deleteResult.getDeletedCount();
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return 0L;
     }
// </editor-fold>

}