package arkanoid;

import java.awt.Graphics;

public abstract class Actor {
	
	protected int x;
	protected int y;

	/**
	 * 
	 */
	public Actor() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 */
	public Actor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * Método para pintar cada actor. Obliga a las subclases a implementarlo.
	 * @param g
	 */
	public abstract void paint(Graphics g);
	
	/**
	 * Método para que los actores actúen.
	 */
	public void act() {
	}
	
	/**
	 * Método para realizar acciones al colisionar
	 * @param a
	 */
	public void collision(Actor a) {
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Actor [x=" + x + ", y=" + y + "]";
	}
	
}
