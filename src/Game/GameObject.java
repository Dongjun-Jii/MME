package Game;

public interface GameObject {
	public void draw();
	
	public void update(double deltaTime);
	
	public void keyPressEvent(int key);

	public void keyReleaseEvent(int key);
	
	public void mousePressEvent(int button);
	
	public void mouseReleaseEvent(int button);
	
	public boolean isCollision(float x, float y);
}