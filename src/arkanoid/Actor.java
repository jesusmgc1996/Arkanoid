package arkanoid;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Actor {
	
	protected int x, y;
	protected int width, height;
	protected BufferedImage img;

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
	public Actor(int x, int y, BufferedImage img) {
		super();
		this.x = x;
		this.y = y;
		this.setImg(img);
	}

	/**
	 * Método para pintar cada actor
	 * @param g
	 */
	public void paint(Graphics g) {
		g.drawImage(img, x, y, null);
	};
	
	/**
	 * Método para que los actores actúen
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
		this.width = this.img.getWidth();
		this.height = this.img.getHeight();
	}

	@Override
	public String toString() {
		return "Actor [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", img=" + img + "]";
	}
	
}
