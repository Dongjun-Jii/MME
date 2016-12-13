package Game;

import static org.lwjgl.glfw.GLFW.*;

public final class GameInfo {
	
	public final static String TITLE_BG_PATH = "./res/bg/Title.png";

	public static void gameInit() {
		GameState.curLevel = new Title();
	}

	public static void gameEnd() {
		glfwSetWindowShouldClose(GameState.window, true);
	}
	
}
