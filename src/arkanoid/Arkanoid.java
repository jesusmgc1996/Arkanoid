package arkanoid;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Arkanoid {

	public static void main(String[] args) {
		
		// Creación del objeto ventana
		JFrame window = new JFrame("Arkanoid");
		window.setBounds(0, 0, 548, 700);
		
		// Asignación de layout a la ventana
		window.getContentPane().setLayout(new BorderLayout());
		
		// Creación del objeto Canvas para pintar los actores
		MiCanvas canvas = new MiCanvas(createActors());
		window.getContentPane().add(canvas, BorderLayout.CENTER);
		
		// Visibilidad de la ventana
		window.setVisible(true);
	}
	
	private static List<Actor> createActors () {
		
		// Creación de la lista de actores
		List<Actor> actors = new ArrayList<Actor>();
		
		int y = 75;
		
		// Creación de los ladrillos
		for (int i = 0; i < 6; i++) {
			int x = 10;
			for (int j = 0; j < 12; j++) {
				Ladrillo l = new Ladrillo(x, y, i);
				actors.add(l);
				x += l.WIDTH + l.SPACE_BETWEEN;
				if (j == 11) y+= l.HEIGHT + l.SPACE_BETWEEN;
			}
		}
		
		// Creación de la nave
		Nave n = new Nave(234, 600);
		actors.add(n);
		
		// Creación de la pelota
		Pelota p = new Pelota(259, 350);
		actors.add(p);
		
		return actors;
	}

}
