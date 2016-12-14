package Game.Object.Button;

import Game.Object.GameObject;
import Graphics.Texture;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class Button implements GameObject{
	private Texture m_Texture[] = new Texture[2];
	private VertexArray m_Vao;
	private Matrix4f m_MVMatrix;
	private float m_PosX;
	private float m_PosY;
	private boolean m_isActive = false;
	
	public Button() {
		
	}
	
	public void setPath(String Activepath, String Inactivepath) {
		m_Texture[0] = new Texture(Activepath);
		m_Texture[1] = new Texture(Inactivepath);
	}
	public void setPos(float x, float y) {
		m_PosX = x;
		m_PosY = y;
		m_MVMatrix = Matrix4f.translate(m_PosX, m_PosY, 0);
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
		if(m_isActive) m_Texture[0].bind();
		else m_Texture[1].bind();
		m_Vao.draw(m_MVMatrix);
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
