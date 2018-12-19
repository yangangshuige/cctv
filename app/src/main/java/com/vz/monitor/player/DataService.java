package com.vz.monitor.player;

import android.annotation.SuppressLint;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import com.device.*;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.media.MP4Recorder;
import com.media.RTSP;
import com.media.RTSP.OnDataReceiver;
//import com.example.vzt.RealTimeActivity;
import java.util.Arrays;
import android.os.*;

public class DataService implements OnDataReceiver {
	private FrameQueue frameQueue;
	private RTSP rtsp;
	private MP4Recorder mp4Recorder;
	private String ip;
//	private String url = "rtsp://182.92.79.136:8554/slamtv60.264";
//	private String url = "rtsp://VisionZenith:147258369@192.168.1.100:8557/h264";
//	private String url  = "rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp";
//	private String url = "rtsp://192.168.3.30:8557/h264";
//	private String url = "rtsp://admin:12345@192.168.3.79:554/Streaming/Channels/1?transportmode=unicast&profile=Profile_1";
	private boolean isReceive = false; //鏄惁鎺ユ敹鏁版�?
	private boolean isRecording = false; //鏄惁姝ｅ湪褰曞�?

	private long lastTime = System.currentTimeMillis();

	/**
	 * 鐢ㄤ簬璁＄畻鐮佹祦閫熺巼鐨勪笁涓彉閲忥�?.鐮佺�?2.寮�鏃堕棿 3.鎺ユ敹鏁版嵁鎬婚暱搴�?
	 */
	private float dataRate = 0.0f;
	private long startTime = 0L;
	private int totalLength = 0;
	private int handle = -1;
	private Frame innerFrame = null;

	private byte[]  recvData = null;

	private CheckDataThread  checkThread=null;

	private static int y = 0 ;

	public DataService(FrameQueue frameQueue) {
		this.frameQueue = frameQueue;

		innerFrame = new Frame();

	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public void setUrl(String ip) {
		this.ip = ip;
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			default:
				break;
			}
		}
	};


	public boolean start() {
		if(null==rtsp) {
			rtsp = RTSP.getInstance();
		}
		if(!"".equals(ip)) {
			handle = rtsp.startPlay(ip,8557);

			if(  handle == -1 )
              return false;
			rtsp.setOnDataReceiver(handle,this);


			isReceive=true;
			lastTime = System.currentTimeMillis();
			checkThread = new CheckDataThread();
			checkThread.start();
			//new CheckDataThread().start();
			return isReceive;
		}
		return false;
	}

	public void stop() {
		isReceive=false;
		if(null != rtsp) {
			if(  handle == -1 )
	              return;
			rtsp.stopPlay(handle);
			//rtsp = null;
		}

		try
		{
			checkThread.join(1000);

			checkThread = null;
		}
		catch(InterruptedException e)
		{

		}

	}

	public boolean startRecord(String fileName) {
		if(null==mp4Recorder) {
			mp4Recorder = new MP4Recorder();
		}
		isRecording = mp4Recorder.startRecorder(fileName);
		return isRecording;
	}

	public void stopRecord() {
		isRecording = false;
		if(null!=mp4Recorder) {
			mp4Recorder.stopRecorder();
		}
	}

	private void addToFrameQueue(Frame frame) {

		try {
			int size = frameQueue.size();
			if(size > 60) {
			//	if(frame.isKey())
				{
					//frameQueue.getFrameFromQueue();
					//frameQueue.clear();
					frameQueue.clearNonFrame(50);
				}
			}
//			if(size > 200) {
//				Log.i("error", "clear frame queue");
//				frameQueue.clear();
//			}
			frameQueue.addFrameToQueue(frame);
		} catch (Exception e) {
			Log.i("error", "addToFrameQueue faile");
		}
	}

	public static String getSDPath(){
		boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if(hasSDCard){
			return Environment.getExternalStorageDirectory().toString();
		}else
			return Environment.getDownloadCacheDirectory().toString();
	}
	public static void saveFile(String str, String filePath){
		FileOutputStream fos = null;
		try {
			File file = new File(filePath,"rtspdata.txt");
			if(!file.exists())
			{
				try{
                       boolean b = file.createNewFile();
              }catch(Exception e){
                       e.printStackTrace();
              }
			}
			fos = new FileOutputStream(file,true);
			fos.write(str.getBytes());
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				if(null!=fos)
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onDataReceive(byte[] data, int length, int width, int height, int fps,int codectype) {
	//Log.i( "onDataReceive","start:"+String.valueOf(data.length)+" :codectype:"+codectype);


//		  try
//		  {
//			  String path = getSDPath()+File.separator;
//			  String srt2=new String(data,"GBK");
//			  saveFile(srt2,path);
//		  }
//		  catch (Exception e)
//		  {
//			  Log.i("info", "IOException ");
//		  }

		lastTime = System.currentTimeMillis();
		if(isReceive && null != data) {
			//褰曞埗闊宠棰�
			if(null != mp4Recorder && isRecording) {
				mp4Recorder.addSample(data, length, MP4Recorder.TYPE_VIDEO);
			}
			countDataRate(length);

			Frame frame = new Frame();

			byte keyNum = data[4];

			if(keyNum >=5 && keyNum <= 8)
			   frame.setKey(true);
			else
				frame.setKey(false);
//			if(length >= 5) {
//				byte mark = data[4];
//				if(mark==0x65 || mark==0x25 || mark==0x67 || mark==0x27 || mark==0x68 || mark==0x28) {
//					frame.setKey(true);
//				}
//			}
			//byte [] tempData = Arrays.copyOf(data, data.length);

		//	if( ( recvData == null ) || recvData.length < length )
		//	{
			//	byte [] tempData = new byte[length];

			//}
		//	System.arraycopy(data, 0, tempData, 0, length);

			frame.setType(Frame.TYPE_VIDEO);
			frame.setData(data);
			frame.setLength(length);
	//		frame.setTimestamp(timestamp);
	//		frame.setDate(date);
			frame.setDataRate(dataRate);

			MediaInfo mediaInfo = new MediaInfo();
//			mediaInfo.setWidth(width);
//			mediaInfo.setHeight(height);
//			mediaInfo.setFrameRate(fps);
			frame.setMediaInfo(mediaInfo);

			if(codectype == 1 )
			   frame.setCodecType(Codec.CODEC_H264);
			else
			   frame.setCodecType(Codec.CODEC_JPEG);

		//	frameQueue.addFrameToQueue(innerFrame);
			addToFrameQueue(frame);
		}

	//	Log.i("onDataReceive", "onDataReceive end");
	}

	private class CheckDataThread extends Thread {
		@Override
		public void run() {
			super.run();
			while(isReceive) {
				if(System.currentTimeMillis()-lastTime > 10*1000) {
					isReceive =false;
					handler.sendEmptyMessage(VedioSetVeiw.LONG_TIME_NO_DATA);
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	/**
	 * 璁＄畻鐮佹祦閫熺�?
	 * @param length 鏁版嵁闀垮害
	 */
	private void countDataRate(int length) {
		totalLength+=length;
		if(startTime==0) {
			startTime= System.currentTimeMillis();
		} else {
			long nowTime = System.currentTimeMillis();
			long diffTime = nowTime-startTime;
			if(diffTime > 1000) {
				dataRate = (float) (totalLength*1000.0/diffTime/1024*8);
				totalLength=0;
				startTime=nowTime;
			}
		}
	}
}
