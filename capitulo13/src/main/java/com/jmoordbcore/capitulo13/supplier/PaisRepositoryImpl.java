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
import com.jmoordbcore.capitulo13.model.Pais;


// </editor-fold>
@ApplicationScoped
public class PaisRepositoryImpl  implements PaisRepository{
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

    String mongodbCollection = "pais";
/**
* AutogeneratedRepository
*/
    @Inject
  AutogeneratedRepository autogeneratedRepository;
/**
* Supplier
*/
    @Inject
  PaisSupplier paisSupplier;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="java.util.stream.Stream<com.jmoordbcore.capitulo13.model.Pais> findByNombreAndApellidoAndEdadLessThanEquals(java.lang.String nombre,java.lang.String apellido,java.lang.Integer edad ) ">

    @Override
    public java.util.stream.Stream<com.jmoordbcore.capitulo13.model.Pais> findByNombreAndApellidoAndEdadLessThanEquals(java.lang.String nombre,java.lang.String apellido,java.lang.Integer edad) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.and(
			Filters.eq("nombre",nombre)
			,Filters.eq("apellido",apellido)
			,Filters.and(
			Filters.lte("edad",edad)
			)
		);


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
                  }
               } finally {
                     cursor.close();
               } 
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return list.stream();

     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> queryByFecha(java.util.Date fecha ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> queryByFecha(java.util.Date fecha) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.eq("fecha",fecha);

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByIdpaisGreaterThanPagination(java.lang.Long idpais,com.jmoordb.core.model.Pagination pagination ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByIdpaisGreaterThanPagination(java.lang.Long idpais,com.jmoordb.core.model.Pagination pagination) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.gt("idpais",idpais);


    

		cursor = collection.find( filter )
					.skip(pagination.skip())
			.limit(pagination.limit())
			.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFecha(java.util.Date fecha ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFecha(java.util.Date fecha) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.eq("fecha",fecha);


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFecha(java.time.LocalDateTime fecha ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFecha(java.time.LocalDateTime fecha) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.and(
Filters.gte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeFirstHourOfDay(fecha)),
Filters.lte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeLastHourOfDay(fecha))
);


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThan(java.util.Date fecha ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThan(java.util.Date fecha) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.gt("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeNextDayFirstHourOfDay(fecha));


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThanEquals(java.util.Date fecha ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThanEquals(java.util.Date fecha) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.gte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeFirstHourOfDay(fecha));


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaLessThan(java.util.Date fecha ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaLessThan(java.util.Date fecha) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.lt("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeBeforeFirstHourOfDay(fecha));


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaLessThanEquals(java.util.Date fecha ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaLessThanEquals(java.util.Date fecha) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.lte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeLastHourOfDay(fecha));


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.Optional<com.jmoordbcore.capitulo13.model.Pais> findByFechaAndPais(java.util.Date fecha,java.lang.String pais ) ">

    @Override
    public java.util.Optional<com.jmoordbcore.capitulo13.model.Pais> findByFechaAndPais(java.util.Date fecha,java.lang.String pais) {

        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);

               Bson filter =Filters.and(
Filters.and(
			Filters.gte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeFirstHourOfDay(fecha)),
			Filters.lte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeLastHourOfDay(fecha))
)
			,Filters.eq("pais",pais)
		);


    

		Document doc = collection.find( filter )
					.first();





            Pais pais_ = paisSupplier.get(Pais::new, doc);
            return Optional.of(pais_);
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();

     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaLessThanAndPais(java.util.Date fecha,java.lang.String pais ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaLessThanAndPais(java.util.Date fecha,java.lang.String pais) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.and(
			Filters.lt("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeBeforeFirstHourOfDay(fecha))
			,Filters.eq("pais",pais)
		);


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByPaisAndFechaLessThan(java.util.Date fecha,java.lang.String pais ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByPaisAndFechaLessThan(java.util.Date fecha,java.lang.String pais) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.and(
Filters.and(
			Filters.gte("pais",JmoordbCoreDateUtil.dateToLocalDateTimeFirstHourOfDay(fecha)),
			Filters.lte("pais",JmoordbCoreDateUtil.dateToLocalDateTimeLastHourOfDay(fecha))
)
			,Filters.lt("fecha",pais)
		);


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByLocal(java.time.LocalDateTime local ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByLocal(java.time.LocalDateTime local) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.and(
Filters.gte("local",JmoordbCoreDateUtil.dateToLocalDateTimeFirstHourOfDay(local)),
Filters.lte("local",JmoordbCoreDateUtil.dateToLocalDateTimeLastHourOfDay(local))
);


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.lang.Long countByLocal(java.time.LocalDateTime local ) ">

    @Override
    public java.lang.Long countByLocal(java.time.LocalDateTime local) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.and(
Filters.gte("local",JmoordbCoreDateUtil.dateToLocalDateTimeFirstHourOfDay(local)),
Filters.lte("local",JmoordbCoreDateUtil.dateToLocalDateTimeLastHourOfDay(local))
);

		 return collection.countDocuments(filter);

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return 0L;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThanEqualsAndFechaLessThanEquals(java.util.Date start,java.util.Date end ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThanEqualsAndFechaLessThanEquals(java.util.Date start,java.util.Date end) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.and(
			Filters.gte("fecha",start)
			,Filters.lte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeLastHourOfDay(end))
		);


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThanEqualAndFechaLessThanEqual(java.util.Date start,java.util.Date end ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThanEqualAndFechaLessThanEqual(java.util.Date start,java.util.Date end) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.and(
			Filters.gte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeFirstHourOfDay(start))
			,Filters.lte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeLastHourOfDay(end))
		);


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThanEqualAndFechaLessThanEqualAndPais(java.util.Date start,java.util.Date end,java.lang.String pais ) ">

    @Override
    public java.util.List<com.jmoordbcore.capitulo13.model.Pais> findByFechaGreaterThanEqualAndFechaLessThanEqualAndPais(java.util.Date start,java.util.Date end,java.lang.String pais) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.and(
			Filters.gte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeFirstHourOfDay(start))
			,Filters.lte("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeLastHourOfDay(end))
			,Filters.and(
			Filters.eq("pais",pais)
			)
		);


    

		cursor = collection.find( filter )
					.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="java.lang.Long deleteByFechaGreaterThan(java.util.Date fecha ) ">

    @Override
    public java.lang.Long deleteByFechaGreaterThan(java.util.Date fecha) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               Bson filter =Filters.gt("fecha",JmoordbCoreDateUtil.dateToLocalDateTimeNextDayFirstHourOfDay(fecha));

		com.mongodb.client.result.DeleteResult deleteResult = collection.deleteOne(filter);

               return deleteResult.getDeletedCount();
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return 0L;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="java.lang.Boolean ping( ) ">

    @Override
    public java.lang.Boolean ping() {
        Boolean conected = Boolean.FALSE;
        try{
            MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
            Bson command = new BsonDocument("ping", new BsonInt64(1));
            Document commandResult = database.runCommand(command);
            conected = Boolean.TRUE;
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
        }
        return conected;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Optional<Pais> save(Pais pais)">

    @Override
    public Optional<Pais> save(Pais pais) {
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               pais.setIdpais(autogeneratedRepository.generate(mongodbDatabase, mongodbCollection));
               if (findByPk(pais.getIdpais()).isPresent()) { 
                   MessagesUtil.warning("There is already a record with that id");
                  return Optional.of(pais);
               }
               InsertOneResult insertOneResult = collection.insertOne(paisSupplier.toDocument(pais));
               if (insertOneResult.getInsertedId() != null) {
                  return Optional.of(pais);
               }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return Optional.empty();
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Boolean update(Pais pais)">

    @Override
    public Boolean update(Pais pais) {
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               if (!findByPk(pais.getIdpais()).isPresent()) { 
                   MessagesUtil.warning("Not found a record with that id");
                    return Boolean.FALSE;
               }
               Bson filter = Filters.empty();
               filter = Filters.eq("idpais",pais.getIdpais());
               //Jsonb jsonb = JsonbBuilder.create();
               //UpdateResult result = collection.updateOne(filter, Document.parse(jsonb.toJson(pais)));
               //UpdateResult result = collection.updateOne(filter, Document.parse(paisSupplier.toJson(pais)));
               UpdateResult result = collection.updateOne(filter,paisSupplier.toDocument(pais));
               if (result.getModifiedCount() > 0) {
                  return Boolean.TRUE;
               }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return Boolean.FALSE;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="public List<Pais> findAllPaginationSorted(Pagination pagination, Sorted sorted)">

    @Override
    public List<Pais> findAllPaginationSorted(Pagination pagination, Sorted sorted) {
        List<Pais> list = new ArrayList<>();
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
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="public List<Pais> findAll()">

    @Override
    public List<Pais> findAll() {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               		cursor = collection.find()
				.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="public List<Pais> findAllPagination(Pagination pagination)">

    @Override
    public List<Pais> findAllPagination(Pagination pagination) {
        List<Pais> list = new ArrayList<>();
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
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="public List<Pais> findAllSorted(Sorted sorted)">

    @Override
    public List<Pais>  findAllSorted(Sorted sorted) {
        List<Pais> list = new ArrayList<>();
        try {
               MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
               MongoCollection<Document> collection = database.getCollection(mongodbCollection);
               MongoCursor<Document> cursor;
               		cursor = collection.find()
					.sort(sorted.getSort())
		.iterator();

               try{
                  while (cursor.hasNext()) {
                        list.add(paisSupplier.get(Pais::new, cursor.next()));
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
// <editor-fold defaultstate="collapsed" desc="public Optional<Pais> findByPk(Long id )">

    public Optional<Pais> findByPk(Long id ) {
        try {
            MongoDatabase database = mongoClient.getDatabase(mongodbDatabase);
            MongoCollection<Document> collection = database.getCollection(mongodbCollection);
            Document doc = collection.find(eq("idpais", id)).first();
            if(doc == null){
               return Optional.empty();
            }
            Pais pais = paisSupplier.get(Pais::new, doc);
            return Optional.of(pais);
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
               Bson filter = Filters.eq("idpais",id);

		com.mongodb.client.result.DeleteResult deleteResult = collection.deleteOne(filter);

               return deleteResult.getDeletedCount();
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return 0L;
     }
// </editor-fold>

}