package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Nave extends Actor {
	
	private static int WIDTH = 80;
	private static int HEIGHT = 10;
	
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
		super(x, y);
	}

	/**
	 * Método para pintar la nave
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	public void act() {
		
	}

	@Override
	public String toString() {
		return "Nave [x=" + x + ", y=" + y + "]";
	}

}
