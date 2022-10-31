package com.jmoordbcore.capitulo13.supplier;
// <editor-fold defaultstate="collapsed" desc="imports">

import com.jmoordbcore.capitulo13.repository.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
import com.mongodb.client.result.InsertOneResult;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
/**
* Java
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.jmoordb.core.util.MessagesUtil;
import com.jmoordb.core.model.Pagination;
import com.jmoordb.core.model.Sorted;
import com.jmoordbcore.capitulo13.model.Persona;


// </editor-fold>
@ApplicationScoped
public class PersonaRepositoryImpl  implements PersonaRepository{
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

    String mongodbCollection = "persona";
/**
* AutogeneratedRepository
*/
    @Inject
AutogeneratedRepository autogeneratedRepository;
/**
* Supplier
*/
    @Inject
PersonaSupplier personaSupplier;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Persona> findByNombre(java.lang.String nombre ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Persona> findByNombre(java.lang.String nombre) {
        List<Persona> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.eq("nombre",nombre);


    System.out.println("Quitar [ filter is ]: "+filter.toBsonDocument().toJson());

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(personaSupplier.get(Persona::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc=" public Optional<Persona> save(Persona persona)">

    @Override
    public Optional<Persona> save(Persona persona) {
        try {
            System.out.println("------ [PersonaRepositoryImpl.java] -------");
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               persona.setIdpersona(autogeneratedRepository.generate(mongodbDatabase, mongodbCollection));
               if (findByPk(persona.getIdpersona()).isPresent()) { 
                   MessagesUtil.warning("There is already a record with that id");
                  return Optional.of(persona);
               }
               InsertOneResult insertOneResult = collection.insertOne(personaSupplier.toDocument(persona));
               if (insertOneResult.getInsertedId() != null) {
                  return Optional.of(persona);
               }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Boolean update(Persona persona)">

    @Override
    public Boolean update(Persona persona) {
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               if (!findByPk(persona.getIdpersona()).isPresent()) { 
                   MessagesUtil.warning("Not found a record with that id");
                    return Boolean.FALSE;
               }
               Bson filter = Filters.empty();
               filter = Filters.eq("idpersona",persona.getIdpersona());
               //Jsonb jsonb = JsonbBuilder.create();
               //UpdateResult result = collection.updateOne(filter, Document.parse(jsonb.toJson(persona)));
               //UpdateResult result = collection.updateOne(filter, Document.parse(personaSupplier.toJson(persona)));
               UpdateResult result = collection.updateOne(filter,personaSupplier.toDocument(persona));
               if (result.getModifiedCount() > 0) {
                  return Boolean.TRUE;
               }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return Boolean.FALSE;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="public List<Persona> findAllPaginationSorted(Pagination pagination, Sorted sorted)">

    @Override
    public List<Persona> findAllPaginationSorted(Pagination pagination, Sorted sorted) {
        List<Persona> list = new ArrayList<>();
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
                        list.add(personaSupplier.get(Persona::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="public List<Persona> findAll()">

    @Override
    public List<Persona> findAll() {
        List<Persona> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               		cursor = collection.find()
				.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(personaSupplier.get(Persona::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="public List<Persona> findAllPagination(Pagination pagination)">

    @Override
    public List<Persona> findAllPagination(Pagination pagination) {
        List<Persona> list = new ArrayList<>();
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
                        list.add(personaSupplier.get(Persona::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="public List<Persona> findAllSorted(Sorted sorted)">

    @Override
    public List<Persona>  findAllSorted(Sorted sorted) {
        List<Persona> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               		cursor = collection.find()
					.sort(sorted.getSort())
		.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(personaSupplier.get(Persona::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="public Optional<Persona> findByPk(Long id )">

    public Optional<Persona> findByPk(Long id ) {
        try {
            MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
            MongoCollection<Document> collection = database.getCollection(mongodbCollection);
            Document doc = collection.find(eq("idpersona", id)).first();
            if(doc == null){
               return Optional.empty();
            }
            Persona persona = personaSupplier.get(Persona::new, doc);
            return Optional.of(persona);
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
               Bson filter = Filters.eq("idpersona",id);

		com.mongodb.client.result.DeleteResult deleteResult = collection.deleteOne(filter);

               return deleteResult.getDeletedCount();
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return 0L;
     }
// </editor-fold>

}