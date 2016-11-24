package Graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.opengl.GL;

import Game.GameState;
import Maths.Matrix4f;
import Utils.ShaderUtil;

public class Graphics {
	public static final int VERTEX_ATTRIB = 0;
	public static final int TCOORD_ATTRIB = 1;
	
	private int m_Program;
	private Matrix4f m_OrthoMat = Matrix4f.orthographic(0, 1920, 1080, 0, -1, 1);
	public void init() {
		GL.createCapabilities();
		m_Program = glCreateProgram();
		ShaderUtil.loadShader(m_Program, "./shader/square");
		glUseProgram(m_Program);
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glUniformMatrix4fv(4, false, m_OrthoMat.getBuffer());
		GameState.curLevel.draw();
	}
}