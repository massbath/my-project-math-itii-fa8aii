package com.projet_math;


import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class statistique extends Activity implements OnClickListener, OnItemClickListener
{
	
	Button ajout;
	Button clearall;
	Button moyenne;
	Button ecarttype;
	Button mode;
	Button mediane;
	TextView affichage_stat;
	ArrayList<Float> tableau;
	GridView Grid;
     
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		//on lie le layout de l'activité 
		setContentView(R.layout.statistique);
		affichage_stat=(TextView)findViewById(R.id.tvResultatstat);
		
		//on instancie les views  et on le lie avec l'id correspondant dans le layout
		ajout=(Button)findViewById(R.id.btnajout);
		//on met un écouteur sur le clic
		ajout.setOnClickListener(this);
		clearall=(Button)findViewById(R.id.btnclearall);
		clearall.setOnClickListener(this);
		
		//on instancie le tableau qui contiendra les valeurs entrées par l'utilisateur
	    tableau=new ArrayList();
	    
	    
	    
		
	    //on instancie la grid view qui nous permettra d'afficher les valeurs du tableau
	    Grid=(GridView)findViewById(R.id.gridview);
	    Grid.setOnItemClickListener(this);
		rafraichir_liste();
	    
		//les boutons pour les différents calculs de stat
		moyenne=(Button)findViewById(R.id.btnmoyenne);
		moyenne.setOnClickListener(this);
		
		ecarttype=(Button)findViewById(R.id.btnecarttype);
		ecarttype.setOnClickListener(this);
		
	    mode=(Button)findViewById(R.id.btnmode);
	    mode.setOnClickListener(this);
	    
	    mediane=(Button)findViewById(R.id.btnmediane);
	    mediane.setOnClickListener(this);
	
	
}

	
	//quand on clique sur un bouton
	@Override
	public void onClick(View v) 
	{
		if(v==ajout)//si on clique sur le bouton ajou
			{
			//on transforme le layout alertdialogsaisievaleur pour en créer une vue qui sera la vue de l'alertdialog
		    LayoutInflater factory = LayoutInflater.from(this);
	        final View alertDialogView = factory.inflate(R.layout.alertdialogsaisievaleur, null);
	        
		    //création de l'alertdialog
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    
			builder.setMessage("Entrez une valeur");//le texte de l'AlertDialog
			builder.setView(alertDialogView);//la vue associée
			builder.setCancelable(false);//on peut annuler l'alertdialog
			
		    //quand on clique sur le bouton valide de l'alertdialog   
			builder.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int which) {
		               
		            
		              // ajouter_element(valeur.getText().toString());
		        	   EditText valeur=(EditText)alertDialogView.findViewById(R.id.etvaleuraajouter);
		        	   if(valeur.getText().length()==0)
		        	     {
		        		   rafraichir_liste();
		        		   
			               Toast.makeText(statistique.this,"Vous n'avez rien rentré", Toast.LENGTH_SHORT).show();
		        	       
		        	     }
			        	   else ajouter_element(valeur);
		           }
		       });
			
			//quand on clique sur le bouton annuler
		    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int which) {
		                dialog.cancel();
		           }
		       });;
			builder.show();
			}
		if(v==clearall)//si on clique sur le bouton clearall
		{
			
		if(tableau.isEmpty())
		    	Toast.makeText(this, "Il n'y a aucune valeur à supprimer", Toast.LENGTH_LONG).show();
		else{		
		//on créer une alert dialog pour valider l'action de l'utilisateur
		Builder warning = new AlertDialog.Builder(this);
		warning.setTitle("Attention !!");
		warning.setMessage("Etes vous sur de vouloir supprimer toute la liste");
		warning.setCancelable(false);
		//si on clique sur oui alors
		 warning.setPositiveButton("Oui", new DialogInterface.OnClickListener()
		 {
			public void onClick(DialogInterface dialog, int which) {
				//on supprime la liste, et on rafraichi la vue
				supprimer_liste();
				rafraichir_liste();
			}
		});
		 //si on clique sur le bouton non
		 warning.setNegativeButton("Non", new DialogInterface.OnClickListener()
		 {
			public void onClick(DialogInterface dialog, int which) 
			{
				//on ferme la boite de dialogue sans rien faire
			dialog.cancel();
			}
		});
		
		warning.show();//permet de rendre l'alertdialog visible
		}
		}
		//si on clique sur moyenne
		if(v==moyenne)
		{
			//si il n'y a aucune valeur on averti l'utilisateur et on ne fait rien
			if(tableau.isEmpty()) Toast.makeText(statistique.this, "Il n'y aucune valeur dans la liste impossible de faire une moyenne", Toast.LENGTH_SHORT).show();
			//sinon on lance la méthode permettant de faire la moyenne
			else moyenne_liste();
		}
		//si on clique sur le bouton mode
		if(v==mode)
		{
			//si il n'y a aucune valeur on averti l'utilisateur et on ne fait rien
			if(tableau.isEmpty()) Toast.makeText(statistique.this, "Il n'y aucune valeur dans la liste impossible de faire une mode", Toast.LENGTH_SHORT).show();
			//sinon on lance la méthode permettant de calculer le mode
			else mode_liste();
		}
		
		//si on clique sur le bouton mediane
		if(v==mediane)
		{
			//si il n'y a aucune valeur on averti l'utilisateur et on ne fait rien
			if(tableau.isEmpty()) Toast.makeText(statistique.this, "Il n'y aucune valeur dans la liste impossible de faire une médiane", Toast.LENGTH_SHORT).show();
			//sinon on lance la méthode permettant de calculer la médiane 
			else mediane_liste();
		}
		
		//si on clique sur le bouton ecarttype
		if(v==ecarttype)
		{
			//si il n'y a aucune valeur on averti l'utilisateur et on ne fait rien
			if(tableau.isEmpty()) Toast.makeText(statistique.this, "Il n'y aucune valeur dans la liste impossible de faire un écart type", Toast.LENGTH_SHORT).show();
			//sinon on lance la méthode pour calculer l'écart type
			else if (tableau.size()==1) Toast.makeText(statistique.this, "Il n'y a qu'un élément dans la liste impossible de faire un écart type", Toast.LENGTH_SHORT).show();
				else ecarttype_liste();
		}
		
		
	}
	
