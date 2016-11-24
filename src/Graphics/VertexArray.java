package Graphics;

import java.nio.IntBuffer;

import Maths.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.BufferUtils.*;

public class VertexArray {
	private float [] m_Vertices;
	private final int [] m_Indices;
	private float [] m_UVs;
	private IntBuffer m_IndexBuffer;
	
	public VertexArray(float width, float height, float top, float bottom, float left, float right) {
		m_Vertices = new float[]{
			width / 2, height / 2, 0.0f,
			width / 2, -height / 2, 0.0f,
			-width / 2, height / 2, 0.0f,
			-width / 2, -height / 2, 0.0f
		};
			
		m_Indices = new int[]{
			0, 2, 1,
			2, 3, 1
		};
			
		m_UVs = new float[]{
				right, bottom,
				right, top,
				left, bottom,
				left, top
		};
		
		glVertexAttribPointer(Graphics.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, m_Vertices);
		glVertexAttribPointer(Graphics.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, m_UVs);
		
		m_IndexBuffer = createIntBuffer(m_Indices.length);
		m_IndexBuffer.put(m_Indices);
		m_IndexBuffer.flip();
	}
	
	public void setSize(float width, float height) {
		m_Vertices = new float[]{
			width / 2, height / 2, 0.0f,
			width / 2, -height / 2, 0.0f,
			-width / 2, height / 2, 0.0f,
			-width / 2, -height / 2, 0.0f
		};
		
		glVertexAttribPointer(Graphics.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, m_Vertices);
	}
	
	public void setTexPos(float top, float bottom, float left, float right) {
		m_UVs = new float[]{
			right, top,
			right, bottom,
			left, top,
			left, bottom
		};
		
		glVertexAttribPointer(Graphics.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, m_UVs);
	}
	
	public void draw(Matrix4f mvMatrix){
		glVertexAttribPointer(Graphics.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, m_Vertices);
		glVertexAttribPointer(Graphics.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, m_UVs);
		glUniformMatrix4fv(3, false, mvMatrix.getBuffer());
		
		glEnableVertexAttribArray(Graphics.VERTEX_ATTRIB);
		glEnableVertexAttribArray(Graphics.TCOORD_ATTRIB);
		glDrawElements(GL_TRIANGLES, m_IndexBuffer);
		glDisableVertexAttribArray(Graphics.VERTEX_ATTRIB);
		glDisableVertexAttribArray(Graphics.TCOORD_ATTRIB);
	}
}
