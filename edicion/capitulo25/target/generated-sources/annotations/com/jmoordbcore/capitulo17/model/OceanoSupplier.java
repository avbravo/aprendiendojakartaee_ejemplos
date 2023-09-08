package com.jmoordbcore.capitulo17.model;
// <editor-fold defaultstate="collapsed" desc="imports">

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
/**
* Java
*/
import java.time.LocalDateTime;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
/**
* Jmoordb
*/
import com.jmoordb.core.util.MessagesUtil;
import com.jmoordb.core.util.JmoordbCoreDateUtil;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.enumerations.TypePK;
import com.jmoordb.core.annotation.enumerations.TypeReferenced;
/**
* MongoDB
*/
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import com.jmoordbcore.capitulo17.model.Oceano;
import com.jmoordbcore.capitulo17.model.*;


// </editor-fold>
@RequestScoped
public class OceanoSupplier  implements Serializable{
// <editor-fold defaultstate="collapsed" desc=" public Oceano get(Supplier<? extendsOceano> s, Document document, Boolean... showError) ">

    public Oceano get(Supplier<? extends Oceano> s, Document document_, Boolean... showError) {
        Oceano oceano= s.get(); 
            Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }
	
	 oceano.setIdoceano(document_.getString("idoceano"));
	oceano.setOceano(document_.getString("oceano"));

         } catch (Exception e) {
             if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
             }
         }
         return oceano;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Oceano getId(Supplier<? extendsOceano> s, Document document, Boolean... showError) ">

    public Oceano getId(Supplier<? extends Oceano> s, Document document_, Boolean... showError) {
        Oceano oceano= s.get(); 
            Boolean show = true;
        try {
            if (showError.length != 0) {
                show = showError[0];
            }
	
	 oceano.setIdoceano(document_.getString("idoceano"));

         } catch (Exception e) {
             if (show) {
                MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
             }
         }
         return oceano;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toDocument (Oceano oceano) ">

    public Document toDocument(Oceano oceano) {
        Document document_ = new Document();
        try {
	 
		document_.put("idoceano",oceano.getIdoceano());
		document_.put("oceano",oceano.getOceano());
	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toDocument (List<Oceano> oceanoList) ">

    public List<Document> toDocument(List<Oceano> oceanoList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Oceano oceano : oceanoList){
		 Document document_ = new Document();
		document_.put("idoceano",oceano.getIdoceano());
		document_.put("oceano",oceano.getOceano());
		documentList_.add(document_);
	

       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return documentList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Bson toUpdate (Oceano oceano) ">

    public Bson toUpdate(Oceano oceano) {
        Bson update_ = Filters.empty();
        try {
        update_ = Updates.combine(
	 
		Updates.set("idoceano",oceano.getIdoceano()),
		Updates.set("oceano",oceano.getOceano())
	

        );
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return update_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Bson> toUpdate (List<Oceano> oceanoList) ">

    public List<Bson> toUpdate(List<Oceano> oceanoList) {
        List<Bson> bsonList_ = new ArrayList<>();
        try {
	 for(Oceano oceano : oceanoList){
		 Bson update_ = Filters.empty();
			update_ = Updates.combine(
	 
		Updates.set("idoceano",oceano.getIdoceano()),
		Updates.set("oceano",oceano.getOceano())
	

        );
		bsonList_.add(update_);
 
       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return bsonList_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public Document toReferenced (Oceano oceano) ">

    public Document toReferenced(Oceano oceano) {
        Document document_ = new Document();
        try {
	 
		document_.put("idoceano",oceano.getIdoceano());
	

         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return document_;
     }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" public List<Document> toReferenced(List<Oceano> oceanoList) ">

    public List<Document> toReferenced(List<Oceano> oceanoList) {
        List<Document> documentList_ = new ArrayList<>();
        try {
	 
	 for(Oceano oceano : oceanoList){
		 Document document_ = new Document();
		document_.put("idoceano",oceano.getIdoceano());
		documentList_.add(document_);
	

       }
         } catch (Exception e) {
              MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
         }
         return documentList_;
     }
// </editor-fold>

}