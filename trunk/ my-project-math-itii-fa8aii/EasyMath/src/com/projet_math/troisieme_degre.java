package com.projet_math;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class troisieme_degre extends Activity {
    /** Called when the activity is first created. */
	
	EditText x3;
	EditText x2;
	EditText x1;
	EditText x0;
	EditText constante;
	Button btnresoudre;
	TextView solution1;
	TextView solution2;
	
	float delta;
	float a,b,c,d,e;
	float sol1,sol2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.troisieme_degre);
        x3=(EditText)findViewById(R.id.eTx3);
        x2=(EditText)findViewById(R.id.eTx2);
        x1=(EditText)findViewById(R.id.eTx1);
        x0=(EditText)findViewById(R.id.eTx0);
        constante=(EditText)findViewById(R.id.eTconstante);
        btnresoudre=(Button)findViewById(R.id.btnresolution);
        solution1=(TextView)findViewById(R.id.tVsolution1);
        solution2=(TextView)findViewById(R.id.tVsolution2);
        
        btnresoudre.setOnClickListener(new View.OnClickListener() {
       	public void onClick(View v) {
       		if((x3.getText().length()!=0) && (x2.getText().length()!=0) && (x1.getText().length()!=0) && (x0.getText().length()!=0) && (constante.getText().length()!=0))	
       		{
      
       			resolution();	
       		}
       			else Toast.makeText(troisieme_degre.this, "Un des champs n'a pas ŽtŽ rempli", Toast.LENGTH_LONG).show();
    	}
    });
    }
    
    
    private void resolution()
    {
    	solution1.setText("");
    	solution2.setText("");
    	
    	float p,k,q,gDelta,m,n,u,v,x,y,re,im,theta,z;
    	
    	    
    	    a=Float.parseFloat(x3.getText().toString());
    	    b=Float.parseFloat(x2.getText().toString());
    	    c=Float.parseFloat(x1.getText().toString());
    	    d=Float.parseFloat(x0.getText().toString());
    	    e=Float.parseFloat(constante.getText().toString());
    	    d=d-e;
    	    
    	    p=(float)((c/a)-(Math.pow(b, 2.0)/(3*Math.pow(a, 2.0))));
			q=(float) (((2*Math.pow(b, 3.0))/(27*Math.pow(a, 3.0)))-((b*c)/(3*Math.pow(a, 2.0)))+(d/a));
			gDelta=(float) (4*Math.pow(p, 3.0)+27*Math.pow(q, 2.0));
     		m=(float) (((-q)/2)+(0.5)*Math.sqrt(gDelta/27));
     		n=(float) (((-q)/2)-(0.5)*Math.sqrt(gDelta/27));
    	    
     		u=(float) Math.pow(Math.abs(m), 1.0/3);
     		v=(float) Math.pow(Math.abs(n), 1.0/3);
    	    
     		if(gDelta>0)
		  		  {
		  			  if (m<0) u=-u;
		  			  if (n<0) v=-v;
		  			  x=u+v;
		  			  x+=(-b)/(3*a);
		  			  re=(-x)/2;
		  			  im=(float) ((Math.sqrt(3)/2)*(u-v));
		  			  re+=(-b)/(3*a);
		  			  //3 solutions
		  			  solution1.setText("Il y a une racine rŽelle: " +x+"\n"+"Il y a deux racines complexes: \n"+"y = "+re+"-"+im+"i ,\n z  = "+re+"+"+im+"i");
		  			  
		  			 	  
		  		  }
     		else if(gDelta==0)
						{
						  
						  if(b==0 && c==0 && d==0)
							  solution1.setText("Il y a une racine rŽelle de multiplicitŽ 3 :x=y=z=0");
						  else
							  {
								x=(3*q)/p;
								x+=(-b)/(3*a);
								y=(-3*q)/(2*p);
								y+=(-b)/(3*a);
								solution1.setText("Il y a une racine rŽelle simple:"+" "+x+"\n"+"Il y a une racine rŽelle double y = z="+" "+y);
								
							  }
							
						}
     		else
  		  {
  			  k=  (float) ((3*q)/((2*p)*Math.sqrt((-p)/3)));               
  			  theta=(float) Math.acos(k);
  			  x= (float) (2*Math.sqrt((-p)/3)*Math.cos(theta/3));
  			  y= (float) (2*Math.sqrt((-p)/3)*Math.cos((theta+2*Math.PI)/3));
  			  z= (float) (2*Math.sqrt((-p)/3)*Math.cos((theta+4*Math.PI)/3));
 
  			  x+=(-c)/(3*b);
  			  y+=(-c)/(3*b);
  			  z+=(-c)/(3*b);
  			  solution1.setText("Il y a 3 racines rŽelles "+x+" \n"+" "+ y +"\n" +z);
  			
  			   
  		  }
     		
    }
}