package Input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import Game.GameState;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;

public class MouseButtonInput extends GLFWMouseButtonCallback{
	private boolean buttons[] = new boolean[8];
	private long window;
	
	public MouseButtonInput(long window) {
		this.window = window;
	}
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		buttons[button] = (action != GLFW_RELEASE) ? true : false;
		if(action == GLFW_PRESS)
			GameState.curLevel.mousePressEvent(button);
		else if(action == GLFW_RELEASE)
			GameState.curLevel.mouseReleaseEvent(button);
		
		
	}
	
	public boolean isMousePressed(int button) {
		return buttons[button];
	}
	
	public double getCursorX() {
		DoubleBuffer xpos = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer ypos = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, xpos, ypos);
		return xpos.get(0);
	}
	
	public double getCursorY() {
		DoubleBuffer xpos = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer ypos = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, xpos, ypos);
		return ypos.get(0);
	}
}