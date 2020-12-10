package com.pixles.cine;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;


public class conexion {
    
    MongoClient mongo ;
    MongoCredential credential;
    
    
    
    public conexion(){

          // Creating a Mongo client 
      mongo = new MongoClient( "127.0.0.1" , 27017 ); 
     
      // Creating Credentials 

      credential = MongoCredential.createCredential("accountAdmin01", "cine", 
         "123456".toCharArray());
    }
}
