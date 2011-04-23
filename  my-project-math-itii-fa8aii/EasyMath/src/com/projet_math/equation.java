package com.projet_math;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class equation extends TabActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TabHost host=getTabHost();
        host.addTab(host.newTabSpec("un").setIndicator("X2").setContent(new Intent(equation.this,second_degre.class)));
        host.addTab(host.newTabSpec("deux").setIndicator("X3").setContent(new Intent(equation.this,troisieme_degre.class)));
	
    }
}