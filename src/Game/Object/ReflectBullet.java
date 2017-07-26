package Game.Object;

import Game.GameInfo;
import Graphics.Texture;
import Graphics.TextureManager;
import Graphics.VertexArray;
import Maths.Matrix4f;

public class ReflectBullet extends Bullet implements GameObject{
	private Texture m_Texture;
	private VertexArray m_Vao;
	private Matrix4f m_MVMatrix;
	
	private float m_PosX;
	private float m_PosY;
	
	private float m_Speed = 600;
	private float m_Direction;
	
	private float m_LeftLimit = GameInfo.GAME_AREA_MR;
	private float m_RightLimit = GameInfo.GAME_AREA_R;
	private float m_TopLimit = GameInfo.GAME_AREA_T;
	private float m_BottomLimit = GameInfo.GAME_AREA_B;
	
	private int m_RemainReflectN;
	
	private boolean m_shouleDelete = false;
	
	public ReflectBullet (int reflectn, int color, float x, float y, float dir) {
		m_Texture = TextureManager.getTexture(GameInfo.BULLET_PATH[color]);
		m_Vao = new VertexArray(m_Texture.getWidth(), m_Texture.getHeight(), 0, 1, 0, 1);
		m_PosX = x;
		m_PosY = y;
		m_Direction = dir;
		m_RemainReflectN = reflectn;
		
		switch (color){
			case 0: m_Speed = 500; break;
			case 1: m_Speed = 400; break;
			case 2: m_Speed = 600; break;
			case 3: m_Speed = 300; break;
		}
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
		float deltaX = (float) (Math.cos(m_Direction) * m_Speed * deltaTime);
		float deltaY = (float) (Math.sin(m_Direction) * m_Speed * deltaTime);
		
		m_PosX = Math.max(m_LeftLimit, Math.min(m_RightLimit, m_PosX + deltaX));
		m_PosY = Math.max(m_TopLimit, Math.min(m_BottomLimit, m_PosY + deltaY));
		
		if(m_PosX == m_LeftLimit || m_PosX == m_RightLimit || m_PosY == m_TopLimit || m_PosY == m_BottomLimit) {
			if(m_RemainReflectN == 0) {
				m_shouleDelete = true;
			}
			else {
				double px = Math.cos(m_Direction);
				double py = Math.sin(m_Direction);
				double nx = 0;
				double ny = 0;
				
				if(m_PosX == m_LeftLimit) {
					nx = 1;
				} else if (m_PosX == m_RightLimit) {
					nx = -1;
				}
				if (m_PosY == m_TopLimit) {
					ny = 1;
				} else if (m_PosY == m_BottomLimit) {
					ny = -1;
				}
				
				if(nx != 0 && ny != 0) {
					double len = Math.sqrt(nx * nx + ny * ny);
					nx /= len; ny /= len;
				}
				
				double rx = px + 2 * nx * (-px * nx + -py * ny);
				double ry = py + 2 * ny * (-px * nx + -py * ny);
				double len = Math.sqrt(rx * rx + ry * ry);
				rx /= len; ry /= len;
				
				float result = (float) Math.acos(rx);
				
				if(ry < 0) result = (float) (2 * Math.PI - result);
				
				m_Direction = result;
				m_RemainReflectN -= 1;
			}
		}
			
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
