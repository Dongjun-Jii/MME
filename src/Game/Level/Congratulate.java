package Game.Level;

import Game.*;
import Graphics.*;
import Maths.Matrix4f;
import Game.GameInfo;
import Game.Object.Button;

import static org.lwjgl.glfw.GLFW.*;


public class Congratulate implements Level{
	
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	private Button m_credit;
	
	public Congratulate() {		
		m_BackgroundT = TextureManager.getTexture(GameInfo.BG_CONGRATULATE_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 0);
		
		m_credit = new Button();
		m_credit.setActive(true);
		m_credit.setPath(GameInfo.BUTTON_CREDIT_PATH[0], GameInfo.BUTTON_CREDIT_PATH[1]);
		m_credit.setPos(960, 780);
	}
	
	@Override
	public void draw() {
		m_BackgroundT.bind();
		m_Background.draw(m_bgMatrix);
		
		m_credit.draw();
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressEvent(int key) {
		if(key == GLFW_KEY_ENTER){
				GameInfo.gotoCredit();			
		}
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
