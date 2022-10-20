/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordbcore.capitulo02.controller;

import com.jmoordb.core.annotation.date.DateFormat;
import com.jmoordb.core.util.JmoordbCoreDateUtil;
import com.jmoordb.core.util.JmoordbCoreUtil;
import com.jmoordbcore.capitulo02.model.Pais;
import com.jmoordbcore.capitulo02.repository.PaisRepository;
import com.mongodb.client.MongoClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;
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
    
     @Inject
    @Metric(name = "counter")
    private Counter counter;
// </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="  @Path("insert")">

    @Path("insert")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  
    public List<Pais> insert(@QueryParam("inicial")  final Integer inicial) {
        
      Integer limiteFactor =13545;

Integer maximo = inicial +limiteFactor;
       for(int i=inicial;i<=maximo ;i++){
           
  
           Pais pais = new Pais();
           pais.setIdpais(JmoordbCoreUtil.integerToLong(i));
           pais.setPais("Pais - "+pais.getIdpais());
           pais.setFecha(new Date());
           paisRepository.save(pais);
       }
        return new ArrayList<>();
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="findAll">

 
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Timed(name = "paisesFindAll",
            description = "Monitorea el tiempo en que se obtiene la lista de todos los paises",
            unit = MetricUnits.MILLISECONDS,  absolute = true)
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
        
                 counter.inc();                
        return paisRepository.findByPk(idpais).orElseThrow(
                () -> new WebApplicationException("No hay pais con idpais " + idpais, Response.Status.NOT_FOUND));

    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Response save">

    @POST
    @Metered(name = "paisSave",
            unit = MetricUnits.MILLISECONDS,
            description = "Monitor la rata de eventos ocurridos al insertar pais",
            absolute = true)
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
    
    // <editor-fold defaultstate="collapsed" desc="code">

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
    
    
// <editor-fold defaultstate="collapsed" desc="@Path("fechagreaterthanequals")">

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
    
    
    
// <editor-fold defaultstate="collapsed" desc=" @Path("fechalessthan")">

    @Path("fechalessthan")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha mayor", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaLessThan(@QueryParam("fecha") @DateFormat("dd-MM-yyyy") final Date fecha) {        
        return paisRepository.findByFechaLessThan(fecha);
    }
// </editor-fold>
   
    // <editor-fold defaultstate="collapsed" desc=" @Path("fechalessthanequals")">


    @Path("fechalessthanequals")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha mayor", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaLessThanEquals(@QueryParam("fecha") @DateFormat("dd-MM-yyyy") final Date fecha) {
        
        return paisRepository.findByFechaLessThanEquals(fecha);
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="@Path("fechaandpais")">


    @Path("fechaandpais")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha mayor", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public Optional<Pais> findByFechaAndPais(@QueryParam("fecha") @DateFormat("dd-MM-yyyy") final Date fecha, @QueryParam("pais") String pais) {
        
        return paisRepository.findByFechaAndPais(fecha, pais);
    }
// </editor-fold>
    

    // <editor-fold defaultstate="collapsed" desc="@Path("fechalessthanandpais")">

    @Path("fechalessthanandpais")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha mayor", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaLessThanAndPais(@QueryParam("fecha") @DateFormat("dd-MM-yyyy") final Date fecha, @QueryParam("pais") String pais) {        
        return paisRepository.findByFechaLessThanAndPais(fecha, pais);
    }
// </editor-fold>
    
    
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="@Path("fechagreaterthanandfechalessthanwithouthours")">

    @Path("fechagreaterthanandfechalessthanwithouthours")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha ", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaGreaterThanAndFechaLessThanWithoutHours(@QueryParam("fecha") @DateFormat final Date fecha) {

       Date dateStart = JmoordbCoreDateUtil.setHourToDate(fecha, 7, 0);
       return paisRepository.findByFechaGreaterThanEqualsAndFechaLessThanEquals(dateStart, fecha);

    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="@Path("fechagreaterthanequalandfechalessthanequal")">

    @Path("fechagreaterthanequalandfechalessthanequal")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Operation(summary = "Obtiene los paises con fecha ", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaGreaterThanEqualAndFechaLessThanEqual(@QueryParam("fecha") @DateFormat final Date fecha, @QueryParam("fechafinal") @DateFormat final Date fechafinal) {
        return paisRepository.findByFechaGreaterThanEqualAndFechaLessThanEqual(fecha, fechafinal);

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Path("fechagreaterthanequalandfechalessthanequalandpais")">

    @Path("fechagreaterthanequalandfechalessthanequalandpais")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Counted(unit = MetricUnits.NONE,
            name = "findByEntreFechasAndPais",
            absolute = true,
            displayName = "obtiene lista de paises",
            description = "Monitorea cuantas veces el método es invocado")
    @Operation(summary = "Obtiene los documentos entre fechas y pais ", description = "Retorna todos los paises con fechas mayor")
    @APIResponse(responseCode = "500", description = "Servidor inalcanzable")
    @APIResponse(responseCode = "200", description = "Los paises")
    @Tag(name = "BETA", description = "Esta api esta en desarrollo")
    @APIResponse(description = "Los paises", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Collection.class, readOnly = true, description = "los paises", required = true, name = "paises")))
    public List<Pais> findByFechaGreaterThanEqualAndFechaLessThanEqualAndPais(@QueryParam("fecha") @DateFormat final Date fecha, @QueryParam("fechafinal") @DateFormat final Date fechafinal, @QueryParam("pais") String pais) {
        return paisRepository.findByFechaGreaterThanEqualAndFechaLessThanEqualAndPais(fecha, fechafinal, pais);

    }

    // </editor-fold>
  
// <editor-fold defaultstate="collapsed" desc="code">
    @Gauge(name = "paisCountFindById", unit = MetricUnits.NONE)
    private long count() {
        return counter.getCount();
    }
// </editor-fold>
    

}
