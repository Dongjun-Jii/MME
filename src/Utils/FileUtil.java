package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.BufferUtils.*;

public class FileUtil {

	public static String loadTextFile(String path) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String buffer = "";
			while ((buffer = reader.readLine()) != null) {
				result.append(buffer + '\n');
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	public static ByteBuffer loadImageFile(String path, IntBuffer x, IntBuffer y, IntBuffer comp) {
		ByteBuffer imageBuffer = null;
		imageBuffer = stbi_load(path, x, y, comp, STBI_rgb_alpha);
		return imageBuffer;
	}
	
	public static ByteBuffer loadSoundFile(String path, IntBuffer channels, IntBuffer sample_rate) {
		ShortBuffer ssoundBuffer = null;
		ssoundBuffer = stb_vorbis_decode_filename(path, channels, sample_rate);
		ByteBuffer soundBuffer = createByteBuffer(ssoundBuffer.capacity() << 1);
		soundBuffer.asShortBuffer().put(ssoundBuffer);
		return soundBuffer;
	}
}