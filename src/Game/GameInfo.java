package Game;

import static org.lwjgl.glfw.GLFW.*;

import Game.Level.Title;

public final class GameInfo {
	
	public final static String BG_BLUE_PATH = "./res/bg/Blue.png";

	public static void gameInit() {
		GameState.curLevel = new Title();
	}

	public static void gameEnd() {
		glfwSetWindowShouldClose(GameState.window, true);
	}
	
	public static void gotoMenu() {
		
	}
	
	public static void gotoStageSelect() {
		
	}
	
}
