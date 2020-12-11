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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.json.JSONObject;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity
{
	RecyclerView recyclerView1;
	public List<cartelera> vs = new ArrayList<>();
	public List<funcion> fun = new ArrayList<>();
	public List<detalles> ll = new ArrayList<>();
	public List<usuario> usu = new ArrayList<>();
	public List<silla> sii = new ArrayList<>();
	public String cedulag="";
	public String nombreg="";
	public String telefonog="";
	public String emailg="";
	public String diag="";
	public String holag="";
	public String salag="";
	public String peliculag="";
	public String silag="";
	int desci=0;
	gestion g ;
	public String [] v;
	public String [] sil;
	TextView nn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		//nn=(TextView)findViewById(R.id.mainTextView);
		recyclerView1 =(RecyclerView) findViewById(R.id.listru);
		assert recyclerView1 != null;
		//reclicle();
		conexion c = new conexion() ;

		MongoDatabase database = c.mongo.getDatabase("cine");
		
		MongoCollection<Document> collection = database.getCollection("cartelera"); 
		
		g = new gestion() ;
		g.execute(collection);
		
		
		
		
		
    }
	public void reclicle(){
		recyclerView1.setAdapter(new SimpleItemRecyclerViewAdapter(MainActivity.this, vs));
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
		private final MainActivity mParentActivity; 
		private final List<cartelera> vusa;		
		private Transition transicion;		

		private final View.OnClickListener mOnClickListener = new View.OnClickListener() { 
			@Override public void onClick(View view) {
				final String id;
				final String peli;
				final String di;
				final String hor;
				final String sa;
				id=vusa.get(recyclerView1.getChildAdapterPosition(view)).getId();
				peli=vusa.get(recyclerView1.getChildAdapterPosition(view)).getTitulo();
				di=fun.get(recyclerView1.getChildAdapterPosition(view)).getDia();
				hor=fun.get(recyclerView1.getChildAdapterPosition(view)).getHora();
				sa=fun.get(recyclerView1.getChildAdapterPosition(view)).getSala();
				AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);
				//alertt.setMessage("El Usuario ya se habia Registrado")
				final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.peliculad, null);

				alertt.setView(vie)
					.setCancelable(false)
					.setPositiveButton("Reserver", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){
							AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);
							//alertt.setMessage("El Usuario ya se habia Registrado")
							final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.cedula, null);

							alertt.setView(vie)
								.setCancelable(false)
								.setPositiveButton("siguiente", new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface dialog,int which){
										
										EditText d=(EditText) vie.findViewById(R.id.cc);
										final String cc=d.getText().toString();
										
										int dc=0;
										int usu1=0;
										for(int ii=0; ii < v.length; ii++ ){
											if(cc.equals(v[ii])){
												dc=1;
												usu1=ii;
											}
										}
										
										if(dc==0){
											//el usuario no esta registrado
											
											AlertDialog.Builder alert= new AlertDialog.Builder(mParentActivity);
											alert.setMessage("El usuario no esta Registrado requiere que se registre")
												.setCancelable(false)
												.setPositiveButton("siguiente", new DialogInterface.OnClickListener(){
													@Override
													public void onClick(DialogInterface dialog,int which){
														//registrar datos del usuario

														AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);

														final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.resgistrar, null);

														alertt.setView(vie)
															.setCancelable(false)
															.setPositiveButton("registrar", new DialogInterface.OnClickListener(){
																@Override
																public void onClick(DialogInterface dialog,int which){
																	
																	EditText nomb= vie.findViewById(R.id.nombre);
																	EditText tele= vie.findViewById(R.id.telefono);
																	EditText email=vie.findViewById(R.id.email);
																	conexion c = new conexion() ;
																	cedulag=cc;
																	nombreg=nomb.getText().toString();
																	telefonog=tele.getText().toString();
																	emailg=email.getText().toString();

																	MongoDatabase database = c.mongo.getDatabase("cine");
																	final MongoCollection<Document> collection = database.getCollection("usuarios");
																	desci=1;
																	g.cancel(true);
																	gestion g1 = new gestion() ;
																	g1.execute(collection);
																	
																	AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);

																	final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.monstrar, null);

																	alertt.setView(vie)
																		.setCancelable(false)
																		.setPositiveButton("reservar", new DialogInterface.OnClickListener(){
																			@Override
																			public void onClick(DialogInterface dialog,int which){
																				//desci=0;
																				//gestion g3 = new gestion() ;
																				//g3.execute(collection);
																				EditText cedu1= vie.findViewById(R.id.cc);
																				EditText silla=vie.findViewById(R.id.silla);
																				cedulag=cedu1.getText().toString();
																				peliculag=peli;
																				silag=silla.getText().toString();
																				diag=di;
																				holag=hor;
																				salag=sa;







																			}		
																		});
																	alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
																			@Override
																			public void onClick(DialogInterface dialogInterface, int which) {
																				desci=0;
																				gestion g3 = new gestion() ;
																				g3.execute(collection);
																			}
																		});


																	AlertDialog titulo=alertt.create();
																	titulo.setTitle("Datos del Usuario");
																	titulo.show();
																	EditText cedu1= vie.findViewById(R.id.cc);
																	EditText nomb1= vie.findViewById(R.id.nombre);
																	EditText tele1= vie.findViewById(R.id.telefono);
																	EditText email1=vie.findViewById(R.id.email);
																	cedu1.setText(""+cedulag);
																	nomb1.setText(""+nombreg);
																	tele1.setText(""+telefonog);
																	email1.setText(""+emailg);





																}		
															});
														alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
																@Override
																public void onClick(DialogInterface dialogInterface, int which) {

																}
															});


														AlertDialog titulo=alertt.create();
														titulo.setTitle("Registrar Datos");
														titulo.show();
														
														EditText cedu= vie.findViewById(R.id.cc);
														
														cedu.setText(""+cc);


													}

												});
												
											AlertDialog titulo1=alert.create();
											titulo1.setTitle("Alerta");
											titulo1.show();
											
											
											
										
										}else{
											AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);

											final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.monstrar, null);

											alertt.setView(vie)
												.setCancelable(false)
												.setPositiveButton("reservar", new DialogInterface.OnClickListener(){
													@Override
													public void onClick(DialogInterface dialog,int which){
														
														
														EditText cedu= vie.findViewById(R.id.cc);
														EditText silla=vie.findViewById(R.id.silla);
														
														cedulag=cedu.getText().toString();
														peliculag=peli;
														silag=silla.getText().toString();
														diag=di;
														holag=hor;
														salag=sa;







													}		
												});
											alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
													@Override
													public void onClick(DialogInterface dialogInterface, int which) {

													}
												});


											AlertDialog titulo=alertt.create();
											titulo.setTitle("Datos del Usuario");
											titulo.show();
											conexion c=new conexion();
											peliculag=peli;
											desci=2;
											//g.cancel(true);

											MongoDatabase database = c.mongo.getDatabase("cine");
											final MongoCollection<Document> collection = database.getCollection("usuarios");

											g.cancel(true);
											gestion g1 = new gestion() ;
											g1.execute(collection);
											EditText cedu= vie.findViewById(R.id.cc);
											EditText nomb= vie.findViewById(R.id.nombre);
											EditText tele= vie.findViewById(R.id.telefono);
											EditText email=vie.findViewById(R.id.email);
											Button b1= vie.findViewById(R.id.b1);

											b1.setOnClickListener(new OnClickListener(){

													@Override
													public void onClick(View p1)
													{
														
														
														
														AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);

														final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.sillas, null);

														alertt.setView(vie)
															.setCancelable(false)
															.setPositiveButton("seleccionar", new DialogInterface.OnClickListener(){
																@Override
																public void onClick(DialogInterface dialog,int which){







																}		
															});
														alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
																@Override
																public void onClick(DialogInterface dialogInterface, int which) {

																}
															});


														AlertDialog titulo=alertt.create();
														titulo.setTitle("Sillas de la Sala: "+sa);
														titulo.show();
														CheckBox A1= vie.findViewById(R.id.A1);
														CheckBox A2= vie.findViewById(R.id.A2);
														CheckBox A3= vie.findViewById(R.id.A3);
														CheckBox A4= vie.findViewById(R.id.A4);
														CheckBox A5= vie.findViewById(R.id.A5);
														CheckBox A6= vie.findViewById(R.id.A6);
														CheckBox A7= vie.findViewById(R.id.A7);
														CheckBox B1= vie.findViewById(R.id.B1);
														
														for(int i=0;i<sii.size();i++){
															//Toast.makeText(getApplicationContext(), ""+v[i], Toast.LENGTH_LONG).show();
															if(sii.get(i).getSilla().equals("A1")){
																A1.setChecked(true);
																A1.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("A2")){
																A2.setChecked(true);
																A2.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("A3")){
																A3.setChecked(true);
																A3.setEnabled(false);
															}
														}
														
														
														
														
													}


												});
											cedu.setText(""+usu.get(usu1).getCedula());
											nomb.setText(""+usu.get(usu1).getNombre());
											tele.setText(""+usu.get(usu1).getTelefono());
											email.setText(""+usu.get(usu1).getEmail());
											
											
											
											
										}




									}		
								});
							alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialogInterface, int which) {

									}
								});


							AlertDialog titulo=alertt.create();
							titulo.setTitle("Usuario");
							titulo.show();



						}		
					});
				alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int which) {

						}
					});


				AlertDialog titulo=alertt.create();
				titulo.setTitle("Pelicula");
				titulo.show();
				int nn=0;
				for(int i=0;i<ll.size();i++){
					if(ll.get(i).getId().equals(id)){
						nn=i;
					}
				}
				((TextView) vie.findViewById(R.id.titulo)).setText(ll.get(nn).getTitulo());
				((TextView) vie.findViewById(R.id.genero)).setText(ll.get(nn).getGenero());
				((TextView) vie.findViewById(R.id.claci)).setText(ll.get(nn).getClasificacion());
				((TextView) vie.findViewById(R.id.formato)).setText(ll.get(nn).getFormato());
				((TextView) vie.findViewById(R.id.resu)).setText(ll.get(nn).getResumen());
				((TextView) vie.findViewById(R.id.prota)).setText(ll.get(nn).getProta());
				((TextView) vie.findViewById(R.id.dic)).setText(ll.get(nn).getDirector());
				((TextView) vie.findViewById(R.id.funcion)).setText(" "+ll.get(nn).getFunciones());
				
				


			}
		};


		SimpleItemRecyclerViewAdapter(MainActivity parent,
									  List<cartelera> items) {
			vusa = items;
			mParentActivity = parent;

		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.pelicula, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			holder.titulo.setText(vusa.get(position).getTitulo());
			holder.formato.setText("Formato: "+vusa.get(position).getFormato());
			holder.funcion.setText("Funcion\n "+vusa.get(position).getFuncion());
			int n1=R.drawable.it2;
			if(vusa.get(position).getImagen().equals("it2")){
			 n1=R.drawable.it2;
			}
			if(vusa.get(position).getImagen().equals("manosarmas")){
				n1=R.drawable.manosarmas;
			}
			holder.imagen.setBackgroundResource(n1);
			holder.itemView.setTag(vusa.get(position));
			holder.itemView.setOnClickListener(mOnClickListener);
		}

		@Override
		public int getItemCount() {
			return vusa.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			final TextView titulo,formato,funcion;
			final LinearLayout imagen;



			ViewHolder(View view) {
				super(view);
				imagen=(LinearLayout) view.findViewById(R.id.imagen);
				titulo=(TextView) view.findViewById(R.id.titulo);
				formato=(TextView) view.findViewById(R.id.formato);
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
				insertarusuario(voids[0]);
			}
			if(desci==2){
				consultarsillas(voids[0]);
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
				ll.clear();
				vs.clear();
				usu.clear();
				FindIterable<Document> findIterable = collection.find();  
				MongoCursor<Document> mongoCursor = findIterable.iterator(); 
				conexion c = new conexion() ;
				DB db = c.mongo.getDB("cine");
				
				while(mongoCursor.hasNext()){  
				
					n= n+mongoCursor.next();
					
					
					//System.out.println(mongoCursor.next());  
				}  
				for(int i=0;i<db.getCollection("cartelera").find().toArray().size();i++){
					
					List<BasicDBObject> nnn= (List<BasicDBObject>) db.getCollection("cartelera").find().toArray();
					
					JSONObject json= new JSONObject(nnn.get(i).get("funciones")+"");
					n=""+"Dia: "+json.getString("dia")+"\n Hora: "+json.getString("hora")+"\n Sala: "+json.getString("sala");
					fun.add(new funcion(json.getString("dia"),json.getString("hora"),json.getString("sala")));
					ll.add(new detalles(""+nnn.get(i).get("_id"),""+nnn.get(i).get("titulo"),""+nnn.get(i).get("genero"),""+nnn.get(i).get("clasificacion"),""+nnn.get(i).get("formato"),""+nnn.get(i).get("resumen"),""+nnn.get(i).get("Protagonistas"),""+nnn.get(i).get("director"),n));
					vs.add(new cartelera(""+nnn.get(i).get("_id")+"",""+nnn.get(i).get("titulo"),""+nnn.get(i).get("formato"),n,""+nnn.get(i).get("imagen")));
				}
				v=new String [db.getCollection("usuarios").find().toArray().size()];
				
				for(int i=0;i<db.getCollection("usuarios").find().toArray().size();i++){
					List<BasicDBObject> nnn= (List<BasicDBObject>) db.getCollection("usuarios").find().toArray();
					v[i]=""+nnn.get(i).get("cedula");
					usu.add(new usuario(""+nnn.get(i).get("cedula"),""+nnn.get(i).get("nombre"),""+nnn.get(i).get("telefono"),""+nnn.get(i).get("email")));
				}
								
				
			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				//return "";
				n=e.getClass().getName() + ": " + e.getMessage()+"";
			}

			publishProgress(n);
		}
		
		public void consultarsillas(MongoCollection<Document> collection){
			String n="";
			try{   
				
				FindIterable<Document> findIterable = collection.find();  
				MongoCursor<Document> mongoCursor = findIterable.iterator(); 
				conexion c = new conexion() ;
				DB db = c.mongo.getDB("cine");

				while(mongoCursor.hasNext()){  

					n= n+mongoCursor.next();


					//System.out.println(mongoCursor.next());  
				} 
				n="";
				for(int i=0;i<db.getCollection("reserva").find().toArray().size();i++){

					List<BasicDBObject> nnn= (List<BasicDBObject>) db.getCollection("reserva").find().toArray();

					
					if((nnn.get(i).get("pelicula")+"").equals(peliculag)){
						sii.add(new silla(""+nnn.get(i).get("silla")));
					}
					
				}
				
				


			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				//return "";
				n=e.getClass().getName() + ": " + e.getMessage()+"";
			}

			//publishProgress(n);
		}
		

		public void insertarusuario(MongoCollection<Document> collection){

			try{      
				Document document = new Document("cedula", cedulag)                       //Se crea un nuevo documento
					.append("nombre", nombreg)
					.append("telefono", telefonog)
					.append("email", emailg);

				collection.insertOne(document);                                         //Inserting document into the collection
				 
			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
			}    
		}   
		public void insertarreserva(MongoCollection<Document> collection){

			try{
				Document document1 = new Document("dia",diag)
					.append("hora",holag)
					.append("sala",salag);
				Document document = new Document("cedula", cedulag)                       //Se crea un nuevo documento
					.append("pelicula", peliculag)
					.append("silla", silag)
					.append("funcion", document1);

				collection.insertOne(document);                                         //Inserting document into the collection

			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
			}    
		}   

		

		
	}      
	
}


