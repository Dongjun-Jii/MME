package Game;

import java.util.ArrayList;
import java.util.List;

import Game.Object.GameObject;

public class tLevel implements Level{
	private List<GameObject> objectList;
	
	public tLevel() {
		objectList = new ArrayList<GameObject>();
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
	}
	
	public void createObject(GameObject obj) {
		objectList.add(obj);
	}
	
	@Override
	public void keyPressEvent(int key) {
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
				obj.mousePressEvent(button);
			}
		}
		
	}

	@Override
	public void mouseReleaseEvent(int button) {
		if(!objectList.isEmpty()) {
			for(GameObject obj : objectList) {
				obj.mouseReleaseEvent(button);
			}
		}
	}
}