/*méthode pour faire la moyenne, on parcourt l'arraylist en faisant la somme des valeurs puis on divise par le nb de valeurs*/
	private void moyenne_liste() 
	{
		float moyenne=0;
		int i;
		//on fait la somme de l'ensemble des valeurs du tableau à l'aide d'un parcours
	for(i=0;i<tableau.size();i++)
		{
		moyenne+=tableau.get(i);
		}
	//on divise cette somme par le nb d'éléments du tableau
	moyenne=moyenne/(float)tableau.size();
	//on affiche le résultat
	affichage_stat.setText("La moyenne de ces valeurs est :\n\t"+ Float.toString(moyenne));	
	}
	
	
/*méthode permettant de faire l'écart type*/	
	private void ecarttype_liste() 
	{
		float somme=0,moyenne=0,somme_des_carres=0;
		float variance_population,ecart_type_population,variance_echantillon,ecart_type_echantillon;
		
		//on calcule la moyenne
		 for (int i=0;i<tableau.size();i++)
		 {
		 somme+= tableau.get(i);
		 }
		 moyenne=somme/tableau.size();
		 
		//on fait maintenant la somme des carrés
	    for(int j=0; j<tableau.size(); j++)
	    {
	        somme_des_carres = (float) (somme_des_carres +Math.pow((double)(tableau.get(j)- moyenne), 2));
	    }
	    
	variance_population=somme_des_carres/tableau.size();
	ecart_type_population=(float) Math.sqrt((double)variance_population);
	
	variance_echantillon=somme_des_carres/(tableau.size()-1);
	ecart_type_echantillon=(float) Math.sqrt((double)variance_echantillon);
	     
	//affichange des varaiances et des écart types	
	affichage_stat.setText("La variance de ces valeurs est : \n\t"
			+Float.toString(variance_population)+"(population)\n\t"
			+Float.toString(variance_echantillon)+"(échantillon)\n\n\n"
			+"L'écart type de ces valeurs est de : \n\t"
			+ Float.toString(ecart_type_population)+"(population)\n\t" 
			+Float.toString(ecart_type_echantillon)+"(échantillon)");
	}
	
	
	//fonction permettant de calculer la médiane
	private void mediane_liste() 
	{
	//pour calculer la médiane première chose à faire, trier le tableau par ordre croissant.
	
	float mediane=0;
	//on tri notre liste en utilisant la méthode sort de la classe collection
	Collections.sort(tableau);	
	
	//si le nb de valeur est pair alors on prend médiane (nb élément/2 + nb élément/2+1 )/2
	if((tableau.size()%2)==0)
		{
		//enlever un à tableau.size() car tab commence à 0	
		mediane=(float)(tableau.get((tableau.size()-1)/2)+tableau.get((tableau.size()-1)/2+1))/2;
		}
	else 
		{
		//sinon si c'est impaire alors médiane = nb élément /2
		mediane=(float)(tableau.get((tableau.size()-1)/2));
		}
    //on rafraichit la liste et affiche la médiane 
	rafraichir_liste();
	affichage_stat.setText("La médiane de ces valeurs est :\n\t"+ Float.toString(mediane));
		
	}
	
	//méthode permettant de calculer le mode
