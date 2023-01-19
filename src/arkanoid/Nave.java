package arkanoid;

import java.awt.event.KeyEvent;

public class Nave extends Actor {
	
	private static int SPEED = 5;
	
	private boolean left = false, right = false;
	
	/**
	 * 
	 */
	public Nave() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 */
	public Nave(int x, int y) {
		super(x, y, ImagesCache.getInstance().getImage(ImagesCache.SHIP_IMAGE));
	}

	/**
	 * Método para mover la nave
	 */
	@Override
	public void act() {
		// Se comprueba el movimiento
		if (left) x -= SPEED;
		if (right) x += SPEED;
		
		// Se comprueba si la nave sale por los laterales
		move(x);
	}
	
	/**
	 * Método para comprobar la posición de la nave
	 * @param x
	 * @param y
	 */
	public void move(int x) {
		MiCanvas canvas = Arkanoid.getInstance().getCanvas();
		this.x = x;

		// Se obtienen los casos en los que la nave sale del Canvas
		if (this.x > canvas.getWidth() - width) this.x = canvas.getWidth() - width; // Derecha
		if (this.x < 0) this.x = 0; // Izquierda
	}
	
	/**
	 * Método que se ejecuta al presionar una tecla
	 * @param e
	 */
	public void keyPressed (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true; break;
		case KeyEvent.VK_RIGHT:
			right = true; break;
		}
	}
	
	/**
	 * Método que se ejecuta al liberar una tecla
	 * @param e
	 */
	public void keyReleased (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false; break;
		case KeyEvent.VK_RIGHT:
			right = false; break;
		}
	}

	@Override
	public String toString() {
		return "Nave [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", img=" + img + "]";
	}

}
