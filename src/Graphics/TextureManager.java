package Graphics;

import java.util.*;

public class TextureManager {
	private static Map<String, Texture> m_Textures = new HashMap<String, Texture>();
	
	public static Texture getTexture(String path) {
		if(m_Textures.containsKey(path)) {
			return m_Textures.get(path);
		}else {
			Texture t = new Texture(path);
			m_Textures.put(path, t);
			return t;
		}
	}
}
