package Game.Level;

import static org.lwjgl.glfw.GLFW.*;

import Game.GameInfo;
import Game.GameState;
import Game.Object.Button;
import Graphics.Texture;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class StageSelect implements Level{
	enum flag{menu, stage1, stage2, stage3, stage4, stage5, stage6, stage7, stage8, stage9, stage10, down, up}
	private static int BUTTONNUM = 10;
	
	private flag m_flag = flag.menu;
	private int m_StageOnOff=0;
	
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	private Button m_menu;
	private Button m_disabledquit;	
	private Button m_Stage[];
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
		
		m_Stage = new Button[BUTTONNUM];
		
		for(int i = 0; i < BUTTONNUM; i++){
			m_Stage[i] = new Button();
			m_Stage[i].setActive(false);
			m_Stage[i].setPath(GameInfo.BUTTON_STAGE_PATH[i*2], GameInfo.BUTTON_STAGE_PATH[i*2+1]);
		}
		
		m_Stage[0].setPos(1495, 600);
		m_Stage[1].setPos(1495, 750);
		m_Stage[2].setPos(1495, 900);
		m_Stage[3].setPos(1495, 600);
		m_Stage[4].setPos(1495, 750);
		m_Stage[5].setPos(1495, 900);
		m_Stage[6].setPos(1495, 600);
		m_Stage[7].setPos(1495, 750);
		m_Stage[8].setPos(1495, 900);
		m_Stage[9].setPos(1495, 750);
		
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
			m_Stage[0].draw();
			m_Stage[1].draw();
			m_Stage[2].draw();
			m_Down.draw();
		}
		else if(m_StageOnOff==1){
			m_Up.draw();
			m_Stage[3].draw();
			m_Stage[4].draw();
			m_Stage[5].draw();
			m_Down.draw();
		}
		else if(m_StageOnOff == 2){
			m_Up.draw();
			m_Stage[6].draw();
			m_Stage[7].draw();
			m_Stage[8].draw();
			m_Down.draw();
		}
		else if(m_StageOnOff == 3){
			m_Up.draw();
			m_Stage[9].draw();
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
				if(m_StageOnOff == 0){
					m_menu.setActive(false);
					m_Stage[0].setActive(true);
					m_flag = flag.stage1;
				}
				else if(m_StageOnOff == 1){
					m_menu.setActive(false);
					m_Stage[3].setActive(true);
					m_flag = flag.stage4;
				}
				else if(m_StageOnOff == 2){
					m_menu.setActive(false);
					m_Stage[6].setActive(true);
					m_flag = flag.stage7;
				}
				else if(m_StageOnOff == 3){
					m_menu.setActive(false);
					m_Stage[9].setActive(true);
					m_flag = flag.stage10;
				}
			}
		}
		
		else if(key == GLFW_KEY_DOWN){
			if(m_flag == flag.stage1){
				m_Stage[0].setActive(false);
				m_Stage[1].setActive(true);
				m_flag = flag.stage2;
			}
			else if(m_flag == flag.stage2){
				m_Stage[1].setActive(false);
				m_Stage[2].setActive(true);
				m_flag = flag.stage3;
			}
			else if(m_flag == flag.stage3){
				m_Stage[2].setActive(false);
				m_Down.setActive(true);
				m_flag = flag.down;
			}
			else if(m_flag == flag.stage4){
				m_Stage[3].setActive(false);
				m_Stage[4].setActive(true);
				m_flag = flag.stage5;
			}
			else if(m_flag == flag.stage5){
				m_Stage[4].setActive(false);
				m_Stage[5].setActive(true);
				m_flag = flag.stage6;
			}
			else if(m_flag == flag.stage6){
				m_Stage[5].setActive(false);
				m_Down.setActive(true);
				m_flag = flag.down;
			}
			else if(m_flag == flag.stage7){
				m_Stage[6].setActive(false);
				m_Stage[7].setActive(true);
				m_flag = flag.stage8;
			}
			else if(m_flag == flag.stage8){
				m_Stage[7].setActive(false);
				m_Stage[8].setActive(true);
				m_flag = flag.stage9;
			}
			else if(m_flag == flag.stage9){
				m_Stage[8].setActive(false);
				m_Down.setActive(true);
				m_flag = flag.down;
			}			
			else if(m_flag == flag.up){
				if(m_StageOnOff == 1){
					m_Up.setActive(false);
					m_Stage[3].setActive(true);
					m_flag = flag.stage4;
				}
				else if(m_StageOnOff == 2){
					m_Up.setActive(false);
					m_Stage[6].setActive(true);
					m_flag = flag.stage7;
				}
				else if(m_StageOnOff == 3){
					m_Up.setActive(false);
					m_Stage[9].setActive(true);
					m_flag = flag.stage10;
				}
			}
		}
		
		else if(key == GLFW_KEY_UP){
			if(m_flag == flag.stage1){
				m_Stage[0].setActive(false);
				m_Down.setActive(true);
				m_flag = flag.down;
			}
			else if(m_flag == flag.stage2){
				m_Stage[1].setActive(false);
				m_Stage[0].setActive(true);
				m_flag = flag.stage1;
			}
			else if(m_flag == flag.stage3){
				m_Stage[2].setActive(false);
				m_Stage[1].setActive(true);
				m_flag = flag.stage2;
			}
			else if(m_flag == flag.stage4){
				m_Stage[3].setActive(false);
				m_Up.setActive(true);
				m_flag = flag.up;
			}
			else if(m_flag == flag.stage5){
				m_Stage[4].setActive(false);
				m_Stage[3].setActive(true);
				m_flag = flag.stage4;
			}
			else if(m_flag == flag.stage6){
				m_Stage[5].setActive(false);
				m_Stage[4].setActive(true);
				m_flag = flag.stage5;
			}
			else if(m_flag == flag.stage7){
				m_Stage[6].setActive(false);
				m_Up.setActive(true);
				m_flag = flag.up;
			}
			else if(m_flag == flag.stage8){
				m_Stage[7].setActive(false);
				m_Stage[6].setActive(true);
				m_flag = flag.stage7;
			}
			else if(m_flag == flag.stage9){
				m_Stage[8].setActive(false);
				m_Stage[7].setActive(true);
				m_flag = flag.stage8;
			}
			else if(m_flag == flag.stage10){
				m_Stage[9].setActive(false);
				m_Up.setActive(true);
				m_flag = flag.up;
			}
			
			else if(m_flag == flag.down){
				if(m_StageOnOff == 0){
					m_Down.setActive(false);
					m_Stage[2].setActive(true);
					m_flag = flag.stage3;
				}
				else if(m_StageOnOff == 1){
					m_Down.setActive(false);
					m_Stage[5].setActive(true);
					m_flag = flag.stage6;
				}
				else if(m_StageOnOff == 2){
					m_Down.setActive(false);
					m_Stage[8].setActive(true);
					m_flag = flag.stage9;
				}
			}
		}
		
		else if (key == GLFW_KEY_LEFT){
			if(m_flag == flag.stage1){
				m_Stage[0].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage2){
				m_Stage[1].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage3){
				m_Stage[2].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage4){
				m_Stage[3].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage5){
				m_Stage[4].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage6){
				m_Stage[5].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage7){
				m_Stage[6].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage8){
				m_Stage[7].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage9){
				m_Stage[8].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.stage10){
				m_Stage[9].setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.down){
				m_Down.setActive(false);
				m_menu.setActive(true);
				m_flag = flag.menu;
			}
			else if(m_flag == flag.up){
				m_Up.setActive(false);
				m_menu.setActive(true);
				m_flag = flag.up;
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
			else if(m_flag == flag.stage7){
				GameInfo.gotoGame(7);
			}
			else if(m_flag == flag.stage8){
				GameInfo.gotoGame(8);
			}
			else if(m_flag == flag.stage9){
				GameInfo.gotoGame(9);
			}
			else if(m_flag == flag.stage10){
				GameInfo.gotoGame(10);
			}
			
			else if(m_flag == flag.up){
				if(m_StageOnOff == 1){
					m_StageOnOff = 0;
					m_Up.setActive(false);
					m_Stage[2].setActive(true);
					m_flag = flag.stage3;
				}
				else if(m_StageOnOff == 2){
					m_StageOnOff = 1;
					m_Up.setActive(false);
					m_Stage[5].setActive(true);
					m_flag = flag.stage6;
				}
				else if(m_StageOnOff == 3){
					m_StageOnOff = 2;
					m_Up.setActive(false);
					m_Stage[8].setActive(true);
					m_flag = flag.stage9;
				}
			}
			
			else if(m_flag == flag.down){
				if(m_StageOnOff == 0){
					m_StageOnOff = 1;
					m_Down.setActive(false);
					m_Stage[3].setActive(true);
					m_flag = flag.stage4;
				}
				else if(m_StageOnOff==1){
					m_StageOnOff = 2;
					m_Down.setActive(false);
					m_Stage[6].setActive(true);
					m_flag = flag.stage7;
				}
				else if(m_StageOnOff==2){
					m_StageOnOff = 3;
					m_Down.setActive(false);
					m_Stage[9].setActive(true);
					m_flag = flag.stage10;
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
				
				else if((x>=m_Up.getLeft())&&(x<=m_Up.getRight())&&(y>=m_Up.getTop())&&(y<=m_Up.getBotton())){
					if(m_StageOnOff == 1){
						m_StageOnOff = 0;
						m_Stage[3].setActive(false);
						m_Stage[4].setActive(false);
						m_Stage[5].setActive(false);
						m_menu.setActive(false);
						m_Up.setActive(false);
						m_Stage[2].setActive(true);
						m_flag = flag.stage3;
					}
					else if(m_StageOnOff == 2){
						//
					}
				}
				
				else if((x>=m_Down.getLeft())&&(x<=m_Down.getRight())&&(y>=m_Down.getTop())&&(y<=m_Down.getBotton())){
					if(m_StageOnOff == 0){
						m_StageOnOff = 1;
						m_Stage[0].setActive(false);
						m_Stage[1].setActive(false);
						m_Stage[2].setActive(false);
						m_menu.setActive(false);
						m_Down.setActive(false);
						m_Stage[3].setActive(true);
						m_flag = flag.stage4;
					}
					else if(m_StageOnOff==1){
						//
					}
				}
				
				else{
					for(int i = 0; i < BUTTONNUM; i++){
						if((x>=m_Stage[i].getLeft())&&(x<=m_Stage[i].getRight())&&(y>=m_Stage[i].getTop())&&(y<=m_Stage[i].getBotton())){
							GameInfo.gotoGame(i+1);//1ź
						}
				}
			}
		}
	}

	@Override
	public void mouseReleaseEvent(int button) {
		// TODO Auto-generated method stub
		
	}

}
