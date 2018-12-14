package com.vz;

 

import android.util.Log;

public class tcpsdk {
	
	private static tcpsdk  m_tcpsdk = null;
	
	private void tcpsdk()
	{
		
	}
	
	public static tcpsdk getInstance()
	{
		if(m_tcpsdk == null)
			m_tcpsdk = new tcpsdk();
		return m_tcpsdk;
	}
	
	/**
	*  @brief ȫ�ֳ�ʼ��
	*  @return 0��ʾ�ɹ���-1��ʾʧ��
	*  @note �����нӿڵ���֮ǰ����
	*  @ingroup group_global
	*/
    public native int   setup();
    /**
    *  @brief ȫ���ͷ�
    *  @note �ڳ������ʱ���ã��ͷ�SDK����Դ
    *  @ingroup group_global
    */
    public native void  cleanup();
     
    /**
    *  @brief ��һ���豸
    *  @param  [IN] ip �豸��IP��ַ
    *  @param  [IN] ipLength �豸��IP��ַ����
    *  @param  [IN] port �豸�Ķ˿ں�
    *  @param  [IN] username �����豸�����û���
    *  @param  [IN] userpassword �����豸��������
    *  @return �����豸�Ĳ������������ʧ��ʱ������0
    *  @ingroup group_device
    */
    public native int   open(byte[] ip,int ipLength,int port,byte[] username,int userLength,byte[] userpassword ,int passwordLenth);
    
    /**
    *  @brief �ر�һ���豸
    *  @param  [IN] handle ��open������õľ��
    *  @return 0��ʾ�ɹ���-1��ʾʧ��
    *  @ingroup group_device
    */
    public native int   close(int tcphandle);

	 	/**
	*  @brief ����IO���
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] uChnId IO�����ͨ���ţ���0��ʼ
	*  @param  [IN] nOutput  ��Ҫ���õ�IO�����״̬��0��ʾ�̵�����·��1��ʾ�̵�����·
	*  @return 0��ʾ�ɹ���-1��ʾʧ��
	*  @ingroup group_device
	*/
   public native  int  setIoOutput(int handle, short uChnId, int nOutput);

    
   public native  int   getIoOutput(int  handle,  int  uChnId , int[] nOutput);

   	/**
	*  @brief ����IO��������Զ���λ
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] uChnId IO�����ͨ���ţ���0��ʼ
	*  @param  [IN] nDuration ��ʱʱ�䣬ȡֵ��Χ[500, 5000]����
	*  @return 0��ʾ�ɹ���-1��ʾʧ��
	*  @ingroup group_device
	*/
   public native  int  setIoOutputAuto(int handle, short uChnId, int nDuration);
   
   /**
	*  @brief ��ȡ����״̬
	*  @param  [IN] handle ��open������õľ��
	*  @return true��ʾ�����У�false��ʾδ����
	*  @note   �û��������ڵ��øú�����������ѯ�豸�Ƿ���ߣ��Լ����ߺ��Ƿ�ָ�����
	*  @ingroup group_device
	*/
   public native  boolean  isConnected(int handle);
   
   /**
	*  @brief �����ѻ�����Ļص�����
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] onDataReceiver ʶ�����ص����������ΪNULL�����ʾ�رոûص������Ĺ���
	*  @param  [IN] bEnableImage ָ��ʶ�����Ļص��Ƿ���Ҫ������ͼ��Ϣ��1Ϊ��Ҫ��0Ϊ����Ҫ
	*  @return 0��ʾ�ɹ���-1��ʾʧ��
	*  @ingroup group_device
	*/
   public native int setPlateInfoCallBack( int handle,  OnDataReceiver  onDataReceiver ,int bEnableImage);
 //  public native int setPlateInfoCallBack( OnDataReceiver  onDataReceiver );
   
   /**
	*  @brief ������������źţ�ǿ�ƴ���ǰʱ�̵����ݲ�������
	*  @param  [IN] handle ��open������õľ��
	*  @return 0��ʾ�ɹ���-1��ʾʧ��
	*  @note   ����ʶ����ͨ���ص������ķ�ʽ���أ������ǰ��Ƶ�������޳��ƣ���ص��������ᱻ����
	*  @ingroup group_device
	*/
   public native int forceTrigger(int handle);
   
