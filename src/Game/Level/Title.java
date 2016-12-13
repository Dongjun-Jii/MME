package Game.Level;

import java.util.*;

import Game.GameInfo;
import Game.Object.GameObject;
import Graphics.Texture;
import Graphics.TextureManager;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class Title implements Level{
	private List<GameObject> objectList;
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	public Title() {
		objectList = new ArrayList<GameObject>();
		m_BackgroundT = TextureManager.loadTexture(GameInfo.TITLE_BG_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), m_BackgroundT.getHeight(), 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(-910, -540, 0.5f);
	}
	
	@Override
	public void draw() {
		m_BackgroundT.bind();
		m_Background.draw(m_bgMatrix);
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.draw();
			}
		}
	}
	
	@Override
	public void update(double deltaTime) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.update(deltaTime);
				
			}
		}
	}
	
	public void createObject(GameObject obj) {
		objectList.add(obj);
	}
	
	@Override
	public void keyPressEvent(int key) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.keyPressEvent(key);
			}
		}
	}

	@Override
	public void keyReleaseEvent(int key) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.keyReleaseEvent(key);
			}
		}
	}

	@Override
	public void mousePressEvent(int button) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.mousePressEvent(button);
			}
		}
		
	}

	@Override
	public void mouseReleaseEvent(int button) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.mouseReleaseEvent(button);
			}
		}
	}
}