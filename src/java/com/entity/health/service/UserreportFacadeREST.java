/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity.health.service;

import com.entity.health.Userreport;
import com.entity.health.UserreportPK;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Administrator
 */
@Stateless
@Path("com.entity.health.userreport")
public class UserreportFacadeREST extends AbstractFacade<Userreport> {

    @PersistenceContext(unitName = "HealthServerPU")
    private EntityManager em;

    private UserreportPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userId=userIdValue;date=dateValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.entity.health.UserreportPK key = new com.entity.health.UserreportPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Integer(userId.get(0)));
        }
        java.util.List<String> date = map.get("date");
        if (date != null && !date.isEmpty()) {
            key.setDate(new java.util.Date(date.get(0)));
        }
        return key;
    }

    public UserreportFacadeREST() {
        super(Userreport.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Userreport entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Userreport entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.entity.health.UserreportPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Userreport find(@PathParam("id") PathSegment id) {
        com.entity.health.UserreportPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /*---------------static query------------------*/
    @GET
    @Path("findByUserId/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findByUserId(@PathParam("userId") int userId){
        Query query = em.createNamedQuery("Userreport.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDate/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findByDate(@PathParam("date") Date date){
        Query query = em.createNamedQuery("Userreport.findByDate");
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCalConsumed/{calConsumed}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findByCalConsumed(@PathParam("calConsumed") double calConsumed){
        Query query = em.createNamedQuery("Userreport.findByCalConsumed");
        query.setParameter("calConsumed", calConsumed);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCalBurned/{calBurned}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findByCalBurned(@PathParam("calBurned") double calBurned){
        Query query = em.createNamedQuery("Userreport.findByCalBurned");
        query.setParameter("calBurned", calBurned);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySteps/{steps}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findBySteps(@PathParam("steps") double steps){
        Query query = em.createNamedQuery("Userreport.findBySteps");
        query.setParameter("steps", steps);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCalorieGoal/{calorieGoal}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findByCalorieGoal(@PathParam("calorieGoal") double calorieGoal){
        Query query = em.createNamedQuery("Userreport.findByCalorieGoal");
        query.setParameter("calorieGoal", calorieGoal);
        return query.getResultList();
    }
    
    @GET
    @Path("findByRemaining/{remaining}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findByRemaining(@PathParam("remaining") double remaining){
        Query query = em.createNamedQuery("Userreport.findByRemaining");
        query.setParameter("remaining", remaining);
        return query.getResultList();
    }
    
    /*-----------dynamic query-------------*/
    @GET
    @Path("findByUserIdAndDate/{userId}/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findByUserIdAndDate(@PathParam("userId") int userId, @PathParam("date") Date date){
        TypedQuery<Userreport> q = em.createQuery("SELECT u FROM Userreport u WHERE u.userreportPK.userId='"+userId+"' AND u.userreportPK.date='"+date+"'", Userreport.class);
        return q.getResultList();
    }
    
    @GET
    @Path("findByUserNameAndDate/{userName}/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userreport> findByUserNameAndDate(@PathParam("userName") String userName, @PathParam("date") Date date){
        TypedQuery<Userreport> q = em.createQuery("SELECT u FROM Userreport u WHERE u.user.name='"+userName+"' AND u.userreportPK.date='"+date+"'", Userreport.class);
        return q.getResultList();
    }
}
