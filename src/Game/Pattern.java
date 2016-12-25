package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Game.Object.Bullet;
import Game.Object.ReflectBullet;

public class Pattern {
	private int m_Stage;
	private double m_ACTime[] = new double[]{0,0,0};
	private double m_Interval[];
	private int m_Color[];
	private float m_Rotation = 0;
	private Random rand = new Random();
	
	private int m_Mode = 0;
	
	public Pattern(int stage) {
		m_Stage = stage;
		
		switch(m_Stage) {
		case 1:
			m_Interval = new double[]{1};
			m_Color = new int[]{Bullet.BULLET_BLUE};
			break;
		case 2:
			m_Interval = new double[]{1};
			m_Color = new int[]{Bullet.BULLET_GREEN};
			break;
		case 3:
			m_Interval = new double[]{0.5};
			m_Color = new int[]{Bullet.BULLET_GREEN};
			break;
		case 4:
			m_Interval = new double[]{1.5};
			m_Color = new int[]{Bullet.BULLET_YELLOW};
			break;
		case 5:
			m_Interval = new double[]{0.5, 1.5};
			m_Color = new int[]{Bullet.BULLET_GREEN, Bullet.BULLET_YELLOW};
			break;
		case 6:
			m_Interval = new double[]{2};
			m_Color = new int[]{Bullet.BULLET_RED};
			break;
		case 7:
			m_Interval = new double[]{5};
			m_ACTime = new double[]{3};
			m_Color = new int[]{Bullet.BULLET_YELLOW};
			break;
		case 8:
			m_Interval = new double[]{0.5};
			m_Color = new int[]{Bullet.BULLET_BLUE};
			break;
		case 9:
			m_Interval = new double[]{0.45, 1};
			m_Color = new int[]{Bullet.BULLET_YELLOW, Bullet.BULLET_BLUE};
			break;
		case 10: 
			m_Interval = new double[]{0.45, 0.75, 0.5, 1.5, 7};
			m_ACTime = new double[]{0, 0, 0, 0, 0};
			m_Color = new int[]{Bullet.BULLET_YELLOW, Bullet.BULLET_RED, Bullet.BULLET_BLUE, Bullet.BULLET_RED, Bullet.BULLET_GREEN};
			break;
		}
	}
	
