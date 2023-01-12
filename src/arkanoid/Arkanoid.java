package arkanoid;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Arkanoid {
	
	private static int FPS = 60;
	private JFrame window = null;
	private List<Actor> actors = new ArrayList<Actor>();
	private MiCanvas canvas = null;
	Nave n = null;
	
	// Propiedad estática para usar un patrón singleton
	private static Arkanoid instance = null;
	
	/**
	 *  Método para usar el patrón singleton
	 * @return
	 */
	public static Arkanoid getInstance() {
		if (instance == null) instance = new Arkanoid();
		return instance;
	}
	
	/**
	 * Método constructor
	 */
	public Arkanoid() {
		// Creación del objeto ventana
		window = new JFrame("Arkanoid");
		window.setBounds(0, 0, 548, 700);
		
		// Asignación de layout a la ventana
		window.getContentPane().setLayout(new BorderLayout());
		
		//Creación de la lista de actores
		actors = createActors();
		
		// Creación del objeto Canvas para pintar los actores
		canvas = new MiCanvas(actors);
		
		// Se envían los eventos del ratón a la nave
		canvas.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				n.move(e.getX());
			}			
		});
		
		// Se envían los eventos de teclado a la nave
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				n.keyPressed(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				n.keyReleased(e);
			}
		});
		
		window.getContentPane().add(canvas, BorderLayout.CENTER);
		
		// Se ignora el repintado de la ventana por parte de Windows
		window.setIgnoreRepaint(true);
		
		// Visibilidad de la ventana
		window.setVisible(true);
		
		// Control del evento de cierre de ventana
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeAplication();
			}
		});
	}

	public static void main(String[] args) {
		// Ejecución del juego
		Arkanoid.getInstance().getCanvas().requestFocus();
		Arkanoid.getInstance().game();
	}
	
	/**
	 * Método con el bucle del juego principal
	 */
	public void game() {
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
	
	/**
	 * Método para crear los actores
	 * @return
	 */
	private List<Actor> createActors() {
		List<Actor> actors = new ArrayList<Actor>();
		Ladrillo l = null;
		int y = 75;
		
		// Creación de la nave
		n = new Nave(224, 600);
		actors.add(n);
		
		// Creación de la pelota
		Pelota p = new Pelota(259, 585);
		actors.add(p);
		
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
		
		return actors;
	}
	
	/**
	 * Método para confirmar al cerrar la aplicación
	 */
	private void closeAplication() {
		String[] options = {"Aceptar","Cancelar"};
		int opt = JOptionPane.showOptionDialog(window, "¿Desea cerrar la aplicación?", "Salir de la aplicación",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Aceptar");
		if (opt == JOptionPane.YES_OPTION) System.exit(0);
	}
	
	/**
	 * @return the canvas
	 */
	public MiCanvas getCanvas() {
		return canvas;
	}

}
