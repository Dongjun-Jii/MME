package Game.Level;

import Game.GameInfo;
import Game.GameState;
import Game.Object.Button;
import Graphics.*;
import Maths.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;

public class Title implements Level{
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	private Button m_StartButton;
	private Button m_QuitButton;
	
	public Title() {
		m_BackgroundT = TextureManager.getTexture(GameInfo.BG_BLUE_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 0);
		
		m_StartButton = new Button();
		m_StartButton.setActive(false);
		m_StartButton.setPath(GameInfo.BUTTON_START_PATH[0], GameInfo.BUTTON_START_PATH[1]);
		m_StartButton.setPos(405, 760);
		
		m_QuitButton = new Button();
		m_QuitButton.setActive(false);
		m_QuitButton.setPath(GameInfo.BUTTON_QUIT_PATH[0], GameInfo.BUTTON_QUIT_PATH[1]);
		m_QuitButton.setPos(405, 910);
	}
	
	@Override
	public void draw() {
		m_BackgroundT.bind();
		m_Background.draw(m_bgMatrix);
		
		m_StartButton.draw();
		m_QuitButton.draw();
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressEvent(int key) {
		if(key == GLFW_KEY_S){
			GameInfo.gotoStageSelect();
		}
		else if (key == GLFW_KEY_Q){
			GameInfo.gameEnd();
		}
	}

	@Override
	public void keyReleaseEvent(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressEvent(int button) {
		if(button == GLFW_MOUSE_BUTTON_LEFT){
			double x = GameState.mouseInput.getCursorX();
			double y = GameState.mouseInput.getCursorY();
				if((x>=140)&&(x<=670)&&(y>=700)&&(y<=820)){
					GameInfo.gotoStageSelect();
				}
				else if((x>=140)&&(x<=670)&&(y>=850)&&(y<=970)){
					GameInfo.gameEnd();
				}
		}
	}

	@Override
	public void mouseReleaseEvent(int button) {
		// TODO Auto-generated method stub
		
	}

}
