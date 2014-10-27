package com.tc.lang.hv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class SoundPlayControl implements SoundChangeListener, Runnable {

	private byte[] sound;
	private Thread playThread;

	public SoundPlayControl() {

	}

	@Override
	public void soundChanged(SoundChangeEvent evt) {
		if (playThread != null && playThread.isAlive()) {
			playThread.interrupt();
		}
		this.sound = evt.getSound();
		playThread = new Thread(this);
		playThread.start();
	}

	@Override
	public void run() {
		if (sound == null)
			return;
		try {
			File file = File.createTempFile("pinyin_sound", ".mp3");
			OutputStream os = new FileOutputStream(file);
			os.write(sound);
			os.close();
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			AudioInputStream din = null;

			AudioFormat mp3Format = ais.getFormat();
			AudioFormat decodedFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED, mp3Format.getSampleRate(),
					16, mp3Format.getChannels(), mp3Format.getChannels() * 2,
					mp3Format.getSampleRate(), false);
			din = AudioSystem.getAudioInputStream(decodedFormat, ais);

			DataLine.Info info = new DataLine.Info(SourceDataLine.class,
					decodedFormat);
			SourceDataLine src = (SourceDataLine) AudioSystem.getLine(info);
			src.open(decodedFormat);
			byte[] buff = new byte[4096];
			src.start();
			@SuppressWarnings("unused")
			int nBytesRead = 0, nBytesWritten = 0;
			while (nBytesRead != -1) {
				nBytesRead = din.read(buff, 0, buff.length);
				if (nBytesRead != -1)
					nBytesWritten = src.write(buff, 0, nBytesRead);
			}
			// Stop
			src.drain();
			src.stop();
			src.close();
			din.close();
			ais.close();
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
