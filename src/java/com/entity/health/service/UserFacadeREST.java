/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity.health.service;

import com.entity.health.User;
import com.entity.health.Userfood;
import com.entity.health.Userreport;
import java.sql.Date;
import java.util.ArrayList;
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
import net.sf.json.JSONArray;

/**
 *
 * @author Administrator
 */
@Stateless
@Path("com.entity.health.user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "HealthServerPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, User entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    @GET
    @Path("addUser/{name}/{age}/{height}/{weight}/{gender}/{activityLevel}/{stepPerMile}")
    @Produces({MediaType.APPLICATION_JSON})
    public String addUser(@PathParam("name") String name, @PathParam("age") int age, @PathParam("height") double height, @PathParam("weight") double weight, @PathParam("gender") int gender, @PathParam("activityLevel") int activityLevel, @PathParam("stepPerMile") double stepPerMile){
        System.out.println("here");
        List<User> u = findByName(name);
        if(u.isEmpty()){
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("0");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            User userMy = new User();
            userMy.setName(name);
            userMy.setAge(age);
            userMy.setHeight(height);
            userMy.setWeight(weight);
            userMy.setGender(gender);
            userMy.setActivityLevel(activityLevel);
            userMy.setStepPerMile(stepPerMile);
            super.create(userMy);
            return jsonArray.toString();
        }
        else{
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("1");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            return jsonArray.toString();
        }
        
    }
    
    
    /*-----------static query--------------*/
    @GET
    @Path("findById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findById(@PathParam("id") int id){
        Query query = em.createNamedQuery("User.findById");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    @GET
    @Path("findByName/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findByName(@PathParam("name") String name){
        Query query = em.createNamedQuery("User.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }
    
    @GET
    @Path("findByAge/{age}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findByAge(@PathParam("age") int age){
        Query query = em.createNamedQuery("User.findByAge");
        query.setParameter("age", age);
        return query.getResultList();
    }
    
    @GET
    @Path("findByHeight/{height}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findByHeight(@PathParam("height") double height){
        Query query = em.createNamedQuery("User.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }
    
    @GET
    @Path("findByWeight/{weight}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findByWeight(@PathParam("weight") double weight){
        Query query = em.createNamedQuery("User.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }
    
    @GET
    @Path("findByGender/{gender}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findByGender(@PathParam("gender") int gender){
        Query query = em.createNamedQuery("User.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }
    
    @GET
    @Path("findByActivityLevel/{activityLevel}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findByActivityLevel(@PathParam("activityLevel") int activityLevel){
        Query query = em.createNamedQuery("User.findByActivityLevel");
        query.setParameter("activityLevel", activityLevel);
        return query.getResultList();
    }
    
    @GET
    @Path("findByStepPerMile/{stepPerMile}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findByStepPerMile(@PathParam("stepPerMile") double stepPerMile){
        Query query = em.createNamedQuery("User.findByStepPerMile");
        query.setParameter("stepPerMile", stepPerMile);
        return query.getResultList();
    }
    
    /*-------------------------dynamic query--------------------------*/
    
    @GET
    @Path("findByGenderAndActivityLevel/{gender}/{activityLevel}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> findByGenderAndActivityLevel(@PathParam("gender") int gender, @PathParam("activityLevel") int activityLevel){
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.gender='"+gender+"' AND u.activityLevel='"+activityLevel+"'", User.class);
        return q.getResultList();
    }
    
    
    /*---------------calculation method--------------------*/
    
    @GET
    @Path("getBMRById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getBMRById(@PathParam("id") int id){
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.id='"+id+"'", User.class);
        User userMy = q.getResultList().get(0);
        //System.out.println(userMy.getName()+" heheh");
        double BMR = 0.0;
        if(userMy.getGender()==0){
            BMR = 13.75*userMy.getWeight()+5*userMy.getHeight()-6.76*userMy.getAge()+66;
        }
        if(userMy.getGender()==1){
            BMR = 9.56*userMy.getWeight()+1.85*userMy.getHeight()-4.68*userMy.getAge()+655;
        }
        return String.valueOf(BMR);
    }
    
    @GET
    @Path("getCalBurnedAtRestById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCalBurnedAtRestById(@PathParam("id") int id){
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.id='"+id+"'", User.class);
        User userMy = q.getResultList().get(0);
        
        double result = 0.0;
        if(userMy.getActivityLevel()==1){
            result = 1.2*Double.valueOf(getBMRById(id));
        }
        if(userMy.getActivityLevel()==2){
            result = 1.375*Double.valueOf(getBMRById(id));
        }
        if(userMy.getActivityLevel()==3){
            result = 1.55*Double.valueOf(getBMRById(id));
        }
        if(userMy.getActivityLevel()==4){
            result = 1.725*Double.valueOf(getBMRById(id));
        }
        if(userMy.getActivityLevel()==5){
            result = 1.9*Double.valueOf(getBMRById(id));
        }
        return String.valueOf(result);
    }
    
    @GET
    @Path("getCalBurnedPerStepById/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCalBurnedPerStepById(@PathParam("id") int id){
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.id='"+id+"'", User.class);
        User userMy = q.getResultList().get(0);
        double calBurnedPerStep = 0.0;
        double stepPerMile = userMy.getStepPerMile();
        double weightInPound = userMy.getWeight()*2.2046;
        double calBurnedPerMile = 0.49*weightInPound;
        calBurnedPerStep = calBurnedPerMile/stepPerMile;
        return String.valueOf(calBurnedPerStep);
    }
    
    @GET
    @Path("getCalBurnedByUserIdAndDate/{userId}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCalBurnedByUserIdAndDate(@PathParam("userId") int userId, @PathParam("date") Date date){
        TypedQuery<Userreport> q = em.createQuery("SELECT u FROM Userreport u WHERE u.userreportPK.userId='"+userId+"' AND u.userreportPK.date='"+date+"'", Userreport.class);
        //System.out.println(q.getResultList().size());
        List<Userreport> resultList = q.getResultList();
        if(resultList.size()==0){
            return "0";
        }
        else{
            Userreport userreportMy = q.getResultList().get(0);
            double steps = userreportMy.getSteps();
            double totalCalBurned;
            totalCalBurned = Double.valueOf(getCalBurnedPerStepById(userId))*steps + Double.valueOf(getCalBurnedAtRestById(userId));
            return String.valueOf(totalCalBurned);
        }  
    }
    
    @GET
    @Path("getCalBurnedByUserIdAndFromDateAndToDate/{userId}/{fromDate}/{toDate}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCalBurnedByUserIdAndFromDateAndToDate(@PathParam("userId") int userId, @PathParam("fromDate") Date fromDate, @PathParam("toDate") Date toDate){
        
        TypedQuery<Userfood> q = em.createQuery("SELECT u FROM Userfood u WHERE u.userfoodPK.userId='"+userId+"' ORDER BY u.userfoodPK.date", Userfood.class);
        List<Userfood> resultList = q.getResultList();
        int listSize = resultList.size();    
        //System.out.println(listSize);
        Userfood[] userfoodMy = new Userfood[listSize];
        
        long millisecondFromDate = fromDate.getTime();
        long millisecondToDate = toDate.getTime();
        double totalCalBurned = 0.0;
        //System.out.println("------------------");
        double last = -1.0;
        for(int i=0; i<listSize; i++){
            //System.out.println("**********");
            userfoodMy[i] = resultList.get(i);
            long millisecondThisDate = userfoodMy[i].getUserfoodPK().getDate().getTime();
            if(millisecondThisDate>=millisecondFromDate && millisecondThisDate<=millisecondToDate){
                if(millisecondThisDate != last){
                    double thisCalBurned = Double.valueOf(getCalBurnedByUserIdAndDate(userId, new Date(millisecondThisDate)));
                    totalCalBurned = totalCalBurned + thisCalBurned;
                    last = millisecondThisDate;
                }
            }
           
        }
        return String.valueOf(totalCalBurned);
    }
    
    
    @GET
    @Path("getCalConsumedByUserIdAndDate/{userId}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCalConsumedByUserIdAndDate(@PathParam("userId") int userId, @PathParam("date") Date date){
        TypedQuery<Userfood> q = em.createQuery("SELECT u FROM Userfood u WHERE u.userfoodPK.userId='"+userId+"' AND u.userfoodPK.date='"+date+"'", Userfood.class);
        List<Userfood> resultList = q.getResultList();
        int listSize = resultList.size();
        System.out.println(listSize);
        Userfood[] userfoodMy = new Userfood[listSize];
        double result = 0.0;
        for(int i=0; i<listSize; i++){
            userfoodMy[i] = resultList.get(i);
            double calPerServ = userfoodMy[i].getFood().getCalorie()/userfoodMy[i].getFood().getServing();
            result = result + userfoodMy[i].getAmount()*calPerServ;
        }
        return String.valueOf(result);
    }
    
    @GET
    @Path("getCalConsumedByUserNameAndDate/{userName}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCalConsumedByUserNameAndDate(@PathParam("userName") String userName, @PathParam("date") Date date){
      
        TypedQuery<Userfood> q = em.createQuery("SELECT u FROM Userfood u WHERE u.user.name='"+userName+"' AND u.userfoodPK.date='"+date+"'", Userfood.class);
        List<Userfood> resultList = q.getResultList();
        int listSize = resultList.size();
        System.out.println(listSize);
        Userfood[] userfoodMy = new Userfood[listSize];
        double result = 0.0;
        for(int i=0; i<listSize; i++){
            userfoodMy[i] = resultList.get(i);
            double calPerServ = userfoodMy[i].getFood().getCalorie()/userfoodMy[i].getFood().getServing();
            result = result + userfoodMy[i].getAmount()*calPerServ;
        }
        return String.valueOf(result);
    }

    
    @GET
    @Path("getCalConsumedByUserIdAndFromDateAndToDate/{userId}/{fromDate}/{toDate}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCalConsumedByUserIdAndFromDateAndToDate(@PathParam("userId") int userId, @PathParam("fromDate") Date fromDate, @PathParam("toDate") Date toDate){
        TypedQuery<Userfood> q = em.createQuery("SELECT u FROM Userfood u WHERE u.userfoodPK.userId='"+userId+"' ORDER BY u.userfoodPK.date", Userfood.class);
        List<Userfood> resultList = q.getResultList();
        int listSize = resultList.size();
        System.out.println(listSize);
        Userfood[] userfoodMy = new Userfood[listSize];
        
        long millisecondFromDate = fromDate.getTime();
        long millisecondToDate = toDate.getTime();
        
        double totalCalConsumed = 0.0;
        double last = -1.0;
        for(int i=0; i<listSize; i++){
            userfoodMy[i] = resultList.get(i);
            long millisecondThisDate = userfoodMy[i].getUserfoodPK().getDate().getTime();
            //System.out.println(millisecondThisDate);
            if(millisecondThisDate>=millisecondFromDate && millisecondThisDate<=millisecondToDate){
                if(millisecondThisDate != last){
                    double thisCalConsumed = Double.valueOf(getCalConsumedByUserIdAndDate(userId, new Date(millisecondThisDate)));
                    //System.out.println(thisCalConsumed);
                    totalCalConsumed = totalCalConsumed + thisCalConsumed;
                    last = millisecondThisDate;
                }  
            }
        }
        return String.valueOf(totalCalConsumed);
    }

    
    @GET
    @Path("updateCalBurnedByUserIdAndDate/{userId}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public void updateCalBurnedByUserIdAndDate(@PathParam("userId") int userId, @PathParam("date") Date date){
        double calBurned;
        calBurned = Double.valueOf(getCalBurnedByUserIdAndDate(userId, date));
        TypedQuery<Userreport> q = em.createQuery("UPDATE Userreport u SET u.calBurned='"+calBurned+"' WHERE u.userreportPK.userId='"+userId+"' AND u.userreportPK.date='"+date+"'", Userreport.class);
        q.executeUpdate();
    }
    
    @GET
    @Path("updateCalBurnedByUserNameAndDate/{userName}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public String updateCalBurnedByUserNameAndDate(@PathParam("userName") String userName, @PathParam("date") Date date){
        List<User> user = findByName(userName);
        if(!user.isEmpty()){//update success
            int userId = user.get(0).getId();
            double calBurned;
            calBurned = Double.valueOf(getCalBurnedByUserIdAndDate(userId, date));
            TypedQuery<Userreport> q = em.createQuery("UPDATE Userreport u SET u.calBurned='"+calBurned+"' WHERE u.userreportPK.userId='"+userId+"' AND u.userreportPK.date='"+date+"'", Userreport.class);
            q.executeUpdate();
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("0");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            return jsonArray.toString();
        }
        else{//update failed
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("1");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            return jsonArray.toString();
        }      
        
    }
    
    @GET
    @Path("updateCalConsumedByUserIdAndDate/{userId}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public void updateCalConsumedByUserIdAndDate(@PathParam("userId") int userId, @PathParam("date") Date date){
        double calConsumed;
        calConsumed = Double.valueOf(getCalConsumedByUserIdAndDate(userId, date));
        TypedQuery<Userreport> q = em.createQuery("UPDATE Userreport u SET u.calConsumed='"+calConsumed+"' WHERE u.userreportPK.userId='"+userId+"' AND u.userreportPK.date='"+date+"'", Userreport.class);
        q.executeUpdate();
    }
    
    @GET
    @Path("updateCalConsumedByUserNameAndDate/{userName}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public String updateCalConsumedByUserNameAndDate(@PathParam("userName") String userName, @PathParam("date") Date date){
        double calConsumed;
        calConsumed = Double.valueOf(getCalConsumedByUserNameAndDate(userName, date));
        System.out.println("hhh"+calConsumed);
        TypedQuery<Userreport> q = em.createQuery("UPDATE Userreport u SET u.calConsumed='"+calConsumed+"' WHERE u.user.name='"+userName+"' AND u.userreportPK.date='"+date+"'", Userreport.class);
        int numOfEntityAffected = q.executeUpdate();
        if( numOfEntityAffected == 1){//update success
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("0");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            return jsonArray.toString();
        }
        else{//update fail
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("1");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            return jsonArray.toString();
        }
        
    }
    
    @GET
    @Path("updateCalorieGoalByUserNameAndDate/{userName}/{date}/{calorieGoal}")
    @Produces({MediaType.APPLICATION_JSON})
    public String updateCalorieGoalByUserNameAndDate(@PathParam("userName") String userName, @PathParam("date") Date date, @PathParam("calorieGoal") double calorieGoal){
        TypedQuery<Userreport> q = em.createQuery("UPDATE Userreport u SET u.calorieGoal='"+calorieGoal+"' WHERE u.user.name='"+userName+"' AND u.userreportPK.date='"+date+"'", Userreport.class);
        int numOfEntityAffected = q.executeUpdate();
        if(numOfEntityAffected == 1){
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("0");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            return jsonArray.toString();
        }
        else{
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("1");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            return jsonArray.toString();
        }
    }
    
    @GET
    @Path("updateStepsByUserNameAndDate/{userName}/{date}/{steps}")
    @Produces({MediaType.APPLICATION_JSON})
    public String updateStepsByUserNameAndDate(@PathParam("userName") String userName, @PathParam("date") Date date, @PathParam("steps") double steps){
        TypedQuery<Userreport> q = em.createQuery("UPDATE Userreport u SET u.steps='"+steps+"' WHERE u.user.name='"+userName+"' AND u.userreportPK.date='"+date+"'", Userreport.class);
        int numOfEntityAffected = q.executeUpdate();
        if(numOfEntityAffected == 1){
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("0");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            return jsonArray.toString();
        }
        else{
            List<String> jsonList = new ArrayList();
            jsonList.add("status");
            jsonList.add("1");
            JSONArray jsonArray = JSONArray.fromObject(jsonList);
            System.out.println(jsonArray.toString());
            return jsonArray.toString();
        }
    }
    
    
}
