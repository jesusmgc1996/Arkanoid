package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actor {
	
	public static int WIDTH = 40;
	public static int HEIGHT = 20;
	public static int SPACE_BETWEEN = 3;
	private static Color COLORS[] = new Color[] {Color.RED, Color.YELLOW, Color.PINK, Color.CYAN, Color.GREEN, Color.ORANGE};
	
	private int color;
	
	/**
	 * 
	 */
	public Ladrillo() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 */
	public Ladrillo(int x, int y, int color) {
		super(x, y);
		this.color = color;
	}

	/**
	 * Método para pintar los ladrillos
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(COLORS[color]);
		g.fillRect(x, y, WIDTH, HEIGHT);
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

}
