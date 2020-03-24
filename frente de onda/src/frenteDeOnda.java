import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class frenteDeOnda {

	private int[][] mat;
	int n;
	int m;
	int xi;
	int yi;
	int xf;
	int yf;


	/*
	 * Metodo constructor de la clase encargado de la busqeuda del camino
	 */

	public frenteDeOnda(double pn,double pm)
	{
		m=(int) Math.round(pn/100);
		n=(int) Math.round(pm/100);
		tamanoMat();
		colocar1enTodo();
	}

	/*
	 * Metodo que establece los puntos inicilaes del robot 
	 */
	public void pInn(double x,double y)
	{
		xi=(int) Math.round(y/100);
		yi=(int) Math.round(x/100);
		mat[xi][yi]=9999;
	}

	/*
	 *Metodo que establece los puntos finales 
	 */
	public void pfIn(double x,double y) throws IOException
	{
		xf=(int) Math.round(y/100);
		yf=(int) Math.round(x/100);
	}
	public String darPuntos() 
	{
		asignarValores(xf, yf, 2);
		run();
		return puntos(camino());
		
	}

	/*
	 *Metodo que inicializa la matriz representacion del espacio
	 */
	private void tamanoMat()
	{
		mat= new int[n+1][m+1];
	}

	/*
	 * Metodo que permite saber el espacio por el cual el robot uede moverse
	 */
	public void colocar1enTodo()
	{
		for(int i =0; i<=n;i++)
		{
			for(int j =0; j<=n; j++)
			{
				mat[i][j]=1;
			}
		}
	}

	/*
	 * Metodo que permite tener una representacion del obstaculo
	 */

	public void obstaculo(int px1,int py1 , int px2, int py2)
	{
		int x1=px1/100;
		int x2=px2/100;
		int y1=py1/100;
		int y2=py2/100;
		int menX=0;
		int mayx=0;
		int meny=0;
		int mayy=0;
		if(x1-x2<0){
			menX=x1;
			mayx=x2;					
		}
		else if(x1-x2>0){

			menX=x2;
			mayx=x1;
		}
		else{
			menX=x2;
			mayx=menX;
		}

		if(y1-y2<0){
			meny=y1;
			mayy=y2;					
		}
		else if(y1-y2>0){

			meny=y2;
			mayy=y1;
		}
		else{
			meny=y2;
			mayy=meny;
		}		

		for(int i= menX;i<=mayx;i++)
		{
			for(int j= meny;j<=mayy;j++)
			{
				mat[j][i]=0;
			}
		}


	}

	/*
	 * Metodo que permite imprimir la representacion de la matriz 
	 * 
	 */
	public void print() throws IOException
	{

		File a = new File("./print.txt");
		FileWriter fw = new FileWriter(a);
		PrintWriter pw = new PrintWriter(a);
		for(int i =n-1; i>=0;i--)
		{
			String men ="";
			for(int j = 0; j<m; j++)
			{
				men=men+mat[i][j]+"      ";
			}
			pw.println(men);

		}
		pw.close();
		fw.close();
		System.out.println("ya");
	}

	/*
	 * metodo que poermite caluclar los puntos sobre los cuale tendria que pasar el robot evitando los obstaculos
	 */
	public String camino()
	{
		int men=9999;
		int xmen=xi;
		int ymen=yi;

		String cam = ymen + "  "+ xmen  + "-"  ; 

		while(mat[xmen][ymen]!=2)
		{
			int xmomen=0;
			int ymomen=0;

			if(mat[xmen+1][ymen]<men && mat[xmen+1][ymen]!=0)
			{
				men=mat[xmen+1][ymen];
				xmomen=xmen+1;
				ymomen=ymen;


			}
			if(mat[xmen+1][ymen+1]<men && mat[xmen+1][ymen+1]!=0)
			{
				men=mat[xmen+1][ymen+1];
				xmomen=xmen+1;
				ymomen=ymen+1;
			}
			if(mat[xmen-1][ymen+1]<men&& mat[xmen-1][ymen+1]!=0)
			{
				men=mat[xmen-1][ymen+1];
				xmomen=xmen-1;
				ymomen=ymen+1;
			}
			if(mat[xmen+1][ymen-1]<men && mat[xmen+1][ymen-1]!=0)
			{
				men=mat[xmen+1][ymen+1];
				xmomen=xmen+1;
				ymomen=ymen-1;
			}
			if(mat[xmen-1][ymen-1]<men && mat[xmen-1][ymen-1]!=0)
			{
				men=mat[xmen-1][ymen-1];
				xmomen=xmen-1;
				ymomen=ymen-1;
			}

			if(mat[xmen-1][ymen]<men&& mat[xmen-1][ymen]!=0)
			{
				men=mat[xmen-1][ymen];
				xmomen=xmen-1;
				ymomen=ymen;

			}
			if(mat[xmen][ymen+1]<men && mat[xmen][ymen+1]!=0)
			{
				men=mat[xmen][ymen+1];
				xmomen=xmen;
				ymomen=ymen+1;
			}
			if(mat[xmen][ymen-1]<men && mat[xmen][ymen-1]!=0)
			{
				men=mat[xmen][ymen-1];
				xmomen=xmen;
				ymomen=ymen-1;	
			}

			xmen=xmomen;
			ymen=ymomen;
			cam =cam + ymen + "  "+ xmen + "-"  ; 
		}
		
		return cam;
	}
	public void run()
	{
		boolean todo=true;
		int i=3;
		while(todo)
		{

			buscar(i);
			i++;
			todo=todos();

		}

	}
	public String puntos(String p)
	{

		String[] pun = p.split("-");
		int xant= Integer.parseInt((pun[0].split("  "))[0]);
		int yant= Integer.parseInt((pun[0].split("  "))[1]);
		String men=xant+"00 "+ yant+ "00 -";
		for(int i=1;i<pun.length-2;i++)
		{
			int x= Integer.parseInt((pun[i+1].split("  "))[0]);
			int y= Integer.parseInt((pun[i+1].split("  "))[1]);

			if((x-y)==(xant-yant))
			{
				xant= Integer.parseInt((pun[i].split("  "))[0]);
				yant= Integer.parseInt((pun[i].split("  "))[1]);

			}
			else if(x==xant || y == yant)
			{
				xant= Integer.parseInt((pun[i].split("  "))[0]);
				yant= Integer.parseInt((pun[i].split("  "))[1]);
			}
			else
			{
				xant= Integer.parseInt((pun[i].split("  "))[0]);
				yant= Integer.parseInt((pun[i].split("  "))[1]);
				men= men+ (pun[i].split("  "))[0]+"00 "+ (pun[i].split("  "))[1] + "00 -";
			}

		}
		men= men+ yf+"00 "+ xf + "00";



		System.out.println(men);
		return men;
	}

	private void buscar(int valor)
	{
		for(int i =n-1; i>=0;i--)
		{

			for(int j = 0; j<m; j++)
			{
				if(mat[i][j]==valor)
				{
					asignarValores(i, j, valor);
				}
			}

		}
	}

	private boolean todos()
	{
		for(int i =n-1; i>=0;i--)
		{

			for(int j = 0; j<m; j++)
			{
				if(mat[i][j]==1)
				{
					return true;
				}
			}

		}

		return false;
	}

	private boolean asignarValores(int x, int y, int val)
	{

		mat[x][y]=val;
		if(0<=x-1 || x+1<m || y+1<n || 0<=y-1)
		{
			if(mat[x][y]==9999){
				return true;}
			if(x+1<m && mat[x+1][y]==1  && mat[x+1][y]!=0)
			{
				mat[x+1][y]=val+1;



			}
			if(0<=x-1 && mat[x-1][y]==1  && mat[x-1][y]!=0)
			{

				mat[x-1][y]=val+1;


			}
			if(y+1<n && mat[x][y+1]==1 && mat[x][y+1]!=0)
			{
				mat[x][y+1]=val+1;


			}
			if(0<=y-1 && mat[x][y-1]==1 && mat[x][y-1]!=0)
			{
				mat[x][y-1]=val+1;


			}	
		}


		return true;
	}

}
