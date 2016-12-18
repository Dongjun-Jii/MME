package Game.Level;

import static org.lwjgl.glfw.GLFW.*;

import Game.GameInfo;
import Game.GameState;
import Game.Object.Button;
import Graphics.Texture;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class StageSelect implements Level{
	enum flag{menu, stage1, stage2, stage3, stage4, stage5, stage6, down, up}
	private flag m_flag = flag.menu;
	private int m_StageOnOff=0;
	
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	private Button m_menu;
	private Button m_disabledquit;	
	private Button m_Stage1;		
	private Button m_Stage2;
	private Button m_Stage3;
	private Button m_Stage4;
	private Button m_Stage5;
	private Button m_Stage6;
	private Button m_Down;
	private Button m_Up;
	
	public StageSelect() {
		
		m_BackgroundT = new Texture(GameInfo.BG_BLUE_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 0);
		
		m_menu = new Button();
		m_menu.setActive(true);
		m_menu.setPath(GameInfo.BUTTON_MENU_PATH[0], GameInfo.BUTTON_MENU_PATH[1]);
		m_menu.setPos(355, 750);
		
		m_disabledquit = new Button();
		m_disabledquit.setPath(GameInfo.BUTTON_QUIT_PATH[2], GameInfo.BUTTON_QUIT_PATH[2]);
		m_disabledquit.setPos(335, 900);
		
		m_Stage1 = new Button();
		m_Stage1.setActive(false);
		m_Stage1.setPath(GameInfo.BUTTON_STAGE_PATH[0], GameInfo.BUTTON_STAGE_PATH[1]);
		m_Stage1.setPos(1495, 600);
		
		m_Stage2 = new Button();
		m_Stage2.setActive(false);
		m_Stage2.setPath(GameInfo.BUTTON_STAGE_PATH[2], GameInfo.BUTTON_STAGE_PATH[3]);
		m_Stage2.setPos(1495, 750);
		
		m_Stage3 = new Button();
		m_Stage3.setActive(false);
		m_Stage3.setPath(GameInfo.BUTTON_STAGE_PATH[4], GameInfo.BUTTON_STAGE_PATH[5]);
		m_Stage3.setPos(1495, 900);
		
		m_Stage4 = new Button();
		m_Stage4.setActive(false);
		m_Stage4.setPath(GameInfo.BUTTON_STAGE_PATH[6], GameInfo.BUTTON_STAGE_PATH[7]);
		m_Stage4.setPos(1495, 600);
		
		m_Stage5 = new Button();
		m_Stage5.setActive(false);
		m_Stage5.setPath(GameInfo.BUTTON_STAGE_PATH[8], GameInfo.BUTTON_STAGE_PATH[9]);
		m_Stage5.setPos(1495, 750);
		
		m_Stage6 = new Button();
		m_Stage6.setActive(false);
		m_Stage6.setPath(GameInfo.BUTTON_STAGE_PATH[10], GameInfo.BUTTON_STAGE_PATH[11]);
		m_Stage6.setPos(1495, 900);
		
		m_Up = new Button();
		m_Up.setActive(false);
		m_Up.setPath(GameInfo.BUTTON_UPARROW_PATH[0], GameInfo.BUTTON_UPARROW_PATH[1]);
		m_Up.setPos(1495, 485);
		
		m_Down = new Button();
		m_Down.setActive(false);
		m_Down.setPath(GameInfo.BUTTON_DOWNARROW_PATH[0], GameInfo.BUTTON_DOWNARROW_PATH[1]);
		m_Down.setPos(1495,1015);
	}
	
	@Override
	public void draw() {
		m_BackgroundT.bind();
		m_Background.draw(m_bgMatrix);
		
		m_menu.draw();
		m_disabledquit.draw();
		
		if(m_StageOnOff==0){
			m_Stage1.draw();
			m_Stage2.draw();
			m_Stage3.draw();
			m_Down.draw();
		}
		else if(m_StageOnOff==1){
			m_Up.draw();
			m_Stage4.draw();
			m_Stage5.draw();
			m_Stage6.draw();
			m_Down.draw();
		}
		
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressEvent(int key) {
		// TODO Auto-generated method s tub
		if(key == GLFW_KEY_RIGHT){
			if(m_flag == flag.menu){
				m_menu.setActive(false);
				m_Stage1.setActive(true);
				m_flag = flag.stage1;
			}
		}
		else if(key == GLFW_KEY_DOWN){
			if(m_flag == flag.stage1){
				m_Stage1.setActive(false);
				m_Stage2.setActive(true);
				m_flag = flag.stage2;
			}
			else if(m_flag == flag.stage2){
				m_Stage2.setActive(false);
				m_Stage3.setActive(true);
				m_flag = flag.stage3;
			}
			else if(m_flag == flag.stage3){
				m_Stage3.setActive(false);
				m_Down.setActive(true);
				m_flag = flag.down;
			}
			else if(m_flag == flag.stage4){
				m_Stage4.setActive(false);
				m_Stage5.setActive(true);
				m_flag = flag.stage5;
			}
			else if(m_flag == flag.stage5){
				m_Stage5.setActive(false);
				m_Stage6.setActive(true);
				m_flag = flag.stage6;
			}
			else if(m_flag == flag.stage6){
				m_Stage6.setActive(false);
				m_Down.setActive(true);
				m_flag = flag.down;
			}
			else if(m_flag == flag.up){
				if(m_StageOnOff == 1){
					m_Up.setActive(false);
					m_Stage4.setActive(true);
					m_flag = flag.stage4;
				}
			}
		}
		else if(key == GLFW_KEY_UP){
			if(m_flag == flag.stage2){
				m_Stage2.setActive(false);
				m_Stage1.setActive(true);
				m_flag = flag.stage1;
			}
			else if(m_flag == flag.stage3){
				m_Stage3.setActive(false);
				m_Stage2.setActive(true);
				m_flag = flag.stage2;
			}
			else if(m_flag == flag.stage4){
				m_Stage4.setActive(false);
				m_Up.setActive(true);
				m_flag = flag.up;
			}
			else if(m_flag == flag.stage5){
				m_Stage5.setActive(false);
				m_Stage4.setActive(true);
				m_flag = flag.stage4;
			}
			else if(m_flag == flag.stage6){
				m_Stage6.setActive(false);
				m_Stage5.setActive(true);
				m_flag = flag.stage5;
			}
			else if(m_flag == flag.down){
				if(m_StageOnOff == 0){
					m_Down.setActive(false);
					m_Stage3.setActive(true);
					m_flag = flag.stage3;
				}
				else if(m_StageOnOff == 1){
					m_Down.setActive(false);
					m_Stage6.setActive(true);
					m_flag = flag.stage6;
				}
			}
		}
		else if (key == GLFW_KEY_LEFT){
			if(m_flag == flag.stage1){
				m_Stage1.setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage2){
				m_Stage2.setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage3){
				m_Stage3.setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
		}
		else if (key == GLFW_KEY_ENTER){
			if(m_flag == flag.menu){
				GameInfo.gotoTitle();
			}
			else if(m_flag == flag.stage1){
				GameInfo.gotoGame(1);
			}
			else if(m_flag == flag.stage2){
				GameInfo.gotoGame(2);
			}
			else if(m_flag == flag.stage3){
				GameInfo.gotoGame(3);
			}
			else if(m_flag == flag.stage4){
				GameInfo.gotoGame(4);
			}
			else if(m_flag == flag.stage5){
				GameInfo.gotoGame(5);
			}
			else if(m_flag == flag.stage6){
				GameInfo.gotoGame(6);
			}
			else if(m_flag == flag.up){
				if(m_StageOnOff == 1){
					m_StageOnOff = 0;
					m_Up.setActive(false);
					m_Stage3.setActive(true);
					m_flag = flag.stage3;
				}
				else if(m_StageOnOff == 2){
					//
				}
			}
			else if(m_flag == flag.down){
				if(m_StageOnOff == 0){
					m_StageOnOff = 1;
					m_Down.setActive(false);
					m_Stage4.setActive(true);
					m_flag = flag.stage4;
				}
				else if(m_StageOnOff==1){
					//
				}
			}
		}
	}

	@Override
	public void keyReleaseEvent(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressEvent(int button) {
		// TODO Auto-generated method stub
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
				else if((x>=m_Stage4.getLeft())&&(x<=m_Stage4.getRight())&&(y>=m_Stage4.getTop())&&(y<=m_Stage4.getBotton())){
					GameInfo.gotoGame(4);
				}
				else if((x>=m_Stage5.getLeft())&&(x<=m_Stage5.getRight())&&(y>=m_Stage5.getTop())&&(y<=m_Stage5.getBotton())){
					GameInfo.gotoGame(5);
				}
				else if((x>=m_Stage6.getLeft())&&(x<=m_Stage6.getRight())&&(y>=m_Stage6.getTop())&&(y<=m_Stage6.getBotton())){
					GameInfo.gotoGame(6);
				}
				else if((x>=m_Up.getLeft())&&(x<=m_Up.getRight())&&(y>=m_Up.getTop())&&(y<=m_Up.getBotton())){
					if(m_StageOnOff == 1){
						m_StageOnOff = 0;
						m_Up.setActive(false);
						m_Stage3.setActive(true);
						m_flag = flag.stage3;
					}
					else if(m_StageOnOff == 2){
						//
					}
				}
				else if((x>=m_Down.getLeft())&&(x<=m_Down.getRight())&&(y>=m_Down.getTop())&&(y<=m_Down.getBotton())){
					if(m_StageOnOff == 0){
						m_StageOnOff = 1;
						m_Down.setActive(false);
						m_Stage4.setActive(true);
						m_flag = flag.stage4;
					}
					else if(m_StageOnOff==1){
						//
					}
				}
		}
	}

	@Override
	public void mouseReleaseEvent(int button) {
		// TODO Auto-generated method stub
		
	}

}
