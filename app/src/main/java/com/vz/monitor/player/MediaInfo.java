package com.vz.monitor.player;

import android.media.AudioFormat;

public class MediaInfo {
	private int width = 1000; // 图像宽度，如果媒体流不含有视频，则为0�?
	private int height = 1000; // 图像高度，如果媒体流不含有视频，则为0�?
	private int frameRate = 25; // 图像帧率，如果媒体流不含有视频，则为0�?

	private int reseve; // 保留，恒�?�?
	private int channels = AudioFormat.CHANNEL_OUT_MONO;; // 声道数，如果媒体流不含有音频，则�?�?
	private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;  // 采样深度，如果媒体流不含有音频，则为0�?
	private int sampleRate = 8000; // 采样率，如果媒体流不含有音频，则�?�?
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getFrameRate() {
		return frameRate;
	}

	public void setFrameRate(int frameRate) {
		this.frameRate = frameRate;
	}

	public int getReseve() {
		return reseve;
	}

	public void setReseve(int reseve) {
		this.reseve = reseve;
	}

	public int getChannels() {
		return channels;
	}

	public void setChannels(int channels) {
		this.channels = channels;
	}

	public int getAudioFormat() {
		return audioFormat;
	}

	public void setAudioFormat(int audioFormat) {
		this.audioFormat = audioFormat;
	}

	public int getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}

	public void copy(MediaInfo other)
	{
		width = other.width; // 图像宽度，如果媒体流不含有视频，则为0�?
		height = other.height; // 图像高度，如果媒体流不含有视频，则为0�?
		frameRate = other.frameRate; // 图像帧率，如果媒体流不含有视频，则为0�?

		reseve = other.reseve; // 保留，恒�?�?
		channels = other.channels; // 声道数，如果媒体流不含有音频，则�?�?
		audioFormat = other.audioFormat;  // 采样深度，如果媒体流不含有音频，则为0�?
		sampleRate = other.sampleRate; // 采样率，如果媒体流不含有音频，则�?�?
	}
}
