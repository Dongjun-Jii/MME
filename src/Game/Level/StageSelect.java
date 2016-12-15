package Game.Level;

import Game.GameInfo;
import Game.GameState;
import Game.Object.Button;
import Graphics.Texture;
import Graphics.VertexArray;
import Maths.Matrix4f;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class StageSelect implements Level{
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	private Button m_menu;
	private Button m_Stage1;
	private Button m_Stage2;
	private Button m_Stage3;
	
	public StageSelect() {
		m_BackgroundT = new Texture(GameInfo.BG_BLUE_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 0);
		
		m_menu = new Button();
		m_menu.setActive(false);
		m_menu.setPath(GameInfo.BUTTON_MENU_PATH[0], GameInfo.BUTTON_MENU_PATH[1]);
		m_menu.setPos(355, 760);
		
		m_Stage1 = new Button();
		m_Stage1.setActive(false);
		m_Stage1.setPath(GameInfo.BUTTON_STAGE_PATH[0], GameInfo.BUTTON_STAGE_PATH[1]);
		m_Stage1.setPos(1495, 640);
		
		m_Stage2 = new Button();
		m_Stage2.setActive(false);
		m_Stage2.setPath(GameInfo.BUTTON_STAGE_PATH[2], GameInfo.BUTTON_STAGE_PATH[3]);
		m_Stage2.setPos(1495, 790);
		
		m_Stage3 = new Button();
		m_Stage3.setActive(false);
		m_Stage3.setPath(GameInfo.BUTTON_STAGE_PATH[4], GameInfo.BUTTON_STAGE_PATH[5]);
		m_Stage3.setPos(1495, 930);
	}
	
	@Override
	public void draw() {
		m_BackgroundT.bind();
		m_Background.draw(m_bgMatrix);
		
		m_menu.draw();
		m_Stage1.draw();
		m_Stage2.draw();
		m_Stage3.draw();
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressEvent(int key) {
		// TODO Auto-generated method s tub
		
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
				if((x>=m_menu.getLeft())&&(x<=m_menu.getRight())&&(y>=m_menu.getTop())&&(y<=m_menu.getBotton())){
					GameInfo.gotoTitle();
				}
				else if((x>=m_Stage1.getLeft())&&(x<=m_Stage1.getRight())&&(y>=m_Stage1.getTop())&&(y<=m_Stage1.getBotton())){
					GameInfo.gotoGame(1);//1ź
				}
				else if((x>=m_Stage2.getLeft())&&(x<=m_Stage2.getRight())&&(y>=m_Stage2.getTop())&&(y<=m_Stage2.getBotton())){
					GameInfo.gotoGame(2);
				}
				else if((x>=m_Stage3.getLeft())&&(x<=m_Stage3.getRight())&&(y>=m_Stage3.getTop())&&(y<=m_Stage3.getBotton())){
					GameInfo.gotoGame(3);
				}
		}
	}

	@Override
	public void mouseReleaseEvent(int button) {
		// TODO Auto-generated method stub
		
	}

}
