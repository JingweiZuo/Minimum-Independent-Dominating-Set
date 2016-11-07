public class execution {
	static final int N = 4;
	public static void main(String args[]){
		
		graph gra = new graph();
		for(int i = 0 ; i < N;i ++){
		sommet s0 = new sommet(i);
		gra.add_sommet(s0);
		}
		
		gra.connexion();
		gra.schma();
		gra.fonction1();
		
		
		
		
	/*	
		int min = 10 ;
		for(int i = 0; i < gra.get_s_e_d_taille(); i ++){
			graph g = gra.get_graph(i);
			if(g.get_taille() < min){
				min = g.get_taille();
			}	
		}
		
	*/	
		
		System.out.println("min ="+ gra.getmin());
		
		for(int i = 0; i < gra.get_s_e_d_taille(); i ++){
			graph g = gra.get_graph(i);
			//if(g.get_taille() == min){
				g.affiche();
			//}	
		}
		
		
	}
}
