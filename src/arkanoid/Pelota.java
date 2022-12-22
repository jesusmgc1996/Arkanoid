package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Actor {
	
	public static int RADIUS = 15;

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

	@Override
	public String toString() {
		return "Pelota [x=" + x + ", y=" + y + "]";
	}

}
