package rattrabage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class graph {
	private ArrayList<sommet> l = new ArrayList<sommet>();// les elements
	private static ArrayList<sommet> ol = new ArrayList<sommet>();//tous les elements
	private ArrayList<sommet> e_d = new ArrayList<sommet>();
	//private ArrayList<sommet> juger = new ArrayList<sommet>();// les sommets lie avec e_d
	public static ArrayList<graph> s_e_d = new ArrayList<graph>();
	private int taille;
	private static int min;//
	private static boolean[][] juger ;//
	private static boolean[] som;//
	graph(){
	}
	
	public int getmin(){
		return min;
	}
	
	graph(graph g){
		this.l = new ArrayList<sommet>(g.l);
		this.e_d = new ArrayList<sommet>(g.e_d);
		this.taille = g.taille;
	}
	public void add_sommet(sommet s){
		this.l.add(s);
		this.ol.add(s);
	}
	
	public void remove_sommet(sommet s){
		this.l.remove(s);
		
	}
	
	
	public int get_taille(){
		return this.e_d.size();
	}
	
	public int get_s_e_d_taille(){
		return this.s_e_d.size();
	}
	
	public graph get_graph(int i){
		return this.s_e_d.get(i);
	}
	
	public void connexion(){
		boolean[][] tab = new boolean[this.l.size()][this.l.size()];		
		for(int i = 0; i < this.l.size(); i ++){
			for(int j = i + 1; j < this.l.size();j++){
				Random r = new Random();
				int k = r.nextInt(2);
				if(k == 0){
					tab[i][j] = true;
				}
				else{
					tab[i][j] = false;
				}
			}			
		}
		
		for(int i = 0;i < this.l.size(); i++){
			int flag = 0;
			for(int j = i +1; j < this.l.size(); j++){
				if(tab[i][j] == true){
					flag = 1;
				}
			}
			if(flag == 0){
				int s = this.l.size();
				tab[i][s - 1] = true;
			}
//modifie***************************************************
			juger = tab;
			som = new boolean [this.l.size()];
			min = this.l.size();
			for(int a = 0; a < this.l.size(); a++){
				    juger[a][a] = true;
				for(int b = a+1; b < this.l.size(); b++){
					juger[b][a] = juger[a][b]; 
				}
			}
		}
//************************************************************		
		for(int i = 0; i < this.l.size(); i ++){
			for( int j = i + 1; j < this.l.size();j++){
				if(tab[i][j] == true){
					sommet a = this.l.get(i);//recuperer l'index du somment
					sommet b = this.l.get(j);
					a.add_liaison(b);
					b.add_liaison(a);
				}			
			}
		}
	}
	
/*	
	Comparator<sommet> comparator = new Comparator<sommet>(){
		public int compare(sommet s1, sommet s2){
			return s1.get_num() - s2.get_num() ;
		}
	};
	
	public int comp(){
		int flag = 0;
		for(int i = 0; i < this.get_s_e_d_taille(); i++){
			graph gc = this.get_graph(i);
			int f = 0;
			if(gc.get_taille() == this.get_taille()){
				for(int j = 0; j < this.get_taille();j++){
					sommet a = gc.e_d.get(j);
					sommet b = this.e_d.get(j);
					if(a.get_num() != b.get_num()){
						f = 1;
					}					
				}
				if (f == 0){
					flag = 1;
				}
			}
		}
		return flag;
	}
*/	
	
	
	public graph modifier (sommet s){
		this.l.remove(s);
		graph g = new graph(this);		   
		return g;
	}
/*	
	public void creer_juger(){
		
		for(int i = 0; i < this.e_d.size(); i++){
			sommet a = this.e_d.get(i);
			for(int j = 0; j < a.get_size(); j++){
				sommet b = a.get_ele(j);
				this.juger.add(b);
			}
			
		}
	}
*/	
	
/*	
	public void jugement(){
		this.creer_juger();
		int flag = 0 ;
		for(int i = 0; i < this.ol.size(); i ++){
			sommet a = this.ol.get(i);
			if((!this.juger.contains(a)) && (!this.e_d.contains(a))){
				flag = 1;
				break;
			}
		}
		if(flag == 0){
			//Collections.sort(this.e_d,comparator);
			//int f = this.comp();
			//if(f == 0 ){
			this.s_e_d.add(this);
			//}
		}
	}
*/
	
	public void creer_juger(){
		
	    for(int i = 0; i < this.ol.size(); i++){
	    	som[i] = false;
	    }
		for(int j = 0; j < this.e_d.size(); j++){
			sommet a = this.e_d.get(j);
			int k = a.get_num();
			som[k] = true;
		}
	}
	
	public void jugement(){
		 this.creer_juger();
		int flag = 0;
		for(int i = 0; i < this.ol.size(); i++){
			if(som[i] == false){
				int lia = 0;
				for(int j = 0; j < this.ol.size(); j++){
					if(som[j] == true){
						if(juger[i][j] == true){
							lia =1;break;
						}
					}
				}
				if(lia == 0){
					flag = 1;break;					
				}
			}		
		}
		if(flag == 0){
			if(this.e_d.size() < min){
				min = this.e_d.size();
				this.s_e_d.clear();
				this.s_e_d.add(this);
			}
			else if(this.e_d.size() == min){
			this.s_e_d.add(this);
			}
			else{
			}
		}	
		
	}
	
	
	
	
	public void fonction1(){
		//if(this.l.size() != 0){	
			while(this.l.size()!=0){
				sommet a = this.l.get(0);				
				graph g = this.modifier(a);
				g.e_d.add(a);				
				g.jugement();
				g.fonction1();
			}
		//}
		//else{
			//Collections.sort(this.e_d,comparator);
			//int f = this.comp();
			//if(f == 0 ){
			//this.s_e_d.add(this);
			//}
		//}
	}
	
	public void affiche (){
		System.out.print("(");
		for(int i = 0; i < this.e_d.size();i++){
			sommet a = this.e_d.get(i);
			System.out.print(a.get_num()+ " ");
		}
		System.out.println(")");
	}
	
	public void schma(){
		System.out.println("c'est le connxion de graph");
		for(int i = 0; i < this.l.size();i++){
			sommet a = this.l.get(i);
			a.affiche();
		}
	}
			
		

}
