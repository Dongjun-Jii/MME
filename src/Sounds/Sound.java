package Sounds;

import java.nio.*;
import java.util.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.*;

import static org.lwjgl.openal.AL10.*;
import static Utils.FileUtil.*;

public class Sound {
	private Map<String, Integer> m_SoundBuffers = new HashMap<String, Integer>();
	private Map<String, SoundSource> m_Sources = new HashMap<String, SoundSource>();
	private long m_SoundDevice;
	private long m_Context;
	
	public Sound() {
		
	}
	
	public void init() {
		IntBuffer ib = null;
		ByteBuffer bb = null;
		m_SoundDevice = ALC10.alcOpenDevice(bb);
		m_Context = ALC10.alcCreateContext(m_SoundDevice, ib);
		ALC10.alcMakeContextCurrent(m_Context);
		AL.createCapabilities(ALC.createCapabilities(m_SoundDevice));
		alListener3f(AL_POSITION, 0, 0, 0);
		alListener3f(AL_VELOCITY, 0, 0, 0);
	}
	
	public void shutdown() {
		ALC10.alcDestroyContext(m_Context);
		ALC10.alcCloseDevice(m_SoundDevice);
	}
	
	public void loadSound(String name, String path){
		int buffer = alGenBuffers();
		IntBuffer channels = BufferUtils.createIntBuffer(1);
		IntBuffer sample_rate = BufferUtils.createIntBuffer(1);
		ByteBuffer data = loadSoundFile(path, channels, sample_rate);
		
		if(channels.get(0) == 1) {
			alBufferData(buffer, AL_FORMAT_MONO16, data, sample_rate.get(0));
		}else if(channels.get(0) == 2) {
			alBufferData(buffer, AL_FORMAT_STEREO16, data, sample_rate.get(0));
		}
		
		m_SoundBuffers.put(name, buffer);
		
		SoundSource source = new SoundSource(buffer);
		m_Sources.put(name, source);
	}
	
	public void play(String name) {
		m_Sources.get(name).play();
	}
	
	public void pause(String name) {
		m_Sources.get(name).pause();
	}
	
	public void stop(String name) {
		m_Sources.get(name).stop();
	}
	
}
