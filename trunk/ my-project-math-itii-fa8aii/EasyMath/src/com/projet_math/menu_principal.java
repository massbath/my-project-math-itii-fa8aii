package com.projet_math;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class menu_principal extends TabActivity  {

	
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TabHost host= getTabHost();
        host.addTab(host.newTabSpec("Outils").setIndicator("Outils", getResources().getDrawable(R.drawable.outils)).setContent(new Intent(menu_principal.this,outils_math.class)));
        host.addTab(host.newTabSpec("Cours").setIndicator("Cours",getResources().getDrawable(R.drawable.cours)).setContent(new Intent(menu_principal.this,menu_cours.class)));
       
        	
         	
        		
    }
    
        
}