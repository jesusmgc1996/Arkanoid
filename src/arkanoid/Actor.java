package arkanoid;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Actor {
	
	protected int x, y;
	protected int width, height;
	protected BufferedImage actualSprite;
	protected List<BufferedImage> animationSprites = new ArrayList<BufferedImage>();
	protected int spriteSpeed = 0;
	private int time = 0;

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
	 * Método para pintar cada actor
	 * @param g
	 */
	public void paint(Graphics g) {
		g.drawImage(actualSprite, x, y, null);
	};
	
	/**
	 * Método para que los actores actúen
	 */
	public void act() {
		if (animationSprites != null && animationSprites.size() > 0) {
			time++;
			if (time % spriteSpeed == 0) {
				time = 0;
				int actualSpriteIndex = animationSprites.indexOf(actualSprite);
				int nextSpriteIndex = (actualSpriteIndex + 1) % animationSprites.size();
				actualSprite = animationSprites.get(nextSpriteIndex);
			}
		}
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

	public BufferedImage getActualSprite() {
		return actualSprite;
	}

	public void setActualSprite(BufferedImage actualSprite) {
		this.actualSprite = actualSprite;
		width = actualSprite.getWidth();
		height = actualSprite.getHeight();
	}

	public List<BufferedImage> getAnimationSprites() {
		return animationSprites;
	}

	public void setAnimationSprites(List<BufferedImage> animationSprites) {
		this.animationSprites = animationSprites;
	}

	@Override
	public String toString() {
		return "Actor [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", actualSprite="
				+ actualSprite + ", animationSprites=" + animationSprites + "]";
	}
	
}
