package arkanoid;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * Clase para almacenar las imágenes
 */
public class ImagesCache {
	
	public static String RED_BRICK = "rojo.png";
	public static String YELLOW_BRICK = "amarillo.png";
	public static String PURPLE_BRICK = "purple.png";
	public static String CYAN_BRICK = "cian.png";
	public static String GREEN_BRICK = "verde.png";
	public static String ORANGE_BRICK = "naranja.png";
	public static String SHIP_IMAGE = "nave.png";
	public static String BALL_IMAGE= "pelota.png";
	
	// Instancia Singleton
	private static ImagesCache instance= null;
	
	// HashMap para almacenar las imágenes
	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	
	/**
	 * Getter Singleton
	 * @return
	 */
	public static ImagesCache getInstance () {
		if (instance == null) instance = new ImagesCache();
		return instance;
	}
	
	/**
	 * Método para acceder a las imágenes desde fuera de la clase
	 * Primero se buscan en el almacén y luego en el sistema de ficheros
	 * @param name
	 * @return
	 */
	public BufferedImage getImage(String name) {
		BufferedImage img = sprites.get(name);
		if (img == null) {
			img = uploadImage("resources/images/" + name);
			sprites.put(name,img);
		}
		return img;
	}

	/**
	 * Método para cargar una imagen
	 * @param name
	 * @return
	 */
	private BufferedImage uploadImage(String name) {
		URL url=null;
		try {
			url = getClass().getResource(name);
			return ImageIO.read(url);
		} catch (Exception e) { // Si algo falla, se acaba el programa
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	
}
