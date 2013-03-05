package com.nhaqp.attendance.view;



import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.nhaqp.attendance.R;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	EditText edtUsuario;
	EditText edtPassword;
	Button btnIngresar;
	Button btnSalir;
	String resultado;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
		btnIngresar = (Button) findViewById(R.id.btnIngresar);
        
    }
    
    public void loginAction(View view)
    {
    	String login=edtUsuario.getText().toString();
    	String contrasena=edtPassword.getText().toString();
    
    	
    	if (login.equals("") || contrasena.equals("")){
			Toast.makeText(LoginActivity.this, "Complete los datos.", Toast.LENGTH_SHORT).show();
			return;
		}
 
		final String NAMESPACE = "http://localhost/";
		final String METHOD_NAME = "login";
		final String SOAP_ACTION = "http://localhost/login";
		final String URL="http://10.0.2.2:8090/Servicios.asmx";
		
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				
		request.addProperty("codigo", login); 
		request.addProperty("contrasena", contrasena); 
			
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		
		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);

		try 
		{
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();	
		 	StrictMode.setThreadPolicy(policy);	 
			transporte.call(SOAP_ACTION, envelope);

			SoapPrimitive resSoap =(SoapPrimitive)envelope.getResponse();
			
			resultado = resSoap.toString();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		//PARA PROBAR SIN SERVER
		//resultado = "1|profesor";

    	
    	
   		if(resultado.equals(""))
   		{
    		Toast.makeText(LoginActivity.this, "Login o password inválidos", Toast.LENGTH_SHORT).show();
  		}
   		else
   		{
   			  String [] respuesta= obtenerDatosLogin(resultado);
   			
   		
   			
   			Toast.makeText(LoginActivity.this, "Bienvenido: " + respuesta[1], Toast.LENGTH_SHORT).show();
   			
   			// ir a pantalla principal
   			
   			Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
   			intent.putExtra("idProfesor",respuesta[0]);
   			startActivity(intent);
   			
   		}
   			
    }
    
    
    public void salirAction(View view)
    {
    	 Intent intent = new Intent(Intent.ACTION_MAIN);
    	 finish();
    	
    }

    public String[] obtenerDatosLogin(String resultado)
    {
    	int posSeparador = resultado.indexOf("|");
    	String[] datos=new String[2];
    	datos[0] = resultado.substring(0,posSeparador);
    	datos[1] = resultado.substring(posSeparador+1,resultado.length());
    	return datos;
    	
    }
    
    
}