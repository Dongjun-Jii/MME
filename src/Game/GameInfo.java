package Game;

import static org.lwjgl.glfw.GLFW.*;

import Game.Level.*;

public final class GameInfo {
	
	public static void gameInit() {
		GameState.curLevel = new Title();
	}

	public static void gameEnd() {
		glfwSetWindowShouldClose(GameState.window, true);
	}
	
	public static final String TITLE_BG_PATH = "./res/bg/Title.png";
}
