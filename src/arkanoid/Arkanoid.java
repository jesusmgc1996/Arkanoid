package arkanoid;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

public class Arkanoid {
	
	private static int FPS = 60;

	public static void main(String[] args) {
		
		// Creación del objeto ventana
		JFrame window = new JFrame("Arkanoid");
		window.setBounds(0, 0, 548, 700);
		
		// Asignación de layout a la ventana
		window.getContentPane().setLayout(new BorderLayout());
		
		//Creación de la lista de actores
		List<Actor> actors = createActors();
		
		// Creación del objeto Canvas para pintar los actores
		MiCanvas canvas = new MiCanvas(actors);
		window.getContentPane().add(canvas, BorderLayout.CENTER);
		
		// Se ignora el repintado de la ventana por parte de Windows
		window.setIgnoreRepaint(true);
		
		// Visibilidad de la ventana
		window.setVisible(true);
		
		// Bucle para redibujar la escena tantas veces por segundo como indique la variable FPS
		int msForFrame = 1000 / FPS;
		do {
			// Se toman los ms actuales
			long msBeforeScene = new Date().getTime();
			
			// Se redibuja la escena
			canvas.repaint();
			
			// Recorro todos los actores, consiguiendo que cada uno de ellos actúe
			for (Actor a: actors)
				a.act();
			
			// Se calculan los ms que debemos parar el proceso, generando 60 FPS
			long msAfterScene = new Date().getTime();
			int msProcessingScene = (int) (msAfterScene - msBeforeScene);
			int msPause = msForFrame - msProcessingScene;
			msPause = (msPause < 0)? 0 : msPause;
			// Se duerme el proceso principal durante los ms calculados
			try {
				Thread.sleep(msPause);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}
	
	private static List<Actor> createActors () {
		List<Actor> actors = new ArrayList<Actor>();
		Ladrillo l = null;
		int y = 75;
		
		// Creación de los ladrillos
		for (int i = 0; i < 6; i++) {
			int x = 10;
			for (int j = 0; j < 12; j++) {
				l = new Ladrillo(x, y, i);
				actors.add(l);
				x += l.WIDTH + l.SPACE_BETWEEN;
			}
			y+= l.HEIGHT + l.SPACE_BETWEEN;
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
