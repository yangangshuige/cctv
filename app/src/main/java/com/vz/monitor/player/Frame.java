package com.vz.monitor.player;

/**
 * ֡��Ϣ
 *
 * @author ̷����
 * @date 2012-6-28
 */
public class Frame {
	public static final int TYPE_VIDEO = 1;
	public static final int TYPE_AUDIO = 2;

	private int type = TYPE_VIDEO; // ֡���ͣ���Ƶ֡/��Ƶ֡

	private byte[] data; // ֡����
	private int length; // ֡����
	private int timestamp; // ֡��ʱ���
	private boolean isKey = false; // �Ƿ�ؼ�֡
	private int date;		//֡ʱ��(��)

	private float dataRate;	//���ʣ�kbps
	
	private MediaInfo mediaInfo;
	
	private int codecType = Codec.CODEC_H264;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	
	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public MediaInfo getMediaInfo() {
		return mediaInfo;
	}

	public void setMediaInfo(MediaInfo mediaInfo) {
		this.mediaInfo = mediaInfo;
	}

	public float getDataRate() {
		return dataRate;
	}

	public void setDataRate(float dataRate) {
		this.dataRate = dataRate;
	}

	public int getCodecType() {
		return codecType;
	}

	public void setCodecType(int codecType) {
		this.codecType = codecType;
	}
}
