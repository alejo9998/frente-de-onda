import java.io.IOException;

public class interfaz {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		frenteDeOnda a = new frenteDeOnda(3300,3300);
		a.obstaculo(600,0 , 600, 2700);
		a.obstaculo(600,2700 , 2100, 2700);
		a.obstaculo(900, 1500, 1200, 1800);
		a.obstaculo(1800, 1500, 2100, 1800);
		a.obstaculo(2700, 1500, 3000, 1800);
		a.obstaculo(2400, 300, 2700, 600);
		a.pInn(900, 300);
		a.pfIn(2850, 3000);
		System.out.println(a.darPuntos());
		
		
	}

}
