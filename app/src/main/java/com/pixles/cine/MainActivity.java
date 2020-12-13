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
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.CompoundButton;
import android.content.Intent;
import org.json.JSONArray;
import org.json.JSONException;


public class MainActivity extends AppCompatActivity
{
	private final MainActivity mParentActivity1 =MainActivity.this;
	EditText sill;
	RecyclerView recyclerView1;
	public List<cartelera> vs = new ArrayList<>();
	public List<funcion> fun = new ArrayList<>();
	public List<detalles> ll = new ArrayList<>();
	public List<usuario> usu = new ArrayList<>();
	public List<silla> sii = new ArrayList<>();
	public List<silla> sg = new ArrayList<>();
	public List<numef> num = new ArrayList<>();
	AlertDialog titulof=null;
	RecyclerView recyclerView2;
	public String cedulag="";
	public String nombreg="";
	public String telefonog="";
	public String emailg="";
	public String diag="";
	public String holag="";
	public String salag="";
	public String peliculag="";
	public String silag="";
	public String funcinng="";
	int desci=0;
	int pelii=0;
	gestion g;
	public String [] v;
	public String [] sil;
	TextView nn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		nn=(TextView)findViewById(R.id.t1);
		recyclerView1 =(RecyclerView) findViewById(R.id.listru);
		assert recyclerView1 != null;
		//reclicle();
		conexion c = new conexion() ;

		MongoDatabase database = c.mongo.getDatabase("cine");
		
		MongoCollection<Document> collection = database.getCollection("cartelera"); 
		
		g = new gestion() ;
		g.execute(collection);
		nn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					AlertDialog.Builder alertt= new AlertDialog.Builder(MainActivity.this);
					//alertt.setMessage("El Usuario ya se habia Registrado")
					final View vie = LayoutInflater.from(MainActivity.this).inflate(R.layout.cedula, null);

