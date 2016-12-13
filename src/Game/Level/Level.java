package Game.Level;

public interface Level {
	public void draw();
	
	public void update(double deltaTime);
	
	public void keyPressEvent(int key);

	public void keyReleaseEvent(int key);
	
	public void mousePressEvent(int button);
	
	public void mouseReleaseEvent(int button);
}
