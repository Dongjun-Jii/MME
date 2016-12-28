package Game.Object;

import Graphics.Texture;
import Graphics.TextureManager;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class Bullet implements GameObject {
	private Texture m_Texture;
	private VertexArray m_Vao;
	private Matrix4f m_MVMatrix;
	
	private float m_PosX;
	private float m_PosY;
	
	private float m_Speed = 600;
	private float m_Direction;
	
	private float m_LeftLimit;
	private float m_RightLimit;
	private float m_TopLimit;
	private float m_BottomLimit;
	
	private boolean m_shouleDelete = false;
	
	public Bullet (String path, int flag, float x, float y, float dir) {
		m_Texture = TextureManager.getTexture(path);
		m_Vao = new VertexArray(m_Texture.getWidth(), m_Texture.getHeight(), 0, 1, 0, 1);
		m_PosX = x;
		m_PosY = y;
		m_Direction = dir;
		switch (flag){
			case 0: m_Speed = 450; break;		//총알의 색에 따라 속도가 다름
			case 1: m_Speed = 300; break;
			case 2: m_Speed = 600; break;
		}
	}
	
	public void setMoveRange(float left, float right, float top, float bottom) {
		m_LeftLimit = left;
		m_RightLimit = right;
		m_TopLimit = top;
		m_BottomLimit = bottom;
	}
	
	public void setDirection(float dir) {
		m_Direction = dir;
	}
	
	public float getX() {
		return m_PosX;
	}
	
	public float getY() {
		return m_PosY;
	}
	
	public boolean shouldDelete() {
		return m_shouleDelete;
	}
	
	@Override
	public void draw() {
		m_MVMatrix = Matrix4f.translate(m_PosX, m_PosY, 0);
		m_Texture.bind();
		m_Vao.draw(m_MVMatrix);
	}

	@Override
	public void update(double deltaTime) {
		float deltaX = (float) (Math.sin(m_Direction) * m_Speed * deltaTime);
		float deltaY = (float) (Math.cos(m_Direction) * m_Speed * deltaTime);
		
		m_PosX = Math.max(m_LeftLimit, Math.min(m_RightLimit, m_PosX + deltaX));
		m_PosY = Math.max(m_TopLimit, Math.min(m_BottomLimit, m_PosY + deltaY));
		
		if(m_PosX == m_LeftLimit || m_PosX == m_RightLimit || m_PosY == m_TopLimit || m_PosY == m_BottomLimit)
			m_shouleDelete = true;
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
