package Game.Object;

public interface GameObject {
	public void draw();
	
	public void update(double deltaTime);
	
	public void keyPressEvent(int key);

	public void keyReleaseEvent(int key);
	
	public void mousePressEvent(int button);
	
	public void mouseReleaseEvent(int button);
}