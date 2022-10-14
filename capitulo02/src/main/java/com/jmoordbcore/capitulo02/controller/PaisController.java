/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordbcore.capitulo02.controller;

import com.jmoordb.core.annotation.date.DateFormat;
import com.jmoordb.core.annotation.date.DateTimeFormat;
import com.jmoordb.core.util.JmoordbCoreDateUtil;
import com.jmoordb.core.util.MessagesUtil;
import com.jmoordbcore.capitulo02.model.Pais;
import com.jmoordbcore.capitulo02.repository.PaisRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 *
 * @author avbravo
 */
@Path("pais")
@Tag(name = "Información del pais", description = "End-point para entidad Pais")
public class PaisController {

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
    // <editor-fold defaultstate="collapsed" desc="Inject">
    @Inject
    PaisRepository paisRepository;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="List<Pais> findAll()">

 
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene todos los paises", description = "Retorna todos los paises disponibles")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Pais findByIdpais">

    @GET
    @Path("{idpais}")
    @Operation(summary = "Busca un pais por el idpais", description = "Busqueda de pais por idpais")
    @APIResponse(responseCode = "200", description = "El pais")
    @APIResponse(responseCode = "404", description = "Cuando no existe el idpais")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "El pais", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Pais.class)))
    public Pais findByIdpais(
            @Parameter(description = "El idpais", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idpais") Long idpais) {
        return paisRepository.findByPk(idpais).orElseThrow(
                () -> new WebApplicationException("No hay pais con idpais " + idpais, Response.Status.NOT_FOUND));

    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Response save">

    @POST
    @Operation(summary = "Inserta un nuevo pais", description = "Inserta un nuevo pais")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  pais")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response save(
            @RequestBody(description = "Crea un nuevo pais.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pais.class))) Pais pais) {

        pais.setFecha(new Date());
        return Response.status(Response.Status.CREATED).entity(paisRepository.save(pais)).build();
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Response update">

    @PUT
    @Operation(summary = "Inserta un nuevo pais", description = "Inserta un nuevo pais")
    @APIResponse(responseCode = "201", description = "Cuanoo se crea un  pais")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response update(
            @RequestBody(description = "Crea un nuevo pais.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pais.class))) Pais pais) {

        pais.setFecha(new Date());
        return Response.status(Response.Status.CREATED).entity(paisRepository.save(pais)).build();
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Response delete">

    @DELETE
    @Path("{idpais}")
    @Operation(summary = "Elimina un pais por  idpais", description = "Elimina un pais por su idpais")
    @APIResponse(responseCode = "200", description = "Cuando elimina el pais")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    public Response delete(
            @Parameter(description = "El elemento idpais", required = true, example = "1", schema = @Schema(type = SchemaType.NUMBER)) @PathParam("idpais") Long idpais) {
        paisRepository.deleteByPk(idpais);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="fechahora">

      @Path("fechahora")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha igual", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFecha(@QueryParam("fecha") @DateFormat("dd-MM-yyyy") final Date fecha) {
          
        return paisRepository.findByFecha(fecha);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Pais> findByFechaGreaterThan(@QueryParam("fecha") @DateFormat("dd-MM-yyyy") final Date fecha)">

    @Path("fechagreaterthan")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha mayor", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaGreaterThan(@QueryParam("fecha") @DateFormat("dd-MM-yyyy") final Date fecha) {        
        return paisRepository.findByFechaGreaterThan(fecha);
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="List<Pais> findByFechaGreaterThan(@QueryParam("fecha") @DateFormat("dd-MM-yyyy") final Date fecha)">

    @Path("fechagreaterthanequals")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha mayor", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaGreaterThanEquals(@QueryParam("fecha") @DateFormat("dd-MM-yyyy") final Date fecha) {
        
        return paisRepository.findByFechaGreaterThanEquals(fecha);
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="List<Pais> findByFechaGreaterThanAndFechaLessThanWithoutHours(@QueryParam("fecha") @DateFormat final Date fecha)">

    @Path("dategreaterthanandfechalessthanwithouthours")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha ", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaGreaterThanAndFechaLessThanWithoutHours(@QueryParam("fecha") @DateFormat final Date fecha) {

        Date dateStart = JmoordbCoreDateUtil.setHourToDate(fecha, 0, 0);
        Date dateEnd = JmoordbCoreDateUtil.setHourToDate(fecha, 23, 59);

        System.out.println("fecha[" + fecha + "]  start [ " + dateStart + "] en dateEnd [" + dateEnd + "]");
        return paisRepository.findByFechaGreaterThanAndFechaLessThan(dateStart, dateEnd);

    }

    // </editor-fold>
    
    
    @Path("dategreaterthanandfechalessthanwithouthourstwodates")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha ", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaGreaterThanAndFechaLessThanWithoutHours(@QueryParam("fecha") @DateFormat final Date fecha, @QueryParam("fechafinal") @DateFormat final Date fechafinal) {

        Date dateStart = JmoordbCoreDateUtil.setHourToDate(fecha, 0, 0);
        Date dateEnd = JmoordbCoreDateUtil.setHourToDate(fechafinal, 23, 59);

        System.out.println("fecha[" + fecha + "]  start [ " + dateStart + "] en dateEnd [" + dateEnd + "]");
        return paisRepository.findByFechaGreaterThanAndFechaLessThan(dateStart, dateEnd);

    }

   
  

    

}
