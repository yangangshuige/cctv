package com.vz.monitor.player;

import java.util.Vector;

/**
 * ����??
 * ʵʱ��Ƶ���п��Էֳ����ɸ�Packet??
 * @author Administrator
 */
public class Packet {
	private int amount; //���ݰ�������
	private Vector<Integer> positionList;	//ÿ�����ݰ�����ʼλ�õ���??

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Vector<Integer> getPositionList() {
		return positionList;
	}

	public void setPositionList(Vector<Integer> positionList) {
		this.positionList = positionList;
	}
}
