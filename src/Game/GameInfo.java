package Game;

import static org.lwjgl.glfw.GLFW.*;

import Game.Level.GameStage;
import Game.Level.StageSelect;
import Game.Level.Title;

public final class GameInfo {
	
	public final static String BG_RED_PATH = "./res/bg/Red.png";
	public final static String BG_GREEN_PATH = "./res/bg/Green.png";
	public final static String BG_BLUE_PATH = "./res/bg/Blue.png";
	public final static String BG_STAGE_PATH = "./res/bg/GameStage.png";
	
	public final static String BUTTON_MENU_PATH[] = {"./res/button/Menu.png", "./res/button/Menu-sS.png", "./res/button/Menu-sP.png", "./res/button/Menu-sO.png"};
	public final static String BUTTON_QUIT_PATH[] = {"./res/button/Quit.png", "./res/button/Quit-s.png", "./res/button/Quit-d.png"};
	public final static String BUTTON_STAGE_PATH[] = {"./res/button/Stage1.png", "./res/button/Stage1-s.png",
			"./res/button/Stage2.png", "./res/button/Stage2-s.png",
			"./res/button/Stage3.png", "./res/button/Stage3-s.png"};
	public final static String BUTTON_RESTART_PATH[] = {"./res/button/Restart.png", "./res/button/Restart-sP.png", "./res/button/Restart-sO.png"};
	public final static String BUTTON_CONTINUE_PATH[] = {"./res/button/Continue.png", "./res/button/Continue-s.png"};
	public final static String BUTTON_START_PATH[] = {"./res/button/Start.png", "./res/button/Start-s.png"};
	
	public final static String STAR_L = "./res/star/MME-Mr.png";
	public final static String STAR_R = "./res/star/MME-Mrs.png";
	
	public final static String BULLET_PATH = "./res/bullet/Bullet.png";
	
	public final static int GAME_AREA_L = 100;
	public final static int GAME_AREA_M = 960;
	public final static int GAME_AREA_R = 1820;
	public final static int GAME_AREA_T = 100;
	public final static int GAME_AREA_B = 980;
	
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
	
	public static void gotoGame(int stage) {
		GameState.curLevel = new GameStage();
	}
	
	public static void gamePause() {
		
	}
	
	public static void gameOver() {
		
	}
	
}
