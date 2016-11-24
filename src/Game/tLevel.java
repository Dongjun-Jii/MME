package Game;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

import Game.Object.Bullet;
import Game.Object.Square;


public class tLevel implements Level{
	private List<GameObject> objectList;
	private Square m_Piece;
	private double m_AcTime = 0;
	private final double m_Interval = 0.03;
	
	public tLevel() {
		objectList = new ArrayList<GameObject>();
		m_Piece = new Square();
		this.createObject(m_Piece);
	}
	
	public void draw() {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.draw();
			}
		}
	}
	
	public void update(double deltaTime) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.update(deltaTime);
				
			}
		}
		
		for(int i = 0; i < objectList.size(); i++) {
			GameObject obj = objectList.get(i);
			if(obj instanceof Bullet) {
				Bullet b = (Bullet)obj;
				if(m_Piece.isCollision(b.getX(), b.getY()))
					GameInfo.gameEnd();
			}
		}
		
		for(int i = 0; i < objectList.size(); i++) {
			GameObject obj = objectList.get(i);
			if(obj instanceof Bullet) {
				if(((Bullet)obj).outOfRoom())
					objectList.remove(i);
			}
		}
		
		m_AcTime += deltaTime;
		if(m_AcTime >= m_Interval) {
			createObject(new Bullet());
			m_AcTime -= m_Interval;
		}
	}
	
	public void createObject(GameObject obj) {
		objectList.add(obj);
	}
	
	@Override
	public void keyPressEvent(int key) {
		if(key == GLFW_KEY_Z) {
			GameState.sound.play("lm");
		}else if(key == GLFW_KEY_X) {
			GameState.sound.pause("lm");
		}else if(key == GLFW_KEY_C) {
			GameState.sound.stop("lm");
		}
		
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.keyPressEvent(key);
			}
		}
	}

	@Override
	public void keyReleaseEvent(int key) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.keyReleaseEvent(key);
			}
		}
	}

	@Override
	public void mousePressEvent(int button) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				double x = GameState.mouseInput.getCursorX();
				double y = GameState.mouseInput.getCursorY();
				if(obj.isCollision((float) x, (float) y))
					obj.mousePressEvent(button);
			}
		}
		
	}

	@Override
	public void mouseReleaseEvent(int button) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				double x = GameState.mouseInput.getCursorX();
				double y = GameState.mouseInput.getCursorY();
				if( obj.isCollision((float) x, (float) y))
					obj.mouseReleaseEvent(button);
			}
		}
	}
}
