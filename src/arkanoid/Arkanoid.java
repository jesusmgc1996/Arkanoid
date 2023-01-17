package arkanoid;

import java.awt.BorderLayout;
import java.awt.Rectangle;
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

	private static Arkanoid instance = null;
	private static int FPS = 60;
	
	private List<Actor> actors = new ArrayList<Actor>();
	private List<Actor> deletedActors = new ArrayList<Actor>();
	private JFrame window = null;
	private MiCanvas canvas = null;
	private Nave n = null;
	private Pelota p = null;
	
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
			canvas.paintScene();
			
			// Se recorren todos los actores para que actúen
			for (Actor a: actors)
				a.act();
			
			// Se detectan colisiones
			detectCollisions();
						
			// Se actualizan los actores
			updateActors();
			
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
		int y = 75;
		
		// Creación de la nave
		n = new Nave(224, 600);
		actors.add(n);
		
		// Creación de la pelota
		p = new Pelota(259, 585);
		actors.add(p);
		
		// Creación de los ladrillos
		for (int i = 0; i < 6; i++) {
			int x = 10;
			for (int j = 0; j < 12; j++) {
				Ladrillo l = new Ladrillo(x, y, i);
				actors.add(l);
				x += Ladrillo.WIDTH + Ladrillo.SPACE_BETWEEN;
			}
			y += Ladrillo.HEIGHT + Ladrillo.SPACE_BETWEEN;
		}
		return actors;
	}
	
	public void deleteActor(Actor a) {
		deletedActors.add(a);
	}
	
	private void updateActors() {
		actors.removeAll(deletedActors); // Se eliminan los actores que se deben eliminar¡
		deletedActors.clear(); // Se limpia la lista de actores a eliminar
	}
	

	/**
	 * Se detectan las colisiones entre los actores
	 */
	private void detectCollisions() {
		// La detección de colisiones se va a basar en formar un rectángulo con las medidas que ocupa cada actor en pantalla,
		// de esa manera, las colisiones se traducirán en intersecciones entre rectángulos
		Actor a1 = p;
		// Se forma el rectángulo para la pelota
		Rectangle p = new Rectangle(a1.getX(), a1.getY(), Pelota.RADIUS, Pelota.RADIUS);
		// Se comprueba un actor con cualquier otro actor
		for (Actor a2: actors)
			// Se evita comparar un actor con él mismo
			if (!a1.equals(a2)) {
				// Se forman los rectángulos para los ladrillos y la nave
				Rectangle l = new Rectangle(a2.getX(), a2.getY(), Ladrillo.WIDTH, Ladrillo.HEIGHT);
				Rectangle n = new Rectangle(a2.getX(), a2.getY(), Nave.WIDTH, Nave.HEIGHT);
				// Si los dos rectángulos tienen alguna intersección, se notifica una colisión
				if (p.intersects(l) || p.intersects(n)) {
					a1.collision(a2); // El actor 1 colisiona con el actor 2
					a2.collision(a1); // El actor 2 colisiona con el actor 1
					break; // Se evita que haya más de una colisión a la vez
				}
			}
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
