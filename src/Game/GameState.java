package Game;

import Game.Level.Level;
import Graphics.Graphics;
import Input.KeyBoardInput;
import Input.MouseButtonInput;
import Sounds.Sound;

public class GameState {
	public static Sound sound;
	public static Graphics graphics;
	public static KeyBoardInput keyInput;
	public static MouseButtonInput mouseInput;
	
	public static Level curLevel;
	
	public static long window;
}
