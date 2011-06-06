package com.projet_math;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class menu_cours extends Activity implements OnItemClickListener
{
	private ListView maListViewPerso;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        //R√©cup√©ration de la listview cr√©√©e dans le fichier main.xml
        maListViewPerso = (ListView) findViewById(R.id.listviewperso);
 
        //Cr√©ation de la ArrayList qui nous permettra de remplir la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
 
        //On d√©clare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
 
        //Cr√©ation d'une HashMap pour ins√©rer les informations du premier item de notre listView
        map = new HashMap<String, String>();
        //on ins√®re un √©l√©ment titre que l'on r√©cup√©rera dans le textView titre cr√©√© dans le fichier affichageitem.xml
        map.put("titre", "Probabilité");
        //on ins√®re un √©l√©ment description que l'on r√©cup√©rera dans le textView description cr√©√© dans le fichier affichageitem.xml
        map.put("description", "Probabilité conditionnelle, variable aléatoire, lois discrétes, loi continue.");
        //on ins√®re la r√©f√©rence √† l'image (convertit en String car normalement c'est un int) que l'on r√©cup√©rera dans l'imageView cr√©√© dans le fichier affichageitem.xml
        map.put("img", String.valueOf(R.drawable.probabilite));
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);
 
        //On refait la manip plusieurs fois avec des donn√©es diff√©rentes pour former les items de notre ListView
 
        map = new HashMap<String, String>();
        map.put("titre", "Produit scalaire dans l’espace");
        map.put("description", "Carré scalaire, orthogonalité, vecteur normal à un plan, équation cartésienne.");
        map.put("img", String.valueOf(R.drawable.scalaire));
        listItem.add(map);
 
        map = new HashMap<String, String>();
        map.put("titre", "Suites");
        map.put("description", "Comportement globale d'une suite, opérations et comparaisons, suites géométriques, arithmétiques, principe de récurrence.");
        map.put("img", String.valueOf(R.drawable.suite));
        listItem.add(map);
       
        map= new HashMap<String,String>();
        map.put("titre", "Complexe");
        map.put("description", "Module , argument, différnetes écritures d'un nombre complexe, géométrie et transformations géométriques.");
        map.put("img", String.valueOf(R.drawable.complex));
        listItem.add(map);
        
        map= new HashMap<String,String>();
        map.put("titre", "Primitives et intégrales");
        map.put("description", "Primitives , intégrales, inégalités, intégration par partie.");
        map.put("img", String.valueOf(R.drawable.integral));
        listItem.add(map);
        
        map= new HashMap<String,String>();
        map.put("titre", "Dénombrement");
        map.put("description", "Listes, arrangement, permutation, combinaisons, binôme de Newton, principe fondamental du dénombrement.");
        map.put("img", String.valueOf(R.drawable.denombrement));
        listItem.add(map);
        
        map= new HashMap<String,String>();
        map.put("titre", "Droites et plans dans l'espace");
        map.put("description", "Barycentre, droites de l'espace, intersection et équations linéaires.");
       map.put("img", String.valueOf(R.drawable.droites));
        listItem.add(map);		
       
        map= new HashMap<String,String>();
        map.put("titre", "Exponentielles, logarithmes, puissances");
        map.put("description", "Fonctions exponentielle, logarythme, puissance, courbes représentatives, fonctions de bases associées.");
        map.put("img", String.valueOf(R.drawable.logarythme));
        listItem.add(map);		
       
        map= new HashMap<String,String>();
        map.put("titre", "Les fonctions");
        map.put("description", "Limites , comportement asymptotique, dérivation, continuité.");
        map.put("img", String.valueOf(R.drawable.fonction));
        listItem.add(map);
        
        
         //génération de la listview avec les différents items créer 
        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.affichageitem,
               new String[] {"img", "titre", "description"}, new int[] {R.id.img, R.id.titre, R.id.description});
 
        //On attribut @ notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);
 
        //Enfin on met un écouteur d'événement sur notre listView
        maListViewPerso.setOnItemClickListener(this);
			
       
         	
        		
    }
    
        public void onItemClick(AdapterView<?> a, View v, int position, long id)
    	{
			//on r√©cup√®re la HashMap contenant les infos de notre item (titre, description, img)
    		HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
    		
    		
    		//selon le titre contenu dans le HashMap on lance l'intent en rapport
    		//Intent intent=null;
    		if(map.get("titre").equals("Exponentielles, logarithmes, puissances"))
    			{
    			openUrl("https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B52lTSusrwypZTdiZDA5MTYtNThkMS00NThiLWI5M2YtNjBiYTIxMDYxNzhi&hl=en&authkey=CMH2x-4K");
    	        }
    		
    		if(map.get("titre").equals("Dénombrement"))
				{
    			openUrl("https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B52lTSusrwypZDVjZDJiYzAtNWU2ZS00YzcwLTkwZjgtNGVjOWE1YjVhZWFk&hl=en&authkey=CJfR0KcP");
    	        }
    		
    		if(map.get("titre").equals("Probabilité"))
    			{
    			openUrl("https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B52lTSusrwypNjZmYWYzNTctMzBmNC00ZjcwLTk0N2YtMGVhOTA1YTI1ODFj&hl=en&authkey=CKnj4ZIE");
    			}
    		
    		if(map.get("titre").equals("Complexe"))
    			{
    			openUrl("https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B52lTSusrwypYmNjOTZmNDItMmZmNS00MTA5LWJjMWQtZDY4ZDFmMmI4ZjQ3&hl=en&authkey=CKbz644G");
    			}
    		
    		if(map.get("titre").equals("Primitives et intégrales"))
				{
    			openUrl( "https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B52lTSusrwypZWY2MjZlZGMtYzEzOC00Zjk5LWFlYzAtMTI0NmIxNTMyMGFh&hl=en&authkey=CPP3lPIK");
    			}
    		
    		if(map.get("titre").equals("Suites"))
				{
    			openUrl("https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B52lTSusrwypMWNiNjkwMjctYTNhYi00ZDc4LWFlM2UtMGYyZjY5NTdmODlm&hl=en&authkey=CIKakssE");
				}
    		
    		if(map.get("titre").equals("Produit scalaire dans l’espace"))
				{
				openUrl("https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B52lTSusrwypMGIyNGEwZjctMDAyOS00ZmUyLTg2MTYtYTliODYyODAwZjMz&hl=en&authkey=CK6pnZQI");
				}
    		
    		if(map.get("titre").equals("Les fonctions"))
				{
    			openUrl("https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B52lTSusrwypYjMzMTIwODktZWVkYy00OTcyLWFmZTItMGRkNzFmZDg0M2Zj&hl=en&authkey=CLit-EE");
			    }
    		
    		if(map.get("titre").equals("Droites et plans dans l'espace"))
				{	
    			openUrl("https://docs.google.com/viewer?a=v&pid=explorer&chrome=true&srcid=0B52lTSusrwypZmU1ZjMzZjctNzhhNi00MmQ2LWI1NmItMWU3OWUwNzA0OTYw&hl=en&authkey=CIOgh4oN");
				}
    		
    		
    	}
 
        //fonction permettant de créer un bundle pour passer une url en extra à un intent vers l'activité navigator
public void openUrl(String url)
	{
	Bundle urlToOpen = new Bundle();
	urlToOpen.putString("url", url);
	Intent intent = new Intent (this, navigator.class);
	intent.putExtras(urlToOpen);
	startActivity(intent);
	}
}
