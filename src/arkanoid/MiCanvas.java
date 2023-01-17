package arkanoid;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.List;

public class MiCanvas extends Canvas {
	
	List<Actor> actors = null;
	
	private BufferStrategy strategy = null;
	
	/**
	 * @param actores
	 */
	public MiCanvas (List<Actor> actors) {
		this.actors = actors;
	}
	
	public void paintScene() {
		// Se inicializa el objeto "strategy" una única vez
		if (strategy == null) {
			// El Canvas se dibujará en pantalla con una estrategia de doble búffer
			createBufferStrategy(2);
			
			// Se obtiene una referencia a la estrategia de doble búffer
			strategy = getBufferStrategy();
			
			// Se resuelve un problema de sincronización de memoria de vídeo en Linux
			Toolkit.getDefaultToolkit().sync();			
		}
		
		// Se obtiene el objeto gráfico que permite pintar en el doble búffer
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		
		// Se pinta un rectángulo negro que ocupa toda la escena
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (Actor a: actors)
			a.paint(g);
		
		// Se muestra en pantalla el buffer con el nuevo frame creado para el juego
		strategy.show();
	}

}
