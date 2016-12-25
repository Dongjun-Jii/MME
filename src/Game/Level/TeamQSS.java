package Game.Level;

import Game.*;
import Graphics.*;
import Maths.Matrix4f;

public class TeamQSS implements Level{
	
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	public TeamQSS() {
		
		m_BackgroundT = TextureManager.getTexture(GameInfo.BG_QSS_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 0);
		GameInfo.gotoCover();
	}
	
	@Override
	public void draw() {
		m_BackgroundT.bind();
		m_Background.draw(m_bgMatrix);
		
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressEvent(int key) {
		GameInfo.gotoCover();
	}

	@Override
	public void keyReleaseEvent(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressEvent(int button) {
	}

	@Override
	public void mouseReleaseEvent(int button) {
		// TODO Auto-generated method stub
		
	}

}
