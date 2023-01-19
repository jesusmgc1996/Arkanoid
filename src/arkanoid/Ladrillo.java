package arkanoid;

public class Ladrillo extends Actor {
	
	public static int SPACE_BETWEEN = 3;
	private static String IMAGES[] = new String[] {ImagesCache.RED_BRICK, ImagesCache.YELLOW_BRICK, ImagesCache.PURPLE_BRICK,
			ImagesCache.CYAN_BRICK, ImagesCache.GREEN_BRICK, ImagesCache.ORANGE_BRICK};
	
	/**
	 * 
	 */
	public Ladrillo() {
		super();
	}
	
	/**
	 * @param x
	 * @param y
	 * @param image
	 */
	public Ladrillo(int x, int y, int image) {
		super(x, y, ImagesCache.getInstance().getImage(IMAGES[image]));
	}

	/**
	 * Método para eliminar ladrillos al colisionar
	 */
	@Override
	public void collision(Actor a) {
		super.collision(a);
		// Si se colisiona con la pelota, se elimina
		if (a instanceof Pelota) Arkanoid.getInstance().deleteActor(this);
	}

	@Override
	public String toString() {
		return "Ladrillo [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", img=" + img + "]";
	}

}
