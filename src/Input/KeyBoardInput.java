package Input;

import org.lwjgl.glfw.GLFWKeyCallback;

import Game.GameState;

import static org.lwjgl.glfw.GLFW.*;

public class KeyBoardInput extends GLFWKeyCallback{
	private boolean keys[] = new boolean[65536];
	
	public KeyBoardInput() {
		for(int i = 0; i < 65536; i++)
			keys[i] = false;
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		/* Esc -> Game End */
		if(key == GLFW_KEY_ESCAPE && action == GLFW_PRESS){
			glfwSetWindowShouldClose(window, true);
		}
		
		/* Call Event */
		if(action == GLFW_PRESS) 
			GameState.curLevel.keyPressEvent(key);
		else if(action == GLFW_RELEASE) 
			GameState.curLevel.keyReleaseEvent(key);
		
		/* Set Keys */
		keys[key] = (action != GLFW_RELEASE) ? true : false;
	}
	
	public boolean isKeyPressed(int key) {
		return keys[key];
	}
}
