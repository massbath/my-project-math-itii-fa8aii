package com.projet_math;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class probabilite extends Activity implements OnClickListener
{

	Button uniforme;
	Button binomiale;
	Button poisson;
	Button normal;
	Button khi;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.probabilte_menu);
		
		uniforme=(Button)findViewById(R.id.btnuniforme);
		binomiale=(Button)findViewById(R.id.btnbinomiale);
		poisson=(Button)findViewById(R.id.btnpoisson);
		normal=(Button)findViewById(R.id.btnnormal);
		khi=(Button)findViewById(R.id.btnkhi);
		
		uniforme.setOnClickListener(this);
		binomiale.setOnClickListener(this);
		poisson.setOnClickListener(this);
		normal.setOnClickListener(this);
		khi.setOnClickListener(this);
		
		
		
	}


	@Override
	public void onClick(View view) 
	{
		// TODO Auto-generated method stub
	if(view == uniforme)
		{
		message("Loi Uniforme");
		}
	
	if(view == binomiale)
		{
		message("Loi Binomiale");
		}
	
	if(view == poisson)
		{
		message("Loi poisson");
		}
	
	if(view == normal)
		{
		message("Loi normale");
		}
	
	if(view == khi)
		{
		message("Test de KHI");
		}
	}
public void message(String text)
	{
	Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
	
	
}
