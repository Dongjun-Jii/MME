package Game.Level;

import java.util.*;

import Game.GameInfo;
import Game.Object.*;
import Graphics.*;
import Maths.Matrix4f;

public class GameStage implements Level{
	private VertexArray m_Background;
	private Texture m_BackgroundT;
	private Matrix4f m_bgMatrix;
	
	private int m_Stage;
	private Star m_LeftStar;
	private Star m_RightStar;
	private Wall[] m_Walls;
	private List<Bullet> m_Bullets = new ArrayList<Bullet>();
	
	private Random rand = new Random();
	private double m_AcTime = 0;
	
	public GameStage() {
		m_BackgroundT = TextureManager.getTexture(GameInfo.BG_STAGE_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 1);
		
		m_LeftStar = new Star(GameInfo.STAR_L);
		m_LeftStar.setMoveRange(GameInfo.GAME_AREA_L, GameInfo.GAME_AREA_M, GameInfo.GAME_AREA_T, GameInfo.GAME_AREA_B);
		m_LeftStar.setPosition((GameInfo.GAME_AREA_L + GameInfo.GAME_AREA_M) / 2, (GameInfo.GAME_AREA_T + GameInfo.GAME_AREA_B) / 2);
		
		m_RightStar = new Star(GameInfo.STAR_R);
		m_RightStar.setMoveRange(GameInfo.GAME_AREA_M, GameInfo.GAME_AREA_R, GameInfo.GAME_AREA_T, GameInfo.GAME_AREA_B);
		m_RightStar.setPosition((GameInfo.GAME_AREA_M + GameInfo.GAME_AREA_R) / 2, (GameInfo.GAME_AREA_T + GameInfo.GAME_AREA_B) / 2);
	}
	
	@Override
	public void draw() {
		m_BackgroundT.bind();
		m_Background.draw(m_bgMatrix);
		
		m_LeftStar.draw();
		m_RightStar.draw();
		
		for(Bullet tbullet : m_Bullets) {
			tbullet.draw();
		}
	}

	@Override
	public void update(double deltaTime) {
		m_LeftStar.update(deltaTime);
		m_RightStar.update(deltaTime);
		
		if(m_AcTime > 0.1) {
			Bullet t = new Bullet(GameInfo.BULLET_PATH,(float) ((GameInfo.GAME_AREA_M + GameInfo.GAME_AREA_R) / 2),(float) ((GameInfo.GAME_AREA_T + (GameInfo.GAME_AREA_T + GameInfo.GAME_AREA_B) / 2) / 2), rand.nextFloat() * 360);
			t.setMoveRange(GameInfo.GAME_AREA_M, GameInfo.GAME_AREA_R, GameInfo.GAME_AREA_T, GameInfo.GAME_AREA_B);
			m_Bullets.add(t);
			m_AcTime -= 0.1;
		}
		
		for(int i = 0; i < m_Bullets.size(); i++) {
			Bullet tbullet = m_Bullets.get(i);
			tbullet.update(deltaTime);
			if(tbullet.shouldDelete())
				m_Bullets.remove(i);
		}
		
		m_AcTime += deltaTime;
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
