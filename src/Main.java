import org.lwjgl.glfw.*;
import Game.*;
import Graphics.Graphics;
import Input.KeyBoardInput;
import Input.MouseButtonInput;
import Sounds.Sound;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Main{

	private int width;
	private int height;
	
	private boolean running = false;
	
	private long window;
	private long monitor;
	
	private GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);
	private KeyBoardInput keyInput;
	private MouseButtonInput mouseInput;
	private Graphics graphics;
	private Sound sound;
	
	private double preTime;
	private double nowTime;
	private double deltaTime;
	private double acTime;
	private int FPS;
	
	public static void main(String[] args) {
		new Main().start();
	}
	
	public void start() {
		running = true;
		init();
		run();
		shutdown();
	}

	public void run() {
		while(running) {
			nowTime = glfwGetTime();
			deltaTime = nowTime - preTime;
			acTime += deltaTime;

			GameState.curLevel.update(deltaTime);
			glfwPollEvents();
			
			graphics.render();
			FPS++;
			
			if(acTime > 1.0) {
				System.out.println("FPS : " + FPS);
				FPS = 0;
				acTime -= 1.0;
			}
			
			glfwSwapBuffers(window);
			
			if(glfwWindowShouldClose(window)) {
				running = false;
			}
			
			preTime = nowTime;
		}
	}
	
	private void init() {
		glfwSetErrorCallback(errorCallback);
		
		if(!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		monitor = glfwGetPrimaryMonitor();
		GLFWVidMode vidMode = glfwGetVideoMode(monitor);
		width = vidMode.width();
		height = vidMode.height();
		
		window = glfwCreateWindow(width, height, "Game", monitor, NULL);
		if(window == NULL) {
			glfwTerminate();
			throw new RuntimeException("Fail to create GLFW window");
		}
		
		keyInput = new KeyBoardInput();
		mouseInput = new MouseButtonInput(window);
		graphics = new Graphics();
		sound = new Sound();
		
		glfwSetKeyCallback(window, keyInput);
		glfwSetMouseButtonCallback(window, mouseInput);
		
		glfwMakeContextCurrent(window);
		
		GameState.graphics = graphics;
		GameState.keyInput = keyInput;
		GameState.mouseInput = mouseInput;
		GameState.sound = sound;
		GameState.window = window;
		
		glfwCreateStandardCursor(GLFW_ARROW_CURSOR);
		
		graphics.init();
		sound.init();
		GameInfo.gameInit();
		
		preTime = glfwGetTime();
		acTime = 0.0;
		FPS = 0;
	}
	
	private void shutdown() {
		sound.shutdown();
		glfwSetErrorCallback(null);
		errorCallback.free();
	}
	
}
