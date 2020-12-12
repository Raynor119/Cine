package com.pixles.cine;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.json.JSONObject;
import org.bson.types.ObjectId;

public class reservas extends AppCompatActivity
{
	RecyclerView recyclerView1;
	public List<rese> vs = new ArrayList<>();
	int desci=0;
	gestion g;
	String idg="";
	String cedu="";
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva);
		recyclerView1 =(RecyclerView) findViewById(R.id.listru);
		assert recyclerView1 != null;
		Bundle extra = getIntent().getExtras();				
		cedu=extra.getString("cc");
		//reclicle();
		conexion c = new conexion() ;

		MongoDatabase database = c.mongo.getDatabase("cine");

		MongoCollection<Document> collection = database.getCollection("cartelera"); 

		g = new gestion() ;
		g.execute(collection);
	}
	public void reclicle(){
		for(int x=0;x<vs.size();x++){
			if(vs.get(x).getCedula().equals(cedu)){
				
			}else{
				vs.remove(x);
			}
		}
		recyclerView1.setAdapter(new SimpleItemRecyclerViewAdapter(reservas.this, vs));
		animacion(recyclerView1);
	}
	private void animacion(RecyclerView recyclerView){		
		Context context=recyclerView.getContext();		
		LayoutAnimationController animacion= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_from_right);		
		recyclerView.setLayoutAnimation(animacion);		
		recyclerView.getAdapter().notifyDataSetChanged();		
		recyclerView.scheduleLayoutAnimation();	
	}
	public class SimpleItemRecyclerViewAdapter	
	extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
		private final reservas mParentActivity; 
		private final List<rese> vusa;		
		private Transition transicion;		

		private final View.OnClickListener mOnClickListener = new View.OnClickListener() { 
			@Override public void onClick(View view) {
				
				final String id=vusa.get(recyclerView1.getChildAdapterPosition(view)).getId();
				
				AlertDialog.Builder alert= new AlertDialog.Builder(mParentActivity);
				alert.setMessage("Quiere Eliminar Esta Reserva")
					.setCancelable(false)
					.setPositiveButton("si", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){
							idg=id;
							//Toast.makeText(getApplicationContext(), ""+idg, Toast.LENGTH_LONG).show();
							conexion c = new conexion() ;

							MongoDatabase database = c.mongo.getDatabase("cine");

							MongoCollection<Document> collection = database.getCollection("reserva"); 
							desci=1;
							g.cancel(true);
							gestion g1 = new gestion() ;
							g1.execute(collection);
						}

					});
				alert.setNeutralButton("no", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int which) {
							

						}
					});	

				AlertDialog titulo1=alert.create();
				titulo1.setTitle("Alerta");
				titulo1.show();
				
			}
		};
		SimpleItemRecyclerViewAdapter(reservas parent,
									  List<rese> items) {
			vusa = items;
			mParentActivity = parent;

		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.reservas, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			holder.cc1.setText(vusa.get(position).getCedula());
			holder.peli.setText(vusa.get(position).getPelicula());
			holder.silla.setText(vusa.get(position).getSilla());
			holder.funcion.setText(vusa.get(position).getFuncion());
			
			holder.itemView.setTag(vusa.get(position));
			holder.itemView.setOnClickListener(mOnClickListener);
		}

		@Override
		public int getItemCount() {
			return vusa.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			final TextView cc1,peli,silla,funcion;
			



			ViewHolder(View view) {
				super(view);
				
				cc1=(TextView) view.findViewById(R.id.cc1);
				peli=(TextView) view.findViewById(R.id.peli);
				silla=(TextView) view.findViewById(R.id.silla);
				funcion=(TextView) view.findViewById(R.id.funcion);


			}
		}
	}
	public class gestion extends AsyncTask<MongoCollection<Document>,String,Void> { 

		protected Void doInBackground(MongoCollection<Document> ... voids) { 

			if(desci==0){
				consultarDocumentos(voids[0]);
			}
			if(desci==1){
				eliminarDocumentos(voids[0]);
			}
			



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
			reclicle();
			//nn.setText(""+values[0]);


			super.onProgressUpdate(values);
		}



		public void consultarDocumentos(MongoCollection<Document> collection){
			String n="";
			try{   
				
				vs.clear();
				
				FindIterable<Document> findIterable = collection.find();  
				MongoCursor<Document> mongoCursor = findIterable.iterator(); 
				conexion c = new conexion() ;
				DB db = c.mongo.getDB("cine");

				while(mongoCursor.hasNext()){  

					n= n+mongoCursor.next();


					//System.out.println(mongoCursor.next());  
				}  
				String si="";
				for(int i=0;i<db.getCollection("reserva").find().toArray().size();i++){

					List<BasicDBObject> nnn= (List<BasicDBObject>) db.getCollection("reserva").find().toArray();
					n=nnn+"";
					JSONObject json= new JSONObject(nnn.get(i).get("funcion")+"");
					n=""+"Dia: "+json.getString("dia")+"\n Hora: "+json.getString("hora")+"\n Sala: "+json.getString("sala");
					JSONObject j=new JSONObject(nnn.get(i).get("sillas")+"");
					for(int x=0;x<j.length();x++){
						si=si+j.getString("silla"+(x+1))+", ";
					}
					vs.add(new rese(""+nnn.get(i).get("_id"),""+nnn.get(i).get("cedula"),""+nnn.get(i).get("pelicula"),si,n));
					si="";
				}
				List<BasicDBObject> nnn= (List<BasicDBObject>) db.getCollection("reserva").find().toArray();
				n=nnn+"";
				


			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				//return "";
				n=e.getClass().getName() + ": " + e.getMessage()+"";
			}

			publishProgress(n);
		}
		public void eliminarDocumentos(MongoCollection<Document> collection){
			String n="";
			try{
				ObjectId id1= new ObjectId(idg);
				collection.deleteOne (Filters.eq("_id", id1));  //Elimina todas las coincidencias
				consultarDocumentos(collection);

			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				n=""+e.getClass().getName() + ": " + e.getMessage();
			}
			publishProgress(n);

		}
		



	}      
	
	
}
