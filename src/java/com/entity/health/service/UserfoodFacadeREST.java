/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity.health.service;

import com.entity.health.Userfood;
import com.entity.health.UserfoodPK;
import java.sql.Date;
import java.sql.Time;
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
@Path("com.entity.health.userfood")
public class UserfoodFacadeREST extends AbstractFacade<Userfood> {

    @PersistenceContext(unitName = "HealthServerPU")
    private EntityManager em;

    private UserfoodPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userId=userIdValue;foodId=foodIdValue;date=dateValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.entity.health.UserfoodPK key = new com.entity.health.UserfoodPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Integer(userId.get(0)));
        }
        java.util.List<String> foodId = map.get("foodId");
        if (foodId != null && !foodId.isEmpty()) {
            key.setFoodId(new java.lang.Integer(foodId.get(0)));
        }
        java.util.List<String> date = map.get("date");
        if (date != null && !date.isEmpty()) {
            key.setDate(new java.util.Date(date.get(0)));
        }
        return key;
    }

    public UserfoodFacadeREST() {
        super(Userfood.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Userfood entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Userfood entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.entity.health.UserfoodPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Userfood find(@PathParam("id") PathSegment id) {
        com.entity.health.UserfoodPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    /*-----------------------------------------------------------*/
    @GET
    @Path("addUserfood/{userId}/{foodId}/{date}/{time}/{amount}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addUserfood(@PathParam("userId") int userId, @PathParam("foodId") int foodId, @PathParam("date") Date date, @PathParam("time") Time time, @PathParam("amount") double amount) {
        UserfoodPK userfoodPKMy = new UserfoodPK();
        userfoodPKMy.setUserId(userId);
        userfoodPKMy.setFoodId(foodId);
        userfoodPKMy.setDate(date);
        Userfood userfoodMy = new Userfood();
        userfoodMy.setUserfoodPK(userfoodPKMy);
        userfoodMy.setTime(time);
        userfoodMy.setAmount(amount);
        super.create(userfoodMy);
    }

    @GET
    @Path("addUserfoodByName/{userName}/{foodName}/{date}/{time}/{amount}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String addUserfoodByName(
            @PathParam("userName") String userName,
            @PathParam("foodName") String foodName,
            @PathParam("date") Date date, 
            @PathParam("time") Time time,
            @PathParam("amount") double amount) {
        String executeResult = "";
        TypedQuery<Userfood> q_user = em.createQuery("SELECT u from Userfood u where u.user.name='"+userName+"'", Userfood.class);
        if(q_user.getResultList().isEmpty()){
            executeResult = "The user you input does not exist!";
            return executeResult;
        }
        TypedQuery<Userfood> q_food = em.createQuery("SELECT u from Userfood u where u.food.name='"+foodName+"'", Userfood.class);        
        if(q_food.getResultList().isEmpty()){
            executeResult = "The food you input does not exist!";
            return executeResult;
        }
        TypedQuery<Userfood> q = em.createQuery("SELECT u from Userfood u where u.user.name='"+userName+"' AND u.food.name='"+foodName+"' AND u.userfoodPK.date='"+date+"'", Userfood.class);
        List<Userfood> results = q.getResultList();
        if(results.isEmpty()){
            int userId = q_user.getResultList().get(0).getUserfoodPK().getUserId();
            int foodId = q_food.getResultList().get(0).getUserfoodPK().getFoodId();
            UserfoodPK userfoodPKMy = new UserfoodPK();
            userfoodPKMy.setUserId(userId);
            userfoodPKMy.setFoodId(foodId);
            userfoodPKMy.setDate(date);
            Userfood userfoodMy = new Userfood();
            userfoodMy.setUserfoodPK(userfoodPKMy);
            userfoodMy.setTime(time);
            userfoodMy.setAmount(amount);
            super.create(userfoodMy);
            executeResult = "Add success!";
            return executeResult;
        }else{
            executeResult = "User "+userName+" has already eaten "+foodName+" in "+date;
            return executeResult;
        }
    }  

    /*------------------------static query----------------------*/
    
    @GET
    @Path("findByFoodNameAndDate/{foodName}/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findByFoodNameAndDate(@PathParam("foodName") String foodName, @PathParam("date") Date date){
        Query query = em.createNamedQuery("Userfood.findByFoodNameAndDate");
        query.setParameter("foodName", foodName);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUserId/{userId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findByUserId(@PathParam("userId") int userId) {
        Query query = em.createNamedQuery("Userfood.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @GET
    @Path("findByFoodId/{foodId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findByFoodId(@PathParam("foodId") int foodId) {
        Query query = em.createNamedQuery("Userfood.findByFoodId");
        query.setParameter("foodId", foodId);
        return query.getResultList();
    }

    @GET
    @Path("findByDate/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findByDate(@PathParam("date") Date date) {
        Query query = em.createNamedQuery("Userfood.findByDate");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @GET
    @Path("findByTime/{time}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findByTime(@PathParam("time") Time time) {
        Query query = em.createNamedQuery("Userfood.findByTime");
        query.setParameter("time", time);
        return query.getResultList();
    }

    @GET
    @Path("findByAmount/{amount}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findByAmount(@PathParam("amount") double amount) {
        Query query = em.createNamedQuery("Userfood.findByAmount");
        query.setParameter("amount", amount);
        return query.getResultList();
    }

    /*------------------------dynamic query----------------------*/
    @GET
    @Path("findByUserIdAndFoodId/{userId}/{foodId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findByUserIdAndFoodId(@PathParam("userId") int userId, @PathParam("foodId") int foodId) {
        TypedQuery<Userfood> q = em.createQuery("SELECT u FROM Userfood u WHERE u.userfoodPK.userId='" + userId + "' AND u.userfoodPK.foodId='" + foodId + "'", Userfood.class);
        return q.getResultList();
    }

    @GET
    @Path("findByUserNameAndFoodName/{userName}/{foodName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findByUserNameAndFoodName(@PathParam("userName") String userName, @PathParam("foodName") String foodName) {
        TypedQuery<Userfood> q = em.createQuery("SELECT u FROM Userfood u WHERE u.user.name='" + userName + "' AND u.food.name='" + foodName + "'", Userfood.class);
        return q.getResultList();
    }

    @GET
    @Path("findByUserNameAndDate/{userName}/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Userfood> findByUserNameAndDate(@PathParam("userName") String userName, @PathParam("date") Date date) {
        TypedQuery<Userfood> q = em.createQuery("SELECT u FROM Userfood u WHERE u.user.name='" + userName + "' AND u.userfoodPK.date='" + date + "'", Userfood.class);
        return q.getResultList();
    }
    
    
}
