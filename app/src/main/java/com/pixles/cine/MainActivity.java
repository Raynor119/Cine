package com.pixles.cine;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import android.widget.TextView;
import android.os.AsyncTask;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
	TextView nn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		nn=(TextView)findViewById(R.id.mainTextView);
		conexion c = new conexion() ;

		MongoDatabase database = c.mongo.getDatabase("cine");                     //Accessing the database 
		MongoCollection<Document> collection = database.getCollection("cartelera"); 
		gestion g ;
		g = new gestion() ;
		g.execute(collection);
		//String n=g.consultarDocumentos(collection);
		
		//Toast.makeText(getApplicationContext(), ""+n, Toast.LENGTH_LONG).show();
		//nn.setText(""+n);
		
    }
	
	
	public class gestion extends AsyncTask<MongoCollection<Document>,String,Void> { 

		protected Void doInBackground(MongoCollection<Document> ... voids) { 

			consultarDocumentos(voids[0]);

			return null;
		}

		@Override
		protected void onPreExecute()
		{

			// TODO: Implement this method
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(String[] values)
		{
			// TODO: Implement this method
			//Toast.makeText(getApplicationContext(), ""+values[0], Toast.LENGTH_LONG).show();
			
			nn.setText(""+values[0]);
			
			
			super.onProgressUpdate(values);
		}



		public void consultarDocumentos(MongoCollection<Document> collection){
			String n="";
			try{   
				FindIterable<Document> findIterable = collection.find();  
				MongoCursor<Document> mongoCursor = findIterable.iterator();  
				while(mongoCursor.hasNext()){  
				
					n= n+mongoCursor.next();
					//System.out.println(mongoCursor.next());  
				}  

			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				//return "";
				n=e.getClass().getName() + ": " + e.getMessage()+"";
			}

			publishProgress(n);
		}

		public void insertarUnDocumento(MongoCollection<Document> collection){

			try{      
				Document document = new Document("nombre", "Lulu")                       //Se crea un nuevo documento
					.append("genero", "f")
					.append("peso", 200)
					.append("hogar", "Acadia")
					.append("vacunado", "true");

				collection.insertOne(document);                                         //Inserting document into the collection
				consultarDocumentos(collection);  
			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}    
		}   

		public void insertarVariosDocumentos(MongoCollection<Document> collection){
			try{
				Document document1 = new Document("nombre","dorian")                    //Se crea un nuevo documento
					.append("genero", "m")
					.append("peso", 400)
					.append("hogar", "Acadia")
					.append("vacunado", "false");

				Document document2 = new Document("nombre", "zafiro")                   //Se crea un nuevo documento
					.append("genero", "f")
					.append("peso", 250)
					.append("hogar", "Acadia")
					.append("vacunado", "true");

				List<Document> list = new ArrayList<Document>();
				list.add(document1);
				list.add(document2);
				collection.insertMany(list);

				consultarDocumentos(collection);  
			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}    
		}       

		public void actualizarUnDocumento(MongoCollection<Document> collection){
			try{         
				collection.updateOne(Filters.eq("nombre", "Fiona"), Updates.set("nombre", "Gena"));       
				consultarDocumentos(collection);    
			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}     
		}

		public void ActualizarDocumentos(MongoCollection<Document> collection){
			try{ 
				collection.updateMany(Filters.eq("vacunado", true), new Document("$set",new Document("vacunado",false)));  
				consultarDocumentos(collection);

			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}
		}
		public void eliminarDocumentos(MongoCollection<Document> collection){
			try{
				//  collection.deleteOne(Filters.eq("nombre", "Aurora"));     //Elimina el primero que encuentre
				collection.deleteMany (Filters.eq("genero", "f"));          //Elimina todas las coincidencias
				consultarDocumentos(collection);

			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}

		}
	}      
	
}


