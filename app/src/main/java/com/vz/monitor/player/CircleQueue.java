package com.vz.monitor.player;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CircleQueue {
	private List<Frame> queue = new ArrayList<Frame>();
	private int         queueSize= 20;
	private int         readPos = 0;
	private int         writePos = 0;
	   
	public CircleQueue()
	{
		 reserveSize(queueSize);
	}
	
	 public void reserveSize(int reserveSize)
	 {
		 if(reserveSize<0)
		 {
			 queue.clear();
			 return;
		 }
		 
		 while (queue.size() > reserveSize )
		 {
			 queue.remove(queue.size() -1);
		 }
		 
		 while (queue.size() < reserveSize )
		 {
			 queue.add(new Frame());
		 }
		 
		 readPos = 0;
		 writePos = 0;
	 }
	
	/**
	 * 向队列的尾部添加数据
	 *
	 * @param frame 帧数�?
	 * @throws Exception
	 */
	public synchronized void addFrameToQueue(Frame frame) //throws Exception
	{

		if(queue.size() == 0)
		{
			return;
		}

		Frame tempFrame = queue.get(writePos);

	//	tempFrame.copy(frame);

		writePos++;

		if(writePos >= queue.size() )
		{
			writePos = 0;
		}
	}

	/**
	 * 从队列的头部取出�?��数据
	 * @return 帧数�?
	 */
	public synchronized boolean getFrameFromQueue(Frame frame)// throws Exception
	{
	    if(readPos == writePos || queue.size() == 0)
	    {
	    	//frame = null;
	    	return false;
	    }

        Frame tempFrame = queue.get(readPos);

       // frame.copy(tempFrame);

        readPos++;

     	if(readPos >= queue.size() )
     	{
     		readPos = 0;
     	}

     	return true;
	}

	/**
	 * 清空队列
	 */
	public synchronized void clear() {
		 
	}
	
	public synchronized int size() {
		return queue.size();
	}
}