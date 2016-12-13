package Graphics;

import java.util.*;

public class TextureManager {
	private static Map<String, Texture> m_tmap = new HashMap<String, Texture>();
	
	public static Texture loadTexture(String path) {
		if(m_tmap.containsKey(path)) {
			return m_tmap.get(path);
		}
		else {
			Texture t = new Texture(path);
			m_tmap.put(path, t);
			return t;
		}
	}
}