package Game.Level;

import static org.lwjgl.glfw.GLFW.*;

import Game.GameInfo;
import Game.GameState;
import Game.Object.Button;
import Graphics.Texture;
import Graphics.TextureManager;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class Clear implements Level{
	
	enum flag {restart, Continue, menu}
	private flag m_flag = flag.restart;
	
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	private Button m_restart;
	private Button m_menu;
	private Button m_next;
	
	public Clear() {
		
		m_BackgroundT = TextureManager.getTexture(GameInfo.BG_CLEAR_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 0);
		
		m_restart = new Button();
		m_restart.setActive(true);
		m_restart.setPath(GameInfo.BUTTON_RESTART_PATH[0], GameInfo.BUTTON_RESTART_PATH[1]);
		m_restart.setPos(475, 650);
		
		m_next = new Button();
		m_next.setActive(false);
		m_next.setPath(GameInfo.BUTTON_NEXT_PATH[0], GameInfo.BUTTON_NEXT_PATH[1]);
		m_next.setPos(340, 770);
		
		m_menu = new Button();
		m_menu.setActive(false);
		m_menu.setPath(GameInfo.BUTTON_MENU_PATH[0], GameInfo.BUTTON_MENU_PATH[2]);
		m_menu.setPos(340, 900);
		
		
		
	}
	
	@Override
	public void draw() {
		m_BackgroundT.bind();
		m_Background.draw(m_bgMatrix);
		
		m_restart.draw();
		m_next.draw();
		m_menu.draw();
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressEvent(int key) {
		if(key == GLFW_KEY_DOWN){
			if(m_flag == flag.restart){
				m_restart.setActive(false);
				m_next.setActive(true);
				m_flag = flag.Continue;
			}
			else if(m_flag == flag.Continue){
				m_next.setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.menu){
				m_menu.setActive(false);
				m_restart.setActive(true);
				m_flag = flag.restart;
			}
		}
		else if(key == GLFW_KEY_UP){
			if(m_flag == flag.restart){
				m_restart.setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.Continue){
				m_next.setActive(false);
				m_restart.setActive(true);
				m_flag = flag.restart;
			}
			else if (m_flag == flag.menu){
				m_menu.setActive(false);
				m_next.setActive(true);
				m_flag = flag.Continue;
			}
		}
		else if(key == GLFW_KEY_ENTER){
			if(m_flag == flag.restart){
				GameInfo.gameRestart();
			}
			else if(m_flag == flag.Continue){
				GameInfo.gotoGame(GameState.stage + 1);
			}
			else if(m_flag == flag.menu){
				GameInfo.gotoStageSelect();
			}
		}
	}

	@Override
	public void keyReleaseEvent(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressEvent(int button) {
		double x = GameState.mouseInput.getCursorX();
		double y = GameState.mouseInput.getCursorY();
			if((x>=m_restart.getLeft())&&(x<=m_restart.getRight())&&(y>=m_restart.getTop())&&(y<=m_restart.getBotton())){
				GameInfo.gameRestart();
			}
			else if((x>=m_next.getLeft())&&(x<=m_next.getRight())&&(y>=m_next.getTop())&&(y<=m_next.getBotton())){
				GameInfo.gotoGame(GameState.stage + 1);
			}
			else if((x>=m_menu.getLeft())&&(x<=m_menu.getRight())&&(y>=m_menu.getTop())&&(y<=m_menu.getBotton())){
				GameInfo.gotoStageSelect();
			}
	}

	@Override
	public void mouseReleaseEvent(int button) {
		// TODO Auto-generated method stub
		
	}

}
