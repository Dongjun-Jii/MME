package Game.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.lwjgl.glfw.GLFW.*;
import Game.GameInfo;
import Game.Pattern;
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
	
	private Pattern m_Pattern;
	
	public GameStage(int level) {
		m_BackgroundT = TextureManager.getTexture(GameInfo.BG_STAGE_PATH);
		m_Background = new VertexArray(m_BackgroundT.getWidth(), 1080.0f, 0.0f, 1.0f, 0.0f, 1.0f);
		m_bgMatrix = Matrix4f.translate(960, 520, 1);
		
		m_LeftStar = new Star(GameInfo.STAR_L);
		m_LeftStar.setMoveRange(GameInfo.GAME_AREA_L, GameInfo.GAME_AREA_ML, GameInfo.GAME_AREA_T, GameInfo.GAME_AREA_B);
		
		m_RightStar = new Star(GameInfo.STAR_R);
		m_RightStar.setMoveRange(GameInfo.GAME_AREA_MR, GameInfo.GAME_AREA_R, GameInfo.GAME_AREA_T, GameInfo.GAME_AREA_B);
		m_RightStar.setPosition((GameInfo.GAME_AREA_MR + GameInfo.GAME_AREA_R) / 2, (GameInfo.GAME_AREA_T + GameInfo.GAME_AREA_B) / 2 + 300);
		
		m_Stage = level;
		m_Pattern = new Pattern(m_Stage);
		
		m_Walls = new GameObject[GameInfo.MAZE_HEIGHT][];
		for(int i = 0; i < GameInfo.MAZE_HEIGHT; i++)
			m_Walls[i] = new GameObject[GameInfo.MAZE_WIDTH];
		
		loadMaze();
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
					if(m_Stage == 10){
						GameInfo.Congratulate();
					} else {
						GameInfo.gameClear();
					}
				}
				if(!(obj instanceof Wall))
					continue;
				
				if(!collision(m_LeftStar.getpreX(), y, 16, wx - 32, wx + 32, wy - 32, wy + 32))
					m_LeftStar.setPosition(m_LeftStar.getpreX(), y);
				else if(!collision(x, m_LeftStar.getpreY(), 16, wx - 32, wx + 32, wy - 32, wy + 32))
					m_LeftStar.setPosition(x, m_LeftStar.getpreY());
				else
					m_LeftStar.setPosition(m_LeftStar.getpreX(), m_LeftStar.getpreY());
			}
		}
		
		m_Pattern.update(deltaTime, m_Bullets, m_RightStar.getX(), m_RightStar.getY());
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
	
	private float distance(float x1, float x2, float y1, float y2) {
		return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
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
	
	private void loadMaze() {
		Scanner in = null;
		try {
			if(m_Stage == 4){
				in = new Scanner(new File("./res/stage/Stage" + m_Stage +"-"+ ((int)(Math.random()*4+1)) + ".txt"));
			}
			else{
				in = new Scanner(new File("./res/stage/Stage" + m_Stage + ".txt"));
			}
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
						float startx = j * 64 + 32 + GameInfo.GAME_AREA_L;
						float starty = i * 64 + 32 + GameInfo.GAME_AREA_T;
						m_LeftStar.setPosition(startx, starty);
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
