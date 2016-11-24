package Game.Object;

import java.util.Random;

import Game.GameObject;
import Graphics.Texture;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class Bullet implements GameObject{
	private Texture m_Texture;
	
	private float m_PosX = 960f;
	private float m_PosY = 540f;
	private float m_Depth = 0.0f;
	
	private float m_Angle = 0.0f;
	
	private float m_SizeX = 1.0f;
	private float m_SizeY = 1.0f;
	
	private float m_Direction;
	private float m_Speed = 100.0f;
	
	private VertexArray m_Vao;
	
	public Bullet() {
		m_Texture = new Texture("./res/Bullet.png");
		m_Vao = new VertexArray(m_Texture.getWidth(), m_Texture.getHeight(), 0.0f, 1.0f, 0.0f, 1.0f);
		m_Direction = (float) (new Random().nextDouble() * Math.PI * 2);
	}
	
	@Override
	public void draw() {
		Matrix4f mvMatrix = Matrix4f.translate(m_PosX, m_PosY, m_Depth).multiply(Matrix4f.rotate(m_Angle, 0.0f, 0.0f, 1.0f)).multiply(Matrix4f.scale(m_SizeX, m_SizeY, 1.0f));
		m_Texture.bind();
		m_Vao.draw(mvMatrix);
	}

	@Override
	public void update(double deltaTime) {
		m_PosX += Math.sin(m_Direction) * m_Speed * deltaTime;
		m_PosY += Math.cos(m_Direction) * m_Speed * deltaTime;
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

	@Override
	public boolean isCollision(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean outOfRoom() {
		boolean result = false;
		if(m_PosX < 0 || 1920 < m_PosX)
			result = true;
		if(m_PosY < 0 || 1080 < m_PosY)
			result = true;
		return result;
	}
	
	public float getX() {return m_PosX;}
	
	public float getY() {return m_PosY;}

}
