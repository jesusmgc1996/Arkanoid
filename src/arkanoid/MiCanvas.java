package arkanoid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class MiCanvas extends Canvas {
	
	List<Actor> actors;
	
	/**
	 * @param actores
	 */
	public MiCanvas (List<Actor> actors) {
		this.actors = actors;
	}
	
	@Override
	public void paint(Graphics g) {
		this.setBackground(Color.BLACK);
		for (Actor a: actors)
			a.paint(g);
	}

}
