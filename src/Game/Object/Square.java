package Game.Object;

import Game.GameObject;
import Game.GameState;
import Graphics.Texture;
import Graphics.VertexArray;
import Maths.Matrix4f;

import static org.lwjgl.glfw.GLFW.*;

public class Square implements GameObject{
	private Texture m_Texture;
	
	private float m_PosX = 960f;
	private float m_PosY = 810f;
	private float m_Depth = 0.0f;
	
	private float m_Angle = 0.0f;
	
	private float m_SizeX = 1.0f;
	private float m_SizeY = 1.0f;
	
	private float m_VSpeed = 150.0f;
	private float m_HSpeed = 150.0f;
	
	private VertexArray m_Vao;
	
	public Square() {
		m_Texture = new Texture("");
		m_Vao = new VertexArray(m_Texture.getWidth(), m_Texture.getHeight(), 0.0f, 1.0f, 0.0f, 1.0f);
	}
	
	@Override
	public void draw() {
		Matrix4f mvMatrix = Matrix4f.translate(m_PosX, m_PosY, m_Depth).multiply(Matrix4f.rotate(m_Angle, 0.0f, 0.0f, 1.0f)).multiply(Matrix4f.scale(m_SizeX, m_SizeY, 1.0f));
		m_Texture.bind();
		m_Vao.draw(mvMatrix);
	}

	@Override
	public void update(double deltaTime) {
		
	}

	@Override
	public void keyPressEvent(int key) {
	}

	@Override
	public void keyReleaseEvent(int key) {
	}

	@Override
	public void mousePressEvent(int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleaseEvent(int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCollision(float x, float y) {
		return false;
	}
}
