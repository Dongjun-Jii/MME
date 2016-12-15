package Game.Level;

import Game.GameInfo;
import Graphics.Texture;
import Graphics.TextureManager;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class GameStage implements Level{
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	private int m_Stage;
	
	public GameStage() {
		m_BackgroundT = TextureManager.getTexture("");
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 0);
	}
	
	

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressEvent(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleaseEvent(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressEvent(int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleaseEvent(int button) {
		// TODO Auto-generated method stub
		
	}
}
