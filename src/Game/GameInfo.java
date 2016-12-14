package Game;

import static org.lwjgl.glfw.GLFW.*;

import Game.Level.StageSelect;
import Game.Level.Title;

public final class GameInfo {
	
	public final static String BG_RED_PATH = "./res/bg/Red.png";
	public final static String BG_GREEN_PATH = "./res/bg/Green.png";
	public final static String BG_BLUE_PATH = "./res/bg/Blue.png";
	
	public final static String BUTTON_MENU_PATH[] = {"./res/button/Menu.png", "./res/button/Menu-sS.png", "./res/button/Menu-sP.png", "./res/button/Menu-sO.png"};
	public final static String BUTTON_QUIT_PATH[] = {"./res/button/Continue.png", ".res/button/continue-s.png"};
	public final static String BUTTON_STAGE_PATH[] = {"./res/button/Stage1.png", "./res/button/Stage1-s.png",
			"./res/button/Stage2.png", "./res/button/Stage2-s.png",
			"./res/button/Stage3.png", "./res/button/Stage3-s.png"};
	public final static String BUTTON_RESTART_PATH[] = {"./res/button/Restart.png", "./res/button/Restart-sP.png", "./res/button/Restart-sO.png"};
	public final static String BUTTON_CONTINUE_PATH[] = {"./res/button/Continue.png", "./res/button/Continue-s.png"};
	public final static String BUTTON_START_PATH[] = {"./res/button/Start.png", "./res/button/Start-s.png"};
	
	public static void gameInit() {
		GameState.curLevel = new Title();
	}

	public static void gameEnd() {
		glfwSetWindowShouldClose(GameState.window, true);
	}
	
	public static void gotoStageSelect() {
		GameState.curLevel = new StageSelect();
	}
	
	public static void gotoTitle() {
		GameState.curLevel = new Title();
	}
	
}
