package Utils;

import static org.lwjgl.opengl.GL20.*;

public class ShaderUtil {
	
	public static void loadShader(int program, String path){
		int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		
		String vertexSource = FileUtil.loadTextFile(path + ".vert");
		String fragmentSource = FileUtil.loadTextFile(path + ".frag");
		
		glShaderSource(vertexShader, vertexSource);
		glShaderSource(fragmentShader, fragmentSource);
		
		glCompileShader(vertexShader);
		glCompileShader(fragmentShader);
		
		glAttachShader(program, vertexShader);
		glAttachShader(program, fragmentShader);
		
		glLinkProgram(program);
		
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
	}
}
