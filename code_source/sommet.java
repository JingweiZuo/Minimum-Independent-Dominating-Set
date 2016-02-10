package rattrabage;

import java.util.ArrayList;
public class sommet {
	private int num;
	private ArrayList<sommet> liaison = new ArrayList<sommet>();
	sommet(){
	}
	
	sommet(int num){
		this.num = num;
	}

	public int get_num(){
		return this.num;
	}
	
	public void add_liaison(sommet s){
		this.liaison.add(s);
	}
	
	public int get_size(){
		return this.liaison.size();
	}
	
	public sommet get_ele(int i){
		return this.liaison.get(i);
	}
	
	public void affiche(){
		System.out.println("num = " + this.num );
		System.out.println("les sommets liaison sont ");
		for(int i = 0; i < this.liaison.size();i++){
			sommet s = this.liaison.get(i);
			System.out.println(s.num);
		}
	}
	
}
