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
					Intent intenet=new Intent(MainActivity.this,reservas.class);
					startActivity(intenet);
					
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
			@Override public void onClick(View view) {
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
																	final EditText sill= vie.findViewById(R.id.silla);
																	conexion c1=new conexion();
																	peliculag=peli;
																	desci=2;
																	//g.cancel(true);

																	MongoDatabase database1 = c.mongo.getDatabase("cine");
																	final MongoCollection<Document> collection1 = database1.getCollection("usuarios");

																	g.cancel(true);
																	gestion g2 = new gestion() ;
																	g2.execute(collection1);
																	Button b1= vie.findViewById(R.id.b1);

																	b1.setOnClickListener(new OnClickListener(){

																			@Override
																			public void onClick(View p1)
																			{



																				AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);

																				final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.sillas, null);

																				alertt.setView(vie)
																					.setCancelable(false);
																				alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
																						@Override
																						public void onClick(DialogInterface dialogInterface, int which) {

																						}
																					});


																				final AlertDialog titulo=alertt.create();
																				titulo.setTitle("Sillas de la Sala: "+sa);
																				titulo.show();
																				final CheckBox A1=(CheckBox) vie.findViewById(R.id.A1);
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

																				A1.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("A1");
																							titulo.cancel();
																						}


																					});
																				A2.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("A2");
																							titulo.cancel();
																						}


																					});
																				A3.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("A3");
																							titulo.cancel();
																						}


																					});
																				A4.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("A4");
																							titulo.cancel();
																						}


																					});
																				A5.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("A5");
																							titulo.cancel();
																						}


																					});
																				A6.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("A6");
																							titulo.cancel();
																						}


																					});
																				A7.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("A7");
																							titulo.cancel();
																						}


																					});
																				B1.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("B1");
																							titulo.cancel();
																						}


																					});
																				B2.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("B2");
																							titulo.cancel();
																						}


																					});
																				B3.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("B3");
																							titulo.cancel();
																						}


																					});
																				B4.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("B4");
																							titulo.cancel();
																						}


																					});
																				B5.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("B5");
																							titulo.cancel();
																						}


																					});
																				B6.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("B6");
																							titulo.cancel();
																						}


																					});
																				B7.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("B7");
																							titulo.cancel();
																						}


																					});
																				C1.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("C1");
																							titulo.cancel();
																						}


																					});
																				C2.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("C2");
																							titulo.cancel();
																						}


																					});
																				C3.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("C3");
																							titulo.cancel();
																						}


																					});
																				C4.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("C4");
																							titulo.cancel();
																						}


																					});
																				C5.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("C5");
																							titulo.cancel();
																						}


																					});
																				C6.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("C6");
																							titulo.cancel();
																						}


																					});
																				C7.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("C7");
																							titulo.cancel();
																						}


																					});
																				D1.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("D1");
																							titulo.cancel();
																						}


																					});
																				D2.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("D2");
																							titulo.cancel();
																						}


																					});
																				D3.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("D3");
																							titulo.cancel();
																						}


																					});
																				D4.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("D4");
																							titulo.cancel();
																						}


																					});
																				D5.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("D5");
																							titulo.cancel();
																						}


																					});
																				D6.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("D6");
																							titulo.cancel();
																						}


																					});
																				D7.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("D7");
																							titulo.cancel();
																						}


																					});
																				F1.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("F1");
																							titulo.cancel();
																						}


																					});
																				F2.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("F2");
																							titulo.cancel();
																						}


																					});

																				F3.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("F3");
																							titulo.cancel();
																						}


																					});

																				F4.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("F4");
																							titulo.cancel();
																						}


																					});

																				F5.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("F5");
																							titulo.cancel();
																						}


																					});

																				F6.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("F6");
																							titulo.cancel();
																						}


																					});

																				F7.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("F7");
																							titulo.cancel();
																						}


																					});
																				G1.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("G1");
																							titulo.cancel();
																						}


																					});
																				G2.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("G2");
																							titulo.cancel();
																						}


																					});
																				G3.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("G3");
																							titulo.cancel();
																						}


																					});
																				G4.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("G4");
																							titulo.cancel();
																						}


																					});
																				G5.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("G5");
																							titulo.cancel();
																						}


																					});
																				G6.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("G6");
																							titulo.cancel();
																						}


																					});
																				G7.setOnClickListener(new OnClickListener(){

																						@Override
																						public void onClick(View p1)
																						{
																							A1.setChecked(true);
																							sill.setText("G7");
																							titulo.cancel();
																						}


																					});





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
																					if(sii.get(i).getSilla().equals("A4")){
																						A4.setChecked(true);
																						A4.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("A5")){
																						A5.setChecked(true);
																						A5.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("A6")){
																						A6.setChecked(true);
																						A6.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("A7")){
																						A7.setChecked(true);
																						A7.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("B1")){
																						B1.setChecked(true);
																						B1.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("B2")){
																						B2.setChecked(true);
																						B2.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("B3")){
																						B3.setChecked(true);
																						B3.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("B4")){
																						B4.setChecked(true);
																						B4.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("B5")){
																						B5.setChecked(true);
																						B5.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("B6")){
																						B6.setChecked(true);
																						B6.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("B7")){
																						B7.setChecked(true);
																						B7.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("C1")){
																						C1.setChecked(true);
																						C1.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("C2")){
																						C2.setChecked(true);
																						C2.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("C3")){
																						C3.setChecked(true);
																						C3.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("C4")){
																						C4.setChecked(true);
																						C4.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("C5")){
																						C5.setChecked(true);
																						C5.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("C6")){
																						C6.setChecked(true);
																						C6.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("C7")){
																						C7.setChecked(true);
																						C7.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("D1")){
																						D1.setChecked(true);
																						D1.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("D2")){
																						D2.setChecked(true);
																						D2.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("D3")){
																						D3.setChecked(true);
																						D3.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("D4")){
																						D4.setChecked(true);
																						D4.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("D5")){
																						D5.setChecked(true);
																						D5.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("D6")){
																						D6.setChecked(true);
																						D6.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("D7")){
																						D7.setChecked(true);
																						D7.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("F1")){
																						F1.setChecked(true);
																						F1.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("F2")){
																						F2.setChecked(true);
																						F2.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("F3")){
																						F3.setChecked(true);
																						F3.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("F4")){
																						F4.setChecked(true);
																						F4.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("F5")){
																						F5.setChecked(true);
																						F5.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("F6")){
																						F6.setChecked(true);
																						F6.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("F7")){
																						F7.setChecked(true);
																						F7.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("G1")){
																						G1.setChecked(true);
																						G1.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("G2")){
																						G2.setChecked(true);
																						G2.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("G3")){
																						G3.setChecked(true);
																						G3.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("G4")){
																						G4.setChecked(true);
																						G4.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("G5")){
																						G5.setChecked(true);
																						G5.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("G6")){
																						G6.setChecked(true);
																						G6.setEnabled(false);
																					}
																					if(sii.get(i).getSilla().equals("G7")){
																						G7.setChecked(true);
																						G7.setEnabled(false);
																					}
																				}




																			}


																		});
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
											final EditText sill= vie.findViewById(R.id.silla);
											Button b1= vie.findViewById(R.id.b1);

											b1.setOnClickListener(new OnClickListener(){

													@Override
													public void onClick(View p1)
													{
														
														
														
														AlertDialog.Builder alertt= new AlertDialog.Builder(mParentActivity);

														final View vie = LayoutInflater.from(mParentActivity).inflate(R.layout.sillas, null);

														alertt.setView(vie)
															.setCancelable(false);
														alertt.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
																@Override
																public void onClick(DialogInterface dialogInterface, int which) {

																}
															});


														final AlertDialog titulo=alertt.create();
														titulo.setTitle("Sillas de la Sala: "+sa);
														titulo.show();
														final CheckBox A1=(CheckBox) vie.findViewById(R.id.A1);
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
														
														A1.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("A1");
																	titulo.cancel();
																}
																
															
														});
														A2.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("A2");
																	titulo.cancel();
																}


															});
														A3.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("A3");
																	titulo.cancel();
																}


															});
														A4.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("A4");
																	titulo.cancel();
																}


															});
														A5.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("A5");
																	titulo.cancel();
																}


															});
														A6.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("A6");
																	titulo.cancel();
																}


															});
														A7.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("A7");
																	titulo.cancel();
																}


															});
														B1.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("B1");
																	titulo.cancel();
																}


															});
														B2.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("B2");
																	titulo.cancel();
																}


															});
														B3.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("B3");
																	titulo.cancel();
																}


															});
														B4.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("B4");
																	titulo.cancel();
																}


															});
														B5.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("B5");
																	titulo.cancel();
																}


															});
														B6.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("B6");
																	titulo.cancel();
																}


															});
														B7.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("B7");
																	titulo.cancel();
																}


															});
														C1.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("C1");
																	titulo.cancel();
																}


															});
														C2.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("C2");
																	titulo.cancel();
																}


															});
														C3.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("C3");
																	titulo.cancel();
																}


															});
														C4.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("C4");
																	titulo.cancel();
																}


															});
														C5.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("C5");
																	titulo.cancel();
																}


															});
														C6.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("C6");
																	titulo.cancel();
																}


															});
														C7.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("C7");
																	titulo.cancel();
																}


															});
														D1.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("D1");
																	titulo.cancel();
																}


															});
														D2.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("D2");
																	titulo.cancel();
																}


															});
														D3.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("D3");
																	titulo.cancel();
																}


															});
														D4.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("D4");
																	titulo.cancel();
																}


															});
														D5.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("D5");
																	titulo.cancel();
																}


															});
														D6.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("D6");
																	titulo.cancel();
																}


															});
														D7.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("D7");
																	titulo.cancel();
																}


															});
														F1.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("F1");
																	titulo.cancel();
																}


															});
														F2.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("F2");
																	titulo.cancel();
																}


															});
														
														F3.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("F3");
																	titulo.cancel();
																}


															});
														
														F4.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("F4");
																	titulo.cancel();
																}


															});
														
														F5.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("F5");
																	titulo.cancel();
																}


															});
														
														F6.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("F6");
																	titulo.cancel();
																}


															});
														
														F7.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("F7");
																	titulo.cancel();
																}


															});
														G1.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("G1");
																	titulo.cancel();
																}


															});
														G2.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("G2");
																	titulo.cancel();
																}


															});
														G3.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("G3");
																	titulo.cancel();
																}


															});
														G4.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("G4");
																	titulo.cancel();
																}


															});
														G5.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("G5");
																	titulo.cancel();
																}


															});
														G6.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("G6");
																	titulo.cancel();
																}


															});
														G7.setOnClickListener(new OnClickListener(){

																@Override
																public void onClick(View p1)
																{
																	A1.setChecked(true);
																	sill.setText("G7");
																	titulo.cancel();
																}


															});
														

														
														
														
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
															if(sii.get(i).getSilla().equals("A4")){
																A4.setChecked(true);
																A4.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("A5")){
																A5.setChecked(true);
																A5.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("A6")){
																A6.setChecked(true);
																A6.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("A7")){
																A7.setChecked(true);
																A7.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("B1")){
																B1.setChecked(true);
																B1.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("B2")){
																B2.setChecked(true);
																B2.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("B3")){
																B3.setChecked(true);
																B3.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("B4")){
																B4.setChecked(true);
																B4.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("B5")){
																B5.setChecked(true);
																B5.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("B6")){
																B6.setChecked(true);
																B6.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("B7")){
																B7.setChecked(true);
																B7.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("C1")){
																C1.setChecked(true);
																C1.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("C2")){
																C2.setChecked(true);
																C2.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("C3")){
																C3.setChecked(true);
																C3.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("C4")){
																C4.setChecked(true);
																C4.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("C5")){
																C5.setChecked(true);
																C5.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("C6")){
																C6.setChecked(true);
																C6.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("C7")){
																C7.setChecked(true);
																C7.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("D1")){
																D1.setChecked(true);
																D1.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("D2")){
																D2.setChecked(true);
																D2.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("D3")){
																D3.setChecked(true);
																D3.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("D4")){
																D4.setChecked(true);
																D4.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("D5")){
																D5.setChecked(true);
																D5.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("D6")){
																D6.setChecked(true);
																D6.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("D7")){
																D7.setChecked(true);
																D7.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("F1")){
																F1.setChecked(true);
																F1.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("F2")){
																F2.setChecked(true);
																F2.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("F3")){
																F3.setChecked(true);
																F3.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("F4")){
																F4.setChecked(true);
																F4.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("F5")){
																F5.setChecked(true);
																F5.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("F6")){
																F6.setChecked(true);
																F6.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("F7")){
																F7.setChecked(true);
																F7.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("G1")){
																G1.setChecked(true);
																G1.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("G2")){
																G2.setChecked(true);
																G2.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("G3")){
																G3.setChecked(true);
																G3.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("G4")){
																G4.setChecked(true);
																G4.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("G5")){
																G5.setChecked(true);
																G5.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("G6")){
																G6.setChecked(true);
																G6.setEnabled(false);
															}
															if(sii.get(i).getSilla().equals("G7")){
																G7.setChecked(true);
																G7.setEnabled(false);
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
			reclicle();
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
					JSONObject jo=null;
					JSONObject jo1=null;
					n="";
					for(int b=0;b<json.length();b++){
						//n=""+json.getString("funcion"+(b+1));
						JSONObject j= new JSONObject(""+json.getString("funcion"+(b+1)));
						n=n+"funcion "+(b+1)+"\n   Dia: "+j.getString("dia")+"\n   Hora: "+j.getString("hora")+"\n   Sala: "+j.getString("sala")+"\n ";
		
						
					}


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

			publishProgress(n);
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