	public void update(double deltaTime, List<Bullet> bullets, float x, float y) {
		float cx = (GameInfo.GAME_AREA_MR + GameInfo.GAME_AREA_R) / 2;
		float cy = (GameInfo.GAME_AREA_T + GameInfo.GAME_AREA_B) / 2;
			
		switch(m_Stage) {
		case 1:
			while(m_Interval[0] < m_ACTime[0]) {
				bullets.addAll(polygon(1, m_Color[0], cx, cy, x, y, 0));
				m_ACTime[0] -= m_Interval[0];
				break;
			}
			break;
		case 2:
			while(m_Interval[0] < m_ACTime[0]) {
				bullets.addAll(polygon(4, m_Color[0], cx, cy, x, y, 75));
				m_ACTime[0] -= m_Interval[0];
				break;
			}
			break;
		case 3:
			while(m_Interval[0] < m_ACTime[0]) {
				bullets.addAll(slug(4, m_Color[0], cx, cy, m_Rotation));
				m_Rotation += 5;
				if(m_Rotation >= 90) m_Rotation -= 90;
				m_ACTime[0] -= m_Interval[0];
				break;
			}
			break;
		case 4:
			while(m_Interval[0] < m_ACTime[0]) {
				bullets.addAll(slug(20, m_Color[0], cx, cy, (float) (rand.nextFloat() * Math.PI)));
				m_ACTime[0] -= m_Interval[0];
				break;
			}
			break;
		case 5:
			while(m_Interval[0] < m_ACTime[0]) {
				bullets.addAll(slug(4, m_Color[0], cx, cy, m_Rotation));
				m_Rotation += 5;
				if(m_Rotation >= 90) m_Rotation -= 90;
				m_ACTime[0] -= m_Interval[0];
			}
			while(m_Interval[1] < m_ACTime[1]) {
				bullets.addAll(slug(20, m_Color[1], cx, cy, (float) (rand.nextFloat() * Math.PI)));
				m_ACTime[1] -= m_Interval[1];
			}
			break;
		case 6:
			while(m_Interval[0] < m_ACTime[0]) {
				bullets.addAll(reflectPolygon(3, 6, m_Color[0], cx, cy, x, y, 75));
				m_ACTime[0] -= m_Interval[0];
				break;
			}
			break;
		case 7:
			while(m_Interval[0] < m_ACTime[0]) {
				bullets.addAll(reflectSlug(3, 18, m_Color[0], cx, cy, 0));
				m_ACTime[0] -= m_Interval[0];
				break;
			}
			break;
		case 8:
			while(m_Interval[0] < m_ACTime[0]) {
				bullets.addAll(reflectSlug(1, 4, m_Color[0], cx, cy, m_Rotation));
				m_Rotation += 5;
				if(m_Rotation >= 90) m_Rotation -= 90;
				m_ACTime[0] -= m_Interval[0];
				break;
			}
			break;
		case 9:
			while(m_Interval[0] < m_ACTime[0]) {
				float subX = GameInfo.GAME_AREA_R - GameInfo.GAME_AREA_MR;
				float subY = GameInfo.GAME_AREA_B - GameInfo.GAME_AREA_T;
				bullets.add(shootVertical(m_Color[0], rand.nextFloat() * subX + GameInfo.GAME_AREA_MR, 1));
				bullets.add(shootVertical(m_Color[0], rand.nextFloat() * subX + GameInfo.GAME_AREA_MR, -1));
				bullets.add(shootHorizontal(m_Color[0], rand.nextFloat() * subY + GameInfo.GAME_AREA_T, 1));
				bullets.add(shootHorizontal(m_Color[0], rand.nextFloat() * subY + GameInfo.GAME_AREA_T, -1));
				m_ACTime[0] -= m_Interval[0];
			}
			while(m_Interval[1] < m_ACTime[1]) {
				bullets.addAll(polygon(5, m_Color[1], x, y, x, y, 300));
				m_ACTime[1] -= m_Interval[1];
			}
			break;
		case 10:
			if(m_Mode == 0) {
				while(m_Interval[0] < m_ACTime[0]) {
					float subX = GameInfo.GAME_AREA_R - GameInfo.GAME_AREA_MR;
					float subY = GameInfo.GAME_AREA_B - GameInfo.GAME_AREA_T;
					bullets.add(shootVertical(m_Color[0], rand.nextFloat() * subX + GameInfo.GAME_AREA_MR, 1));
					bullets.add(shootVertical(m_Color[0], rand.nextFloat() * subX + GameInfo.GAME_AREA_MR, -1));
					bullets.add(shootHorizontal(m_Color[0], rand.nextFloat() * subY + GameInfo.GAME_AREA_T, 1));
					bullets.add(shootHorizontal(m_Color[0], rand.nextFloat() * subY + GameInfo.GAME_AREA_T, -1));
					m_ACTime[0] -= m_Interval[0];
				}
				while(m_Interval[1] < m_ACTime[1]) {
					bullets.addAll(reflectPolygon(1, 8, m_Color[1], cx, cy, x, y, 75));
					m_ACTime[1] -= m_Interval[1];
					break;
				}
			}else if(m_Mode == 2) {
				while(m_Interval[2] < m_ACTime[2]) {
					bullets.addAll(reflectSlug(1, 4, m_Color[2], cx, cy, m_Rotation));
					m_Rotation += 5;
					if(m_Rotation >= 90) m_Rotation -= 90;
					m_ACTime[2] -= m_Interval[2];
				}
				while(m_Interval[3] < m_ACTime[3]) {
					bullets.addAll(slug(20, m_Color[3], cx, cy, (float) (rand.nextFloat() * Math.PI)));
					m_ACTime[3] -= m_Interval[3];
				}
			}
			while(m_Interval[4] < m_ACTime[4]) {
				if(m_Mode == 0) {
					m_ACTime[2] = 0;
					m_ACTime[3] = 0;
					m_Mode = 2;
				}
				else if(m_Mode == 2) {
					bullets.addAll(reflectSlug(3, 30, m_Color[4], cx, cy, 0));
					m_Mode = 4;
				}
				else {
					m_ACTime[0] = 0;
					m_ACTime[1] = 0;
					m_Mode = 0;
				}
				m_ACTime[4] -= m_Interval[4];
			}
			break;
		}
		
		for(int i = 0; i < m_ACTime.length; i++) {
			m_ACTime[i] += deltaTime;
		}
	}
	
