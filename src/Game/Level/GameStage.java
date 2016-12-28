package Game.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.lwjgl.glfw.GLFW.*;
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
	private GameObject[][] m_Walls;
	private List<Bullet> m_Bullets = new ArrayList<Bullet>();
	private double m_regenTime = 0.1; //총알 연사속도
	private int m_BulletColor = 0;
	private int bulletdir = 0;
	
	private float m_StartPosX = 0;
	private float m_StartPosY = 0;
	
	private Random rand = new Random();
	private double m_AcTime = 0;
	
	public GameStage(int level) {
		m_BackgroundT = TextureManager.getTexture(GameInfo.BG_STAGE_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 1);
		
		m_LeftStar = new Star(GameInfo.STAR_L);
		m_LeftStar.setMoveRange(GameInfo.GAME_AREA_L, GameInfo.GAME_AREA_ML, GameInfo.GAME_AREA_T, GameInfo.GAME_AREA_B);
		m_LeftStar.setPosition((GameInfo.GAME_AREA_L + GameInfo.GAME_AREA_ML) / 2, (GameInfo.GAME_AREA_T + GameInfo.GAME_AREA_B) / 2 + 250);
		
		m_RightStar = new Star(GameInfo.STAR_R);
		m_RightStar.setMoveRange(GameInfo.GAME_AREA_MR, GameInfo.GAME_AREA_R, GameInfo.GAME_AREA_T, GameInfo.GAME_AREA_B);
		m_RightStar.setPosition((GameInfo.GAME_AREA_MR + GameInfo.GAME_AREA_R) / 2, (GameInfo.GAME_AREA_T + GameInfo.GAME_AREA_B) / 2 + 250);
		
		switch (level){
			case 1 : m_regenTime = 0.5; m_BulletColor = 1; break;
			case 2 : m_regenTime = 0.08; break;
			case 3 : m_regenTime = 0.5; m_BulletColor = 0; break;
			case 4 : m_regenTime = 0.04; break;
			case 5 : m_regenTime = 0.5; m_BulletColor = 2;break;
			case 6 : m_regenTime = 0.01; break;
		}
		m_Stage = level;
		
		m_Walls = new GameObject[GameInfo.MAZE_HEIGHT][];
		Scanner in = null;
		try {
			in = new Scanner(new File("./res/stage/Stage" + m_Stage + ".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < GameInfo.MAZE_HEIGHT; i++)
			m_Walls[i] = new GameObject[GameInfo.MAZE_WIDTH];
		for(int i = 0; in.hasNextLine(); i++) {
			char[] str = in.nextLine().toCharArray();
			for(int j = 0; j < GameInfo.MAZE_WIDTH; j++) {
				switch(str[j]) {
				case '.':
					m_Walls[i][j] = null;
					break;
				case '#':
					m_Walls[i][j] = new Wall(GameInfo.WALL_PATH, j * 64 + 32 + GameInfo.GAME_AREA_L, i * 64 + 32 + GameInfo.GAME_AREA_T);
					break;
				case '@':
					m_Walls[i][j] = new Portal(j * 64 + 32 + GameInfo.GAME_AREA_L, i * 64 + 32 + GameInfo.GAME_AREA_T);
					break;
				case 'S':
					m_Walls[i][j] = null;
					m_StartPosX = j * 64 + 32 + GameInfo.GAME_AREA_L;
					m_StartPosY = i * 64 + 32 + GameInfo.GAME_AREA_T;
					m_LeftStar.setPosition(m_StartPosX, m_StartPosY);
					break;
				}
			}
		}
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
		
		for(int i = 0; i < GameInfo.MAZE_HEIGHT; i++) {
			for(int j = 0; j < GameInfo.MAZE_WIDTH; j++) {
				if(m_Walls[i][j] != null) {
					m_Walls[i][j].draw();
				}
			}
		}
		
	}

	@Override
	public void update(double deltaTime) {
		m_LeftStar.update(deltaTime);
		m_RightStar.update(deltaTime);
		
		if(m_AcTime > m_regenTime && m_Stage % 2 == 0) {
			m_BulletColor = (m_BulletColor + 1) % 3;
			
			Bullet t = new Bullet(GameInfo.BULLET_PATH[m_BulletColor], m_BulletColor, (float)((GameInfo.GAME_AREA_MR + GameInfo.GAME_AREA_R) / 2),(float) (((GameInfo.GAME_AREA_T + GameInfo.GAME_AREA_B) / 2)), rand.nextFloat() * 360);
			t.setMoveRange(GameInfo.GAME_AREA_MR, GameInfo.GAME_AREA_R, GameInfo.GAME_AREA_T, GameInfo.GAME_AREA_B);
			m_Bullets.add(t);
			m_AcTime -= m_regenTime;
		}else if(m_AcTime > m_regenTime){
			for(int i = bulletdir; i < 360 + bulletdir; i += 20) {
				Bullet t = new Bullet(GameInfo.BULLET_PATH[m_BulletColor], m_BulletColor, (float)((GameInfo.GAME_AREA_MR + GameInfo.GAME_AREA_R) / 2),(float) (((GameInfo.GAME_AREA_T + GameInfo.GAME_AREA_B) / 2)), i);
				t.setMoveRange(GameInfo.GAME_AREA_MR, GameInfo.GAME_AREA_R, GameInfo.GAME_AREA_T, GameInfo.GAME_AREA_B);
				m_Bullets.add(t);
			}
			bulletdir = (bulletdir + 3) % 30;
			m_AcTime -= m_regenTime;
		}
		
		for(int i = 0; i < m_Bullets.size(); i++) {
			Bullet tbullet = m_Bullets.get(i);
			tbullet.update(deltaTime);
			if(tbullet.shouldDelete()){
				m_Bullets.remove(i);
			}else {
				float bx = tbullet.getX();
				float by = tbullet.getY();
				float x = m_RightStar.getX();
				float y = m_RightStar.getY();
				
				if(distance(bx, x, by, y) < 16) {
					GameInfo.gameOver();
				}
			}
		}
		
		for(int i = 0; i < GameInfo.MAZE_HEIGHT; i++) {
			for(int j = 0; j < GameInfo.MAZE_WIDTH; j++) {
				if(m_Walls[i][j] == null)
					continue;
				
				float x = m_LeftStar.getX();
				float y = m_LeftStar.getY();
				float wx = j * 64 + 32 + GameInfo.GAME_AREA_L;
				float wy = i * 64 + 32 + GameInfo.GAME_AREA_T;
				GameObject obj = m_Walls[i][j];
				
				if(!collision(x, y, 16, wx - 32, wx + 32, wy - 32, wy + 32))
					continue;
				
				if(obj instanceof Portal) {
					GameInfo.gameClear();
				}
				
				if(!(obj instanceof Wall))
					continue;
				
				m_LeftStar.setPosition(m_LeftStar.getpreX(), m_LeftStar.getpreY());
			}
		}
		
//		if(m_Walls[iy][ix] != null) {
//			GameObject obj = m_Walls[iy][ix];
//			if(obj instanceof Portal) {
//				GameInfo.gameClear();
//			}else if(obj instanceof Wall) {
//				float px = m_LeftStar.getpreX() - GameInfo.GAME_AREA_L;
//				float py = m_LeftStar.getpreY() - GameInfo.GAME_AREA_T;
//				int ipx = (int) (px / 64);
//				int ipy = (int) (py / 64);
//				if(ipx != ix) {
//					x = (float) ((ix + ipx + 1) * 64 / 2.0 + GameInfo.GAME_AREA_L);
//				}
//				if(ipy != iy) {
//					y = (float) ((iy + ipy + 1) * 64 / 2.0 + GameInfo.GAME_AREA_T);
//				}
//				
//				m_LeftStar.setPosition(x, y);
//			}
//		}
		
		m_AcTime += deltaTime;
	}
	
	private boolean collision(float cx, float cy, float r, float x1, float x2, float y1, float y2) {
		boolean result = false;
		if(x1 - r < cx && cx < x2 + r && y1 - r < cy && cy < y2 + r)
			result = true;
		
		if(cx < x1 && cy < y1 && distance(x1, cx, y1, cy) > r)
			result = false;
		
		if(cx < x1 && y2 < cy && distance(x1, cx, y2, cy) > r)
			result = false;
		
		if(x2 < cx && cy < y1 && distance(x2, cx, y1, cy) > r)
			result = false;
		
		if(x2 < cx && y2 < cy && distance(x2, cx, y2, cy) > r)
			result = false;
		
		return result;
	}
	
	private float distance(float x1, float x2, float y1, float y2) {
		return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	@Override
	public void keyPressEvent(int key) {
		if(key == GLFW_KEY_P){
			GameInfo.gamePause();
		}
		
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