/*Pour chaque valeur de la liste on cherche sa fréquence grâce à la fonction frequence
  si c'est la première valeur on initialise les valeur compteur_max et mode avec celle de 
  la première valeur de la liste, si la freq d'une est supérieur au compteur max alors c'est el mode.
  Et le dernier cas si deux valeurs ont la même freq, le mode est al plus grande des deux valeurs*/	
public void mode_liste()
{
int freq,compteur_max=0;
float mode=0;
	
for(int i=0;i<tableau.size();i++)
	{
	
	freq=frequence(tableau.get(i));
	
	if((i==0) || (freq>compteur_max) || ((freq==compteur_max) && mode<tableau.get(i)) )
	{
	mode=tableau.get(i);
	compteur_max=freq;
	}
	
	}
	affichage_stat.setText("Le mode de ces valeurs est :\n\t"+ Float.toString(mode));
	
}
//fonction retournant la fréquence d'une valeur dans la liste
public int frequence(float valeur)
{
int i, freq=0;

for(i=0;i<tableau.size();i++)
	{
	if(tableau.get(i)==valeur)
		{
		freq++;
		}
	}
	
return freq;	
}
	
	
/*si on clique sur une valeur présente dans le grid view une alertdialog permettant
  à l'utilisateur de pouvoir modifier une valeur, supprimer une valeur, ou annuler l'action*/
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.alertdialogmodifiervaleur, null);
        
	    //création de l'alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    
		builder.setMessage("Que voulez vous faire ?");//le texte de l'AlertDialog
		builder.setView(alertDialogView);//la vue associée
		builder.setCancelable(false);//on peut annuler l'alertdialog
		
	    //action quand on clique sur le bouton modifier
		builder.setPositiveButton("Modifier", new DialogInterface.OnClickListener() 
		{
			
	           public void onClick(DialogInterface dialog, int which) 
	           {
	            //on récupère la valeur entrez dasn l'alertdialog
	        	   EditText valeur=(EditText)alertDialogView.findViewById(R.id.eTnewvalueAD);
	        	   
	        	   //si il n'y a rien on le signele et on ferme la fenêtre
	        	   if(valeur.getText().length()==0)
	        	   {
	               Toast.makeText(statistique.this,"Vous n'avez rien rentré", Toast.LENGTH_SHORT).show();          
	        	   rafraichir_liste();
	        	   }
	        	   //sinon on lance  la méthode modifier_element, avec comme argument la valuer rentré par l'utilisateur et la position de la valeur dans le gridview
	               else modifier_element(valeur,arg2);
	           }
	    });
		
		//le bouton supprimer
		builder.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() 
		{
			//si on clique dessus
			public void onClick(DialogInterface dialog, int which)
			{
				//on lance la méthode pour supprimer l'élément avec en argument la position de l'élément
				supprimer_element(arg2);
			}
		});
		
		//le bouton annuler
	    builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() 
	    {
	    	//si on clique sur le bouton annuler alors on fermer l'alert dialog
	           public void onClick(DialogInterface dialog, int which) 
	           {
	                dialog.cancel();
	           }
	       });;
		builder.show();
		}
		
	
	
	//méthode pour ajouter un élément dans la liste
	public void ajouter_element(EditText a)
	{
		//on ajoute la valeur a au tableau
		tableau.add(Float.valueOf(a.getText().toString()));
		//on réinitilise le textview servant à afficher les stats
		affichage_stat.setText("");
		// on rafraichit la liste
		rafraichir_liste();
		
	}
	
	//méthode pour supprimer un élement de la liste avec comme paramètre sa position
	public void supprimer_element(int position)
	{
		//on supprime l'élément grâce àa la position
		tableau.remove(position);
		//on réinitilise le textview servant à afficher les stats
		affichage_stat.setText("");
		// on rafraichit la liste
		rafraichir_liste();
	}
	
	//méthode pour modifier un élément paramètre: une valeur et une position
	public void modifier_element(EditText a,int position)
	{
	//on change la valeur à la position donné par la valeur passée en paramètre.
	tableau.set(position,(Float.valueOf(a.getText().toString())));	
	//on réinitilise le textview servant à afficher les stats
	affichage_stat.setText("");
	// on rafraichit la liste
	rafraichir_liste();
	}
	
	//méthode pour rafraichir l'affichage de la liste
	public void rafraichir_liste()
	{
		// on créer un adapter avec la liste de veleurs
		ArrayAdapter<Float> adapter= new ArrayAdapter<Float>(statistique.this,android.R.layout.simple_list_item_1,tableau);
		//on utilise l'adapter pour la gridview
		Grid.setAdapter(adapter);
		
	} 
	
	
	//méthode pour supprimer toute les valeurs de la liste
	public void supprimer_liste()
	{
	//on efface l'intégralité de la liste ainsi que le textview affichant les stats.
    tableau.clear();	
	affichage_stat.setText("");
	}


	
	

}
