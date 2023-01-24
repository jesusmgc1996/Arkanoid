package arkanoid;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Explosion extends Actor {
	
	/**
	 * @param x
	 * @param y
	 */
	public Explosion(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		// Sprites para la explosión
		List<BufferedImage> newSprites = new ArrayList<BufferedImage>();
		newSprites.add(ImagesCache.getInstance().getImage("sprite-explosion1.png"));
		newSprites.add(ImagesCache.getInstance().getImage("sprite-explosion2.png"));
		newSprites.add(ImagesCache.getInstance().getImage("sprite-explosion3.png"));
		newSprites.add(ImagesCache.getInstance().getImage("sprite-explosion4.png"));
		newSprites.add(ImagesCache.getInstance().getImage("sprite-explosion5.png"));
		newSprites.add(ImagesCache.getInstance().getImage("sprite-explosion6.png"));
		newSprites.add(ImagesCache.getInstance().getImage("sprite-explosion7.png"));
		newSprites.add(ImagesCache.getInstance().getImage("sprite-explosion8.png"));
		newSprites.add(ImagesCache.getInstance().getImage("sprite-explosion9.png"));
		setAnimationSprites(newSprites);
		// Sprite actual
		actualSprite = this.getAnimationSprites().get(0);
		// Velocidad de cambio de sprite
		spriteSpeed = 5;
	}
	

	/**
	 * Método para que la explosión actúe
	 */
	@Override
	public void act() {
		super.act();
		if (actualSprite.equals(animationSprites.get(animationSprites.size()-1))) Arkanoid.getInstance().deleteActor(this);
	}

	@Override
	public String toString() {
		return "Explosion [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + 
				", animationSprites=" + animationSprites + "]";
	}

}