   /**
	*  @brief ����͸��ͨ��
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] nSerialPort ָ��ʹ���豸�Ĵ�����ţ�0��ʾ��һ�����ڣ�1��ʾ�ڶ�������
	*  @return ����͸��ͨ�������0��ʾʧ��
	*  @ingroup group_device
	*/
   public native int serialStart(int handle, int nSerialPort);

    /**
	*  @brief ͸��ͨ����������
	*  @param [IN] nSerialHandle ��serialStart������õľ��
	*  @param [IN] pData ��Ҫ��������ݿ���׵�ַ
	*  @param [IN] uSizeData ��Ҫ��������ݿ���ֽ���
	*  @return 0��ʾ�ɹ�������ֵ��ʾʧ��
	*  @ingroup group_device
	*/
   public native int  serialSend(int handle, int nSerialPort, byte[] pData, long uSizeData);

    /**
	*  @brief ͸��ͨ��ֹͣ��������
	*  @param [IN] nSerialHandle ��serialStart������õľ��
	*  @return 0��ʾ�ɹ�������ֵ��ʾʧ��
	*  @ingroup group_device
	*/
   public native int serialStop(int handle);

      /**
	*  @brief ����͸��ͨ��
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] imgBuffer ͼƬ������
	*  @param  [IN] imgBufferMaxLength ͼƬ����������
	*  @return ����͸��ͨ�������0��ʾʧ��
	*  @ingroup group_device
	*/
   public native int  getSnapImageData(int handle, byte[] imgBuffer, int imgBufferMaxLength);

       /**
	*  @brief ��ȡRTSP��ַ
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] url ������
	*  @param  [IN] urlMaxLength ����������
	*  @return ����͸��ͨ�������0��ʾʧ��
	*  @ingroup group_device
	*/
   public native int  getRtspUrl(int handle,  byte[] url, int urlMaxLength);

       /**
	*  @brief ��������
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] voice ��������
	*  @param  [IN] interval �����ļ��Ĳ��ż��(0-5000��
	*  @param  [IN] volume ������С(0-100)
	*  @param  [IN] male  ��������(����0��Ů��1)
	*  @return ����͸��ͨ�������0��ʾʧ��
	*  @ingroup group_device
	*/
   public native int playVoice( int handle, byte[] voice, int interval, int volume, int male);
   
       /**
	*  @brief ���ð������ص�
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] recevier �ص�
	*  @return ����͸��ͨ�������0��ʾʧ��
	*  @ingroup group_device
	*/
   public native int setWlistInfoCallBack(int handle,onWlistReceiver recevier);

     /**
	*  @brief ���������
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] wllist ������
	*  @return ����͸��ͨ�������0��ʾʧ��
	*  @ingroup group_device
	*/
   public native int  importWlistVehicle(int handle,WlistVehicle wllist);

     /**
	*  @brief ɾ��������
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] plateCode ���ƺ�
	*  @return ����͸��ͨ�������0��ʾʧ��
	*  @ingroup group_device
	*/
   public native int  deleteWlistVehicle(int handle,byte[] plateCode);

     /**
	*  @brief ��ѯ������
	*  @param  [IN] handle ��open������õľ��
	*  @param  [IN] plateCode ���ƺ�
	*  @return ����͸��ͨ�������0��ʾʧ��
	*  @ingroup group_device
	*/
   public native int  queryWlistVehicle(int handle,byte[] plateCode);
   
	public interface OnDataReceiver {
		
		void onDataReceive(int handle,PlateResult plateResult,int uNumPlates,int eResultType,
				byte[] pImgFull,int nFullSize, byte[] pImgPlateClip,int nClipSize  );
//		void onDataReceive(int handle,byte[] szPlateData,int plateLength,int plateConfidence,int plateType,byte[] bdTimeData,int timeLength,
//				byte[] pImgFull,int nFullSize, byte[] pImgPlateClip,int nClipSize);
		
		
	}
	
	public interface onWlistReceiver {
		void onWlistReceive(int handle, WlistVehicle wlist  );
	}
    static {
    	try {
    		//System.loadLibrary("log");
    		System.loadLibrary("vztcpsdk_dynamic");
            System.loadLibrary("tcpsdk");
    	}
    	catch(UnsatisfiedLinkError e) {
			// fatal error, we can't load some our libs
			Log.d("tcpsdk", "Couldn't load lib: " + " - " + e.getMessage());
			
		}
    }
}
