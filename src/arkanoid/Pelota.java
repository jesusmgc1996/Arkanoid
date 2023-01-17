package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Actor {
	
	public static int RADIUS = 15;
	
	private int xSpeed = -5, ySpeed = -5;

	/**
	 * 
	 */
	public Pelota() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 */
	public Pelota(int x, int y) {
		super(x, y);
	}

	/**
	 * Método para pintar la pelota
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, RADIUS, RADIUS);
	}
	
	/**
	 * Método para mover la pelota
	 */
	@Override
	public void act() {
		// Movimiento horizontal en cada FPS
		x += xSpeed;
		// Si la pelota sale por la izquierda o la derecha, rebota
		if (x <= 0 || x + RADIUS >= Arkanoid.getInstance().getCanvas().getWidth()) xSpeed = -xSpeed;
		
		// Movimiento vertical en cada FPS
		y += ySpeed;
		// Si la pelota sale por arriba o abajo, rebota
		if (y <= 0 || y + RADIUS >= Arkanoid.getInstance().getCanvas().getHeight()) ySpeed = -ySpeed;
	}
	
	/**
	 * Método para cambiar la dirección de la pelota al colisionar
	 */
	@Override
	public void collision(Actor a) {
		super.collision(a);
		// Si se colisiona con el ladrillo o la nave, cambia la dirección
		if (a instanceof Ladrillo || a instanceof Nave) {
			ySpeed = -ySpeed;
		}
	}

}
