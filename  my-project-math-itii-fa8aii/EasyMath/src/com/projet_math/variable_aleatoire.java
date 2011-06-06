package com.projet_math;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.ContentProviderOperation.Builder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class variable_aleatoire extends Activity implements OnClickListener, OnItemClickListener 
{
Button Resultat;
Button Add;
Button clear;
ArrayList<Float> tableau;
GridView Grid;

//les deux arrayList une pour les valeur et l'autre pour les fréquence associées à ces valeurs
ArrayList<Float>freq;
ArrayList<Float>valeur;

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	//on associe le layout
	setContentView(R.layout.variable_aleatoire);
	//on lie les boutons et le gridview au éléments du layout avec les id
	Resultat = (Button)findViewById(R.id.btnresultat);
	Add=(Button)findViewById(R.id.btnajouter);
	clear=(Button)findViewById(R.id.btntoutsupprimer);
	Grid=(GridView)findViewById(R.id.gridview);
	
	//on active les clics sur les boutons et sur les items du gridview
	Resultat.setOnClickListener(this);
	Add.setOnClickListener(this);
	clear.setOnClickListener(this);
	Grid.setOnItemClickListener(this);
	
	//on instancie notre arraylist qui servira de méthode pour le traitement des données du gridview
	tableau=new ArrayList();
	
}
@Override
public void onClick(View arg0) {
	// TODO Auto-generated method stub
	if(arg0==Add)
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
	        		   
		               Toast.makeText(variable_aleatoire.this,"Vous n'avez rien rentré", Toast.LENGTH_SHORT).show();
	        	       
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
	
	
	if(arg0==Resultat)
	{
		if(tableau.isEmpty())
			Toast.makeText(this, "Il n'y a aucune valeur rentrée", Toast.LENGTH_SHORT).show();
		else afficher_graph();
	}
	
	if(arg0 == clear)
	{
		if(tableau.isEmpty())
			Toast.makeText(this, "Il n'y a aucune valeur rentrée", Toast.LENGTH_SHORT).show();
		else 
		{
		android.app.AlertDialog.Builder warning = new AlertDialog.Builder(this);	
		warning.setTitle("Attention");
		warning.setCancelable(false);
		warning.setMessage("Etes vous sur de vouloir supprimer toutes les valeurs de la liste");
		 warning.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				supprimer_liste();
				rafraichir_liste();
				
			}	
		});
		
		 warning.setNegativeButton("Non", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		warning.show();
		}
		
	}
	
}
@Override
public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
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
               Toast.makeText(variable_aleatoire.this,"Vous n'avez rien rentré", Toast.LENGTH_SHORT).show();          
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
	// on rafraichit la liste
	rafraichir_liste();	
}

public void modifier_element(EditText valeur,int index)
{
	tableau.set(index, Float.valueOf(valeur.getText().toString()));
	rafraichir_liste();
}

public void supprimer_liste()
{
	tableau.clear();	
}

public void supprimer_element(int index)
{
	tableau.remove(index);
	rafraichir_liste();
}


//méthode pour rafraichir l'affichage de la liste
public void rafraichir_liste()
{
	// on créer un adapter avec la liste de veleurs
	ArrayAdapter<Float> adapter= new ArrayAdapter<Float>(variable_aleatoire.this,android.R.layout.simple_list_item_1,tableau);
	//on utilise l'adapter pour la gridview
	Grid.setAdapter(adapter);	
} 	

//fonction servant à afficher un graphique dans une webview avec comme données les fréquences de chaque valeur du tableau
public void afficher_graph()
{
/*tab_freq = new Float[tableau.size()][2];
Float valeur,frequence;
String resultat="";

for(int i=1;i<tableau.size()-1;i++)
	{
	if(tab_freq.length > 0)
	{	
		if(isAlready(tableau.get(i))==false)
			{
			tab_freq[i][0]=tableau.get(i);
			tab_freq[i][1]=new Float(frequence(tableau.get(i)));		
			}		
	}
	else
		{
			tab_freq[i][0]=tableau.get(i);
			tab_freq[i][1]=new Float(frequence(tableau.get(i)));		
		}
	}
*/valeur.clear();
  freq.clear();
	for(int i= 0;i < tableau.size();i++)
	{
	if(valeur.isEmpty())
		{
		valeur.add(tableau.get(i));
		freq.add(new Float(frequence(tableau.get(i))));
		}
	else
		if(isAlready(tableau.get(i))==false)
		{
		valeur.add(tableau.get(i));
		freq.add(new Float(frequence(tableau.get(i))));
		}
	}
}

//fonction permettant de savoir si une valeur est déja présente dans le tableau de frequence
public boolean isAlready(float value)
{
int i;

for(i=0; i< valeur.size();i++)
	{
	if(valeur.get(i) == value)
		{
		return true;
		}
	}
return false;
}

//fonction retournant la fréquence d'une valeur dans la liste
public int frequence(float valeur)
{
int i; 
int freq= 0;

for(i=0;i<tableau.size();i++)
	{
	if(tableau.get(i)==valeur)
		{
		freq++;
		}
	}
	
return freq;	
}

}
