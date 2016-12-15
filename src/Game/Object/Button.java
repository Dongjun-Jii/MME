package Game.Object;

import Game.GameInfo;
import Game.GameState;
import Graphics.Texture;
import Graphics.VertexArray;
import Maths.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;

public class Button implements GameObject{
	private Texture m_Texture[] = new Texture[2];
	private VertexArray m_Vao;
	private Matrix4f m_MVMatrix;
	
	private float m_PosX;
	private float m_PosY;
	
	private boolean m_isActive = false;
	
	public Button() {
		
	}
	
	public void setPath(String Inactivepath, String Activepath) {
		m_Texture[0] = new Texture(Inactivepath);
		m_Texture[1] = new Texture(Activepath);
		m_Vao = new VertexArray(m_Texture[0].getWidth(), m_Texture[0].getHeight(), 0, 1, 0, 1);
	}
	
	public void setPos(float x, float y) {
		m_PosX = x;
		m_PosY = y;
		m_MVMatrix = Matrix4f.translate(m_PosX, m_PosY, 0.5f);
	}
	
	public void setActive(boolean a) {
		m_isActive = a;
	}
	
	public float getLeft(){return -m_Texture[0].getWidth()/2 + m_PosX;}
	public float getRight(){return m_Texture[0].getWidth()/2 + m_PosX;}
	public float getTop(){return -m_Texture[0].getHeight()/2 + m_PosY;}
	public float getBotton(){return m_Texture[0].getHeight()/2 + m_PosY;}

	@Override
	public void draw() {
		if(!m_isActive) m_Texture[0].bind();
		else m_Texture[1].bind();
		m_Vao.draw(m_MVMatrix);
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
