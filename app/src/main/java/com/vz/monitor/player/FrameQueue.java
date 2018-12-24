package com.vz.monitor.player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import android.util.Log;

public class FrameQueue {
	private List<Frame> queue = new ArrayList<Frame>();

	/**
	 * 向队列的尾部添加数据
	 *
	 * @param frame 帧数�?
	 * @throws Exception
	 */
	public synchronized void addFrameToQueue(Frame frame) throws Exception {
		if (null == frame) {
			return;
		}
		try{

			queue.add(frame);
		}
		catch( UnsupportedOperationException e )
		{
			Log.i("error", e.getMessage());
		}

	}

	/**
	 * 从队列的头部取出�?��数据
	 * @return 帧数�?
	 */
	public synchronized Frame getFrameFromQueue() throws Exception {
		if (queue.size() > 0) {
			return queue.remove(0);
		}
		return null;
	}

	/**
	 * 清空队列
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
