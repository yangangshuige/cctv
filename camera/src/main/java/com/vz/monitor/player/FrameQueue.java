package com.vz.monitor.player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import android.util.Log;

public class FrameQueue {
	private List<Frame> queue = new ArrayList<Frame>();
  
	/**
	 * 鍚戦槦鍒楃殑灏鹃儴娣诲姞鏁版嵁
	 * 
	 * @param frame 甯ф暟锟�
	 * @throws Exception
	 */
	public synchronized void addFrameToQueue(Frame frame) throws Exception {
		if (null == frame) {
			return;
		}
		try{ 
			
			queue.add(frame);
		}
		catch( UnsupportedOperationException  e )
		{
			Log.i("error", e.getMessage());
		}
		
	}

	/**
	 * 浠庨槦鍒楃殑澶撮儴鍙栧嚭锟�锟斤拷鏁版嵁
	 * @return 甯ф暟锟�
	 */
	public synchronized Frame getFrameFromQueue() throws Exception{
		if (queue.size() > 0) {
			return queue.remove(0);
		}
		return null;
	}

	/**
	 * 娓呯┖闃熷垪
	 */
	public synchronized void clear() {
		try {
			queue.clear();
		} catch (Exception e) {
			
		}
	}
	
	public synchronized int size() {
		return queue.size();
	}
	
	public synchronized void clearNonFrame(int maxSize) {
		
		if(maxSize <20)
		{
			maxSize = 20;
		}
		
		 if( queue.size() < maxSize)
			 return;
		 
		 int fristFramePos = 0;
		 
		 while(  queue.size() > maxSize   )
		 {
			 fristFramePos = 0;
			 for(int i = 0 ; i < queue.size(); i++)
			 {
				 Frame frame = queue.get(i);
				 if(frame.isKey())
				 {
					 fristFramePos = i;
					 break;
				 }
			 }
				
			 if(fristFramePos == 0)
				 queue.clear();
			 else
			 {
				 for(int j = 0 ; j<=fristFramePos;j++)
				   queue.remove(0);
			 }
				 
		 }
	}
}
