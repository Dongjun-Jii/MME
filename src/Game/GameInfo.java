package Game;

import static org.lwjgl.glfw.GLFW.*;

import Game.Level.*;

public final class GameInfo {
	
	/*배경*/
	public final static String BG_RED_PATH = "./res/bg/Red.png";
	public final static String BG_GREEN_PATH = "./res/bg/Green.png";
	public final static String BG_BLUE_PATH = "./res/bg/Blue.png";
	public final static String BG_STAGE_PATH = "./res/bg/GameStage.png";
	public final static String BG_COVER_PATH = "./res/bg/Title.png";
	public final static String BG_CREDIT_PATH = "./res/bg/Credit.png";
	public final static String BG_CLEAR_PATH = "./res/bg/Clear.png";
	public final static String BG_QSS_PATH = "./res/bg/QSS.png";
	public final static String BG_CONGRATULATE_PATH = "./res/bg/Congratulate.png";
	
	/*버튼*/
	public final static String BUTTON_MENU_PATH[] = {"./res/button/Menu.png", "./res/button/Menu-sS.png", "./res/button/Menu-sP.png", "./res/button/Menu-sO.png"};
	public final static String BUTTON_QUIT_PATH[] = {"./res/button/Quit.png", "./res/button/Quit-s.png", "./res/button/Quit-d.png"};
	public final static String BUTTON_STAGE_PATH[] = {"./res/button/Stage1.png", "./res/button/Stage1-s.png",
			"./res/button/Stage2.png", "./res/button/Stage2-s.png",
			"./res/button/Stage3.png", "./res/button/Stage3-s.png",
			"./res/button/Stage4.png", "./res/button/Stage4-s.png",
			"./res/button/Stage5.png", "./res/button/Stage5-s.png",
			"./res/button/Stage6.png", "./res/button/Stage6-s.png",
			"./res/button/Stage7.png", "./res/button/Stage7-s.png",
			"./res/button/Stage8.png", "./res/button/Stage8-s.png",
			"./res/button/Stage9.png", "./res/button/Stage9-s.png",
			"./res/button/Stage10.png", "./res/button/Stage10-s.png"};
	public final static String BUTTON_RESTART_PATH[] = {"./res/button/Restart.png", "./res/button/Restart-sP.png", "./res/button/Restart-sO.png"};
	public final static String BUTTON_CONTINUE_PATH[] = {"./res/button/Continue.png", "./res/button/Continue-s.png"};
	public final static String BUTTON_NEXT_PATH[] = {"./res/button/Next.png","./res/button/Next-s.png"};
	public final static String BUTTON_START_PATH[] = {"./res/button/Start.png", "./res/button/Start-s.png"};
	public final static String BUTTON_DOWNARROW_PATH[] = {"./res/button/Down.png","./res/button/Down-s.png"};
	public final static String BUTTON_UPARROW_PATH[] = {"./res/button/Up.png","./res/button/Up-s.png"};
	public final static String BUTTON_CREDIT_PATH[] = {"./res/button/Credit.png", "./res/button/Credit-s.png"};
	
	/*캐릭터*/
	public final static String STAR_L = "./res/star/MME-Mr.png";
	public final static String STAR_R = "./res/star/MME-Mrs.png";
	
	/*총알*/
	public final static String BULLET_PATH[] = {"./res/bullet/MME-Bullet-Y.png", "./res/bullet/MME-Bullet-B.png","./res/bullet/MME-Bullet-R.png","./res/bullet/MME-Bullet-G.png"};
	
	public final static String WALL_PATH = "./res/wall/Wall.png";
	public final static String PORTAL_PATH = "./res/wall/Portal.png";
	
	public final static int GAME_AREA_L = 128;
	public final static int GAME_AREA_ML = 896;
	public final static int GAME_AREA_MR = 1043;
	public final static int GAME_AREA_R = 1780;
	public final static int GAME_AREA_T = 40;
	public final static int GAME_AREA_B = 980;
	
	public final static int MAZE_WIDTH = 12;
	public final static int MAZE_HEIGHT = 15;	
	
	public static void gameInit() {
		GameState.curLevel = new TeamQSS();
		GameState.sound.loadSound("bgm", "./res/sound/BGM.ogg");
		GameState.sound.setLoop("bgm", true);
		GameState.sound.play("bgm");		
	}

	public static void gameEnd() {
		glfwSetWindowShouldClose(GameState.window, true);
	}
	
	public static void gotoStageSelect() {
		GameState.curLevel = new StageSelect();
	}
	
	public static void gotoCover(){
		GameState.curLevel = new Cover();
	}
	
	public static void gotoTitle() {
		GameState.curLevel = new Title();
	}
	
	public static void gotoGame(int stage) {
		int s = (stage % 10 == 0) ? 10 : stage % 10;
		GameState.curLevel = new GameStage(s);
		GameState.stage = stage;
	}
	
	public static void gotoCredit(){
		GameState.curLevel = new Credit();
	}
	
	public static void gamePause() {
		GameState.gameLevel = GameState.curLevel;
		GameState.curLevel = new Pause();
	}
	
	public static void gameResume() {
		GameState.curLevel = GameState.gameLevel;
	}
	
	public static void gameOver() {
		GameState.curLevel = new GameOver();
	}
	
	public static void gameRestart() {
		GameState.curLevel = new GameStage(GameState.stage);
	}

	public static void gameClear() {
		GameState.curLevel = new Clear();
	}
	
	public static void Congratulate(){
		GameState.curLevel = new Congratulate();
	}
}