					alertt.setView(vie)
						.setCancelable(false)
						.setPositiveButton("siguiente", new DialogInterface.OnClickListener(){

							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								EditText d=(EditText) vie.findViewById(R.id.cc);
								final String cc=d.getText().toString();
								Intent intenet=new Intent(MainActivity.this,reservas.class);

								intenet.putExtra("cc",cc);	
								startActivity(intenet);
							}
							
							
				});
				alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int which) {

				}
			});


		AlertDialog titulo=alertt.create();
		titulo.setTitle("Digite la Cedula");
		titulo.show();
					
					
				}
				
			
		});
		
		
		
		
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
			@Override public void onClick(final View view) {
				final String id;
				final String peli;
				final String di="";
				final String hor="";
				final String sa="";
				id=vusa.get(recyclerView1.getChildAdapterPosition(view)).getId();
				peli=vusa.get(recyclerView1.getChildAdapterPosition(view)).getTitulo();
				//di=fun.get(recyclerView1.getChildAdapterPosition(view)).getDia();
				//hor=fun.get(recyclerView1.getChildAdapterPosition(view)).getHora();
				//sa=fun.get(recyclerView1.getChildAdapterPosition(view)).getSala();
				AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);
				//alertt.setMessage("El Usuario ya se habia Registrado")
				final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.peliculad, null);

				alertt.setView(vie)
					.setCancelable(false)
					.setPositiveButton("Reservar", new DialogInterface.OnClickListener(){
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
																	
																	cedulag=cc;
																	nombreg=nomb.getText().toString();
																	telefonog=tele.getText().toString();
																	emailg=email.getText().toString();
																	conexion c = new conexion() ;
																	MongoDatabase database = c.mongo.getDatabase("cine");
																	final MongoCollection<Document> collection = database.getCollection("usuarios");
																	desci=1;
																	g.cancel(true);
																	gestion g1 = new gestion() ;
																	g1.execute(collection);
																	
																	reservar(cc,nomb.getText().toString(),tele.getText().toString(),email.getText().toString(),peli,recyclerView1.getChildAdapterPosition(view));
																	
																	


																	





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
											usu.get(usu1).getNombre();
											reservar(usu.get(usu1).getCedula(),usu.get(usu1).getNombre(),usu.get(usu1).getTelefono(),usu.get(usu1).getEmail(),peli,recyclerView1.getChildAdapterPosition(view));
											
											
											
											
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
		
		public void reservar(String cc,String nombre,String tele1,String email1,final String peli,final int n1){
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
						try
						{
							JSONObject json= new JSONObject(num.get(n1).getFunciones());
							
								//n=""+json.getString("funcion"+(b+1));
							JSONObject j= new JSONObject(""+json.getString(funcinng));
								
							diag=j.getString("dia");
							holag=j.getString("hora");
							salag=j.getString("sala");

							
						}
						catch (JSONException e)
						{}
						
						conexion c = new conexion() ;
						MongoDatabase database = c.mongo.getDatabase("cine");
						final MongoCollection<Document> collection = database.getCollection("reserva");
						desci=3;
						g.cancel(true);
						gestion g4 = new gestion() ;
						g4.execute(collection);
						Toast.makeText(getApplicationContext(), "La reserva se a Guardado", Toast.LENGTH_LONG).show();







					}		
				});
			alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int which) {
						conexion c=new conexion();
						MongoDatabase database = c.mongo.getDatabase("cine");
						final MongoCollection<Document> collection = database.getCollection("usuarios");
						desci=0;
						gestion g3 = new gestion() ;
						g3.execute(collection);
					}
				});


			AlertDialog titulo=alertt.create();
			titulo.setTitle("Datos del Usuario");
			titulo.show();
			
			EditText cedu= vie.findViewById(R.id.cc);
			EditText nomb= vie.findViewById(R.id.nombre);
			EditText tele= vie.findViewById(R.id.telefono);
			EditText email=vie.findViewById(R.id.email);
			sill= vie.findViewById(R.id.silla);
			Button b1= vie.findViewById(R.id.b1);
			peliculag=peli;
			b1.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{



						AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);

						final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.funciones, null);

						alertt.setView(vie)
							.setCancelable(false);
						alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int which) {

								}
							});


						titulof=alertt.create();
						titulof.setTitle("Funciones");
						titulof.show();
						
						recyclerView2 =(RecyclerView) vie.findViewById(R.id.listru);
						num.get(n1).getFunciones();
						pelii=n1;
						List<silla> tl = new ArrayList<>();
						try
						{
							JSONObject json= new JSONObject(num.get(n1).getFunciones());
							for(int b=0;b<json.length();b++){
								//n=""+json.getString("funcion"+(b+1));
								JSONObject j= new JSONObject(""+json.getString("funcion"+(b+1)));
								tl.add(new silla("funcion "+(b+1)+"\n   Dia: "+j.getString("dia")+"\n   Hora: "+j.getString("hora")+"\n   Sala: "+j.getString("sala")+""));


							}
						}
						catch (JSONException e)
						{}

						
						recyclerView2.setAdapter(new RecicleF(MainActivity.this, tl));
						animacion(recyclerView1);
						
						




					}


				});
			cedu.setText(""+cc);
			nomb.setText(""+nombre);
			tele.setText(""+tele1);
			email.setText(""+email1);
			
		}


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
			holder.funcion.setText("Funciones \n "+vusa.get(position).getFuncion());
			int n1=R.drawable.cargar;
			if(vusa.get(position).getImagen().equals("it2")){
			 n1=R.drawable.it2;
			}
			if(vusa.get(position).getImagen().equals("manosarmas")){
				n1=R.drawable.manosarmas;
			}
			if(vusa.get(position).getImagen().equals("bill")){
				n1=R.drawable.bill;
			}
			if(vusa.get(position).getImagen().equals("enola")){
				n1=R.drawable.enola;
			}
			if(vusa.get(position).getImagen().equals("power")){
				n1=R.drawable.power;
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
	
	
	
	
	
	
	public class RecicleF	
	extends RecyclerView.Adapter<RecicleF.ViewHolder> {
		private final MainActivity mParentActivity; 
		private final List<silla> vusa;		
		private Transition transicion;	
		
		public void sillasm(){
			

		}

		private final View.OnClickListener mOnClickListener = new View.OnClickListener() { 
			@Override public void onClick(View view) {
				conexion c=new conexion();
				
				desci=2;
				//g.cancel(true);
				
				funcinng="funcion"+(recyclerView2.getChildAdapterPosition(view)+1);
				//Toast.makeText(getApplicationContext(), ""+funcinng, Toast.LENGTH_LONG).show();
				MongoDatabase database = c.mongo.getDatabase("cine");
				final MongoCollection<Document> collection = database.getCollection("usuarios");

				g.cancel(true);
				gestion g1 = new gestion() ;
				g1.execute(collection);
				//sillasm();
				titulof.cancel();
				

			}
		};

		


		RecicleF(MainActivity parent,
				 List<silla> items) {
			vusa = items;
			mParentActivity = parent;

		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.fun, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(final ViewHolder holder, int position) {
			holder.funcion.setText(vusa.get(position).getSilla());

			holder.itemView.setTag(vusa.get(position));
			holder.itemView.setOnClickListener(mOnClickListener);
		}

		@Override
		public int getItemCount() {
			return vusa.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			final TextView funcion;
			



			ViewHolder(View view) {
				super(view);
				
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
			if(desci==3){
				insertarreserva(voids[0]);
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
			if(values[0].equals("1")){
				AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity1); 

				final View vie = LayoutInflater.from(mParentActivity1).inflate(R.layout.sillas, null); 

				alertt.setView(vie)
					.setCancelable(false)
					.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog,int which){
							sg.clear();
							sill.setText("");
							CheckBox A1=(CheckBox) vie.findViewById(R.id.A1);
							CheckBox A2= vie.findViewById(R.id.A2);
							CheckBox A3= vie.findViewById(R.id.A3);
							CheckBox A4= vie.findViewById(R.id.A4);
							CheckBox A5= vie.findViewById(R.id.A5);
							CheckBox A6= vie.findViewById(R.id.A6);
							CheckBox A7= vie.findViewById(R.id.A7);
							CheckBox B1= vie.findViewById(R.id.B1);
							CheckBox B2= vie.findViewById(R.id.B2);
							CheckBox B3= vie.findViewById(R.id.B3);
							CheckBox B4= vie.findViewById(R.id.B4);
							CheckBox B5= vie.findViewById(R.id.B5);
							CheckBox B6= vie.findViewById(R.id.B6);
							CheckBox B7= vie.findViewById(R.id.B7);
							CheckBox C1= vie.findViewById(R.id.C1);
							CheckBox C2= vie.findViewById(R.id.C2);
							CheckBox C3= vie.findViewById(R.id.C3);
							CheckBox C4= vie.findViewById(R.id.C4);
							CheckBox C5= vie.findViewById(R.id.C5);
							CheckBox C6= vie.findViewById(R.id.C6);
							CheckBox C7= vie.findViewById(R.id.C7);
							CheckBox D1= vie.findViewById(R.id.D1);
							CheckBox D2= vie.findViewById(R.id.D2);
							CheckBox D3= vie.findViewById(R.id.D3);
							CheckBox D4= vie.findViewById(R.id.D4);
							CheckBox D5= vie.findViewById(R.id.D5);
							CheckBox D6= vie.findViewById(R.id.D6);
							CheckBox D7= vie.findViewById(R.id.D7);
							CheckBox F1= vie.findViewById(R.id.F1);
							CheckBox F2= vie.findViewById(R.id.F2);
							CheckBox F3= vie.findViewById(R.id.F3);
							CheckBox F4= vie.findViewById(R.id.F4);
							CheckBox F5= vie.findViewById(R.id.F5);
							CheckBox F6= vie.findViewById(R.id.F6);
							CheckBox F7= vie.findViewById(R.id.F7);
							CheckBox G1= vie.findViewById(R.id.G1);
							CheckBox G2= vie.findViewById(R.id.G2);
							CheckBox G3= vie.findViewById(R.id.G3);
							CheckBox G4= vie.findViewById(R.id.G4);
							CheckBox G5= vie.findViewById(R.id.G5);
							CheckBox G6= vie.findViewById(R.id.G6);
							CheckBox G7= vie.findViewById(R.id.G7);
							
							if(A1.isChecked()){
								sg.add(new silla("A1"));
							}
							if(A2.isChecked()){
								sg.add(new silla("A2"));
							}
							if(A3.isChecked()){
								sg.add(new silla("A3"));
							}
							if(A4.isChecked()){
								sg.add(new silla("A4"));
							}
							if(A5.isChecked()){
								sg.add(new silla("A5"));
							}
							if(A6.isChecked()){
								sg.add(new silla("A6"));
							}
							if(A7.isChecked()){
								sg.add(new silla("A7"));
							}
							if(B1.isChecked()){
								sg.add(new silla("B1"));
							}
							if(B2.isChecked()){
								sg.add(new silla("B2"));
							}
							if(B3.isChecked()){
								sg.add(new silla("B3"));
							}
							if(B4.isChecked()){
								sg.add(new silla("B4"));
							}
							if(B5.isChecked()){
								sg.add(new silla("B5"));
							}
							if(B6.isChecked()){
								sg.add(new silla("B6"));
							}
							if(B7.isChecked()){
								sg.add(new silla("B7"));
							}
							if(C1.isChecked()){
								sg.add(new silla("C1"));
							}
							if(C2.isChecked()){
								sg.add(new silla("C2"));
							}
							if(C3.isChecked()){
								sg.add(new silla("C3"));
							}
							if(C4.isChecked()){
								sg.add(new silla("C4"));
							}
							if(C5.isChecked()){
								sg.add(new silla("C5"));
							}
							if(C6.isChecked()){
								sg.add(new silla("C6"));
							}
							if(C7.isChecked()){
								sg.add(new silla("C7"));
							}
							if(D1.isChecked()){
								sg.add(new silla("D1"));
							}
							if(D2.isChecked()){
								sg.add(new silla("D2"));
							}
							if(D3.isChecked()){
								sg.add(new silla("D3"));
							}
							if(D4.isChecked()){
								sg.add(new silla("D4"));
							}
							if(D5.isChecked()){
								sg.add(new silla("D5"));
							}
							if(D6.isChecked()){
								sg.add(new silla("D6"));
							}
							if(D7.isChecked()){
								sg.add(new silla("D7"));
							}
							if(F1.isChecked()){
								sg.add(new silla("F1"));
							}
							if(F2.isChecked()){
								sg.add(new silla("F2"));
							}
							if(F3.isChecked()){
								sg.add(new silla("F3"));
							}
							if(F4.isChecked()){
								sg.add(new silla("F4"));
							}
							if(F5.isChecked()){
								sg.add(new silla("F5"));
							}
							if(F6.isChecked()){
								sg.add(new silla("F6"));
							}
							if(F7.isChecked()){
								sg.add(new silla("F7"));
							}
							if(G1.isChecked()){
								sg.add(new silla("G1"));
							}
							if(G2.isChecked()){
								sg.add(new silla("G2"));
							}
							if(G3.isChecked()){
								sg.add(new silla("G3"));
							}
							if(G4.isChecked()){
								sg.add(new silla("G4"));
							}
							if(G5.isChecked()){
								sg.add(new silla("G5"));
							}
							if(G6.isChecked()){
								sg.add(new silla("G6"));
							}
							if(G7.isChecked()){
								sg.add(new silla("G7"));
							}
							
							
							
							
							
							
							for(int x=0;x<sg.size();x++){
								sill.setText(sill.getText()+"|"+sg.get(x).getSilla());
							}
							
							
							
							}
							});
				alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int which) { 

						}
					});


				final AlertDialog titulo=alertt.create();
				titulo.setTitle("Sillas de la Sala");
				titulo.show();
				CheckBox A1=(CheckBox) vie.findViewById(R.id.A1);
				CheckBox A2= vie.findViewById(R.id.A2);
				CheckBox A3= vie.findViewById(R.id.A3);
				CheckBox A4= vie.findViewById(R.id.A4);
				CheckBox A5= vie.findViewById(R.id.A5);
				CheckBox A6= vie.findViewById(R.id.A6);
				CheckBox A7= vie.findViewById(R.id.A7);
				CheckBox B1= vie.findViewById(R.id.B1);
				CheckBox B2= vie.findViewById(R.id.B2);
				CheckBox B3= vie.findViewById(R.id.B3);
				CheckBox B4= vie.findViewById(R.id.B4);
				CheckBox B5= vie.findViewById(R.id.B5);
				CheckBox B6= vie.findViewById(R.id.B6);
				CheckBox B7= vie.findViewById(R.id.B7);
				CheckBox C1= vie.findViewById(R.id.C1);
				CheckBox C2= vie.findViewById(R.id.C2);
				CheckBox C3= vie.findViewById(R.id.C3);
				CheckBox C4= vie.findViewById(R.id.C4);
				CheckBox C5= vie.findViewById(R.id.C5);
				CheckBox C6= vie.findViewById(R.id.C6);
				CheckBox C7= vie.findViewById(R.id.C7);
				CheckBox D1= vie.findViewById(R.id.D1);
				CheckBox D2= vie.findViewById(R.id.D2);
				CheckBox D3= vie.findViewById(R.id.D3);
				CheckBox D4= vie.findViewById(R.id.D4);
				CheckBox D5= vie.findViewById(R.id.D5);
				CheckBox D6= vie.findViewById(R.id.D6);
				CheckBox D7= vie.findViewById(R.id.D7);
				CheckBox F1= vie.findViewById(R.id.F1);
				CheckBox F2= vie.findViewById(R.id.F2);
				CheckBox F3= vie.findViewById(R.id.F3);
				CheckBox F4= vie.findViewById(R.id.F4);
				CheckBox F5= vie.findViewById(R.id.F5);
				CheckBox F6= vie.findViewById(R.id.F6);
				CheckBox F7= vie.findViewById(R.id.F7);
				CheckBox G1= vie.findViewById(R.id.G1);
				CheckBox G2= vie.findViewById(R.id.G2);
				CheckBox G3= vie.findViewById(R.id.G3);
				CheckBox G4= vie.findViewById(R.id.G4);
				CheckBox G5= vie.findViewById(R.id.G5);
				CheckBox G6= vie.findViewById(R.id.G6);
				CheckBox G7= vie.findViewById(R.id.G7); 

				





				for(int i=0;i<sii.size();i++){
//Toast.makeText(getApplicationContext(), ""+v[i], Toast.LENGTH_LONG).show();
					if(sii.get(i).getSilla().equals("A1")){
						
						A1.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("A2")){
						
						A2.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("A3")){
						
						A3.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("A4")){
						
						A4.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("A5")){
						
						A5.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("A6")){
						
						A6.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("A7")){
						
						A7.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("B1")){
						
						B1.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("B2")){
						
						B2.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("B3")){
						
						B3.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("B4")){
						
						B4.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("B5")){
						
						B5.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("B6")){
						
						B6.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("B7")){
						
						B7.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("C1")){
						
						C1.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("C2")){
					
						C2.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("C3")){
						
						C3.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("C4")){
						
						C4.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("C5")){
						
						C5.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("C6")){
						
						C6.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("C7")){
						
						C7.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("D1")){
						
						D1.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("D2")){
						
						D2.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("D3")){
						
						D3.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("D4")){
						
						D4.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("D5")){
						
						D5.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("D6")){
						
						D6.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("D7")){
						
						D7.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("F1")){
						
						F1.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("F2")){
						
						F2.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("F3")){
						
						F3.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("F4")){
						
						F4.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("F5")){
						
						F5.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("F6")){
						
						F6.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("F7")){
						
						F7.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("G1")){
						
						G1.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("G2")){
						
						G2.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("G3")){
						
						G3.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("G4")){
						
						G4.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("G5")){
						
						G5.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("G6")){
						
						G6.setEnabled(false);
					}
					if(sii.get(i).getSilla().equals("G7")){
						
						G7.setEnabled(false);
					}
				}
			}
			if(values[0].equals("0")){
				reclicle();
			}
			
			//nn.setText(""+values[0]);
			
			
			super.onProgressUpdate(values);
		}



		public void consultarDocumentos(MongoCollection<Document> collection){
			String n="";
			try{   
				n="entro00";
				ll.clear();
				vs.clear();
				usu.clear();
				FindIterable<Document> findIterable = collection.find();  
				MongoCursor<Document> mongoCursor = findIterable.iterator(); 
				conexion c = new conexion() ;
				DB db = c.mongo.getDB("cine");
				
				while(mongoCursor.hasNext()){  
				
					n= n+mongoCursor.next();
					n="entro000";
					
					//System.out.println(mongoCursor.next());  
				}  
				List<BasicDBObject> nnn1= (List<BasicDBObject>) db.getCollection("cartelera").find().toArray();
				n="entro :"+""+nnn1;
				for(int i=0;i<db.getCollection("cartelera").find().toArray().size();i++){

					List<BasicDBObject> nnn= (List<BasicDBObject>) db.getCollection("cartelera").find().toArray();

					JSONObject json= new JSONObject(nnn.get(i).get("funciones")+"");
					n="entro0"+json;
					
					n="";
					for(int b=0;b<json.length();b++){
						//n=""+json.getString("funcion"+(b+1));
						JSONObject j= new JSONObject(""+json.getString("funcion"+(b+1)));
						n=n+"funcion "+(b+1)+"\n   Dia: "+j.getString("dia")+"\n   Hora: "+j.getString("hora")+"\n   Sala: "+j.getString("sala")+"\n ";
		
						
					}
					

					num.add(new numef(json.length(),nnn.get(i).get("funciones")+""));
					//n=""+"Dia: "+json.getString("dia")+"\n Hora: "+json.getString("hora")+"\n Sala: "+json.getString("sala");
					//fun.add(new funcion(json.getString("dia"),json.getString("hora"),json.getString("sala")));
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
				n="error 2: "+e.getClass().getName() + ": " + e.getMessage()+"";
			}

			publishProgress("0");
		}
		
		public void consultarsillas(MongoCollection<Document> collection){
			String n="";
			sii.clear();
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
						
						JSONObject jo=new JSONObject(nnn.get(i).get("funcion")+"");
						if(jo.getString("nombre").equals(funcinng)){
							
							JSONObject jo1=new JSONObject(nnn.get(i).get("sillas")+"");
							for(int x=0;x<jo1.length();x++){
								sii.add(new silla(jo1.getString("silla"+(x+1))));
								n=n+jo1.getString("silla"+(x+1))+"\n";
							}
							
							n=n+"  "+jo1.length();
						}
						
					}
					
				}
				
				


			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				//return "";
				n=e.getClass().getName() + ": " + e.getMessage()+"";
			}
				//sillasm();
			publishProgress("1");
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
				Document document1 = new Document("nombre",funcinng)
					.append("dia",diag)
					.append("hora",holag)
					.append("sala",salag);
				Document sillas =new Document();
					
				for(int x=0;x<sg.size();x++){
					sillas.append("silla"+(x+1),sg.get(x).getSilla());
				}
					
					
				Document document = new Document("cedula", cedulag)                       //Se crea un nuevo documento
					.append("pelicula", peliculag)
					.append("sillas", sillas)
					.append("funcion", document1);

				collection.insertOne(document);                                         //Inserting document into the collection

			}catch(Exception e){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
			}    
		}   

		

		
	}      
	
}