	private List<Bullet> reflectSlug(int r, int n, int color, float cx, float cy, float rotation) {
		List<Bullet> bullets = new ArrayList<Bullet>();
		
		for(int i = 0; i < n; i++) {
			float dir = (float) (Math.toRadians(i * 360.0 / n) + Math.toRadians(rotation));
			Bullet tbullet = new ReflectBullet(r, color, cx, cy , dir);
			bullets.add(tbullet);
		}
		
		return bullets;
	}
	
	private List<Bullet> slug(int n, int color, float cx, float cy, float rotation) {
		List<Bullet> bullets = new ArrayList<Bullet>();
		
		for(int i = 0; i < n; i++) {
			float dir = (float) (Math.toRadians(i * 360.0 / n) + Math.toRadians(rotation));
			Bullet tbullet = new Bullet(color, cx, cy , dir);
			bullets.add(tbullet);
		}
		
		return bullets;
	}
	
	private List<Bullet> reflectPolygon(int r, int n, int color, float cx, float cy, float tx, float ty, int len) {
		List<Bullet> bullets = new ArrayList<Bullet>();
		
		for(int i = 0; i < n; i++) {
			double dir = Math.toRadians(i * 360.0 / n);
			float x = (float) (len * Math.sin(dir) + cx);
			float y = (float) (len * Math.cos(dir) + cy );
			Bullet tbullet = new ReflectBullet(r, color, x, y, getDirection(x, tx, y, ty));
			bullets.add(tbullet);
		}
		
		return bullets;
	}
	
	private List<Bullet> polygon(int n, int color, float cx, float cy, float tx, float ty, int len) {
		List<Bullet> bullets = new ArrayList<Bullet>();
		
		for(int i = 0; i < n; i++) {
			double dir = Math.toRadians(i * 360.0 / n);
			float x = (float) (len * Math.sin(dir) + cx);
			float y = (float) (len * Math.cos(dir) + cy );
			Bullet tbullet = new Bullet(color, x, y, getDirection(x, tx, y, ty));
			bullets.add(tbullet);
		}
		
		return bullets;
	}
	
	private Bullet shootVertical(int color, float x, int dir) {
		float y = (dir > 0) ? GameInfo.GAME_AREA_T + 1 : GameInfo.GAME_AREA_B - 1;
		Bullet bullet = new Bullet(color, x, y, (float) Math.toRadians((dir > 0) ? 90 : 270));
		return bullet;
	}
	
	private Bullet shootHorizontal(int color, float y, int dir) {
		float x = (dir > 0) ? GameInfo.GAME_AREA_MR + 1 : GameInfo.GAME_AREA_R - 1;
		Bullet bullet = new Bullet(color, x, y, (float) Math.toRadians((dir > 0) ? 0 : 180));
		return bullet;
	}
	
	private float getDirection(float x1, float x2, float y1, float y2) {
		float result = 0;
		double deltaX = x2 - x1;
		double deltaY = y2 - y1;
		
		double len = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		deltaX /= len;
		deltaY /= len;
		
		result = (float) Math.acos(deltaX);
		
		if(deltaY < 0) result = (float) (2 * Math.PI - result);
		
		return result;
	}
}
