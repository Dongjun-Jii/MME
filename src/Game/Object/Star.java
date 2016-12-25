package Game.Object;

import Game.GameState;
import Graphics.Texture;
import Graphics.TextureManager;
import Graphics.VertexArray;
import Maths.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;

public class Star implements GameObject{
	private Texture m_Texture;
	private VertexArray m_Vao;
	private Matrix4f m_MVMatrix;
	
	private float m_PosX;
	private float m_PosY;
	
	private float m_PreX;
	private float m_PreY;
	
	private float m_LeftLimit;
	private float m_RightLimit;
	private float m_TopLimit;
	private float m_BottomLimit;
	
	private float m_Hspeed = 400;
	private float m_Vspeed = 400;
	
	public Star(String path) {
		m_Texture = TextureManager.getTexture(path);
		m_Vao = new VertexArray(m_Texture.getWidth(), m_Texture.getHeight(), 0, 1, 0, 1);
	}
	
	public void setMoveRange(float left, float right, float top, float bottom) {
		m_LeftLimit = left;
		m_RightLimit = right;
		m_TopLimit = top;
		m_BottomLimit = bottom;
	}
	
	public void setPosition(float x, float y) {
		m_PosX = x;
		m_PosY = y;
	}
	
	public float getX() {
		return m_PosX;
	}
	
	public float getY() {
		return m_PosY;
	}
	
	public float getpreX() {
		return m_PreX;
	}
	
	public float getpreY() {
		return m_PreY;
	}

	@Override
	public void draw() {
		m_Texture.bind();
		m_MVMatrix = Matrix4f.translate(m_PosX, m_PosY, 0);
		m_Vao.draw(m_MVMatrix);
	}

	@Override
	public void update(double deltaTime) {
		float deltaX = 0;
		float deltaY = 0;
		
		if(GameState.keyInput.isKeyPressed(GLFW_KEY_LEFT)) {
			deltaX += -m_Vspeed * deltaTime;
		}
		if(GameState.keyInput.isKeyPressed(GLFW_KEY_RIGHT)) {
			deltaX += m_Vspeed * deltaTime;
		}
		if(GameState.keyInput.isKeyPressed(GLFW_KEY_UP)) {
			deltaY += -m_Hspeed * deltaTime;
		}
		if(GameState.keyInput.isKeyPressed(GLFW_KEY_DOWN)) {
			deltaY += m_Hspeed * deltaTime;
		}
		
		m_PreX = m_PosX;
		m_PreY = m_PosY;
		m_PosX = Math.max(m_LeftLimit, Math.min(m_RightLimit, m_PosX + deltaX));
		m_PosY = Math.max(m_TopLimit, Math.min(m_BottomLimit, m_PosY + deltaY));
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
