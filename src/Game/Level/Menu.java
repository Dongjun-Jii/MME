package Game.Level;

import java.util.*;

import Game.GameInfo;
import Game.GameState;
import Game.Object.GameObject;
import Graphics.Texture;
import Graphics.TextureManager;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class Menu implements Level{
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	public Menu() {
		m_BackgroundT = TextureManager.loadTexture(GameInfo.MENU_BG_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), m_BackgroundT.getHeight(), 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(-910, -540, 0.5f);
	}
	
	@Override
	public void draw() {
		m_BackgroundT.bind();
		m_Background.draw(m_bgMatrix);
	}
	
	@Override
	public void update(double deltaTime) {
	}
	
	@Override
	public void keyPressEvent(int key) {
	}

	@Override
	public void keyReleaseEvent(int key) {
	}

	@Override
	public void mousePressEvent(int button) {
		
	}

	@Override
	public void mouseReleaseEvent(int button) {
		
	}
}