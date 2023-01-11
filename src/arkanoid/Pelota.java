package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Actor {
	
	private static int RADIUS = 15;
	
	private int xSpeed = -5;
	private int ySpeed = -5;

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
	
	public void act() {
		// Movimiento horizontal en cada FPS
		x += xSpeed;
		// Si la pelota abandona la escena por la izquierda o la derecha, rebota
		if (x < 0 || x + RADIUS * 2 > 548) xSpeed = -xSpeed;
		
		// Movimiento vertical en cada FPS
		y += ySpeed;
		// Si la pelota abandona la escena por arriba o abajo, rebota
		if (y < 0 || y + RADIUS * 3.5 > 700) ySpeed = -ySpeed;
	}

	@Override
	public String toString() {
		return "Pelota [x=" + x + ", y=" + y + "]";
	}

}
