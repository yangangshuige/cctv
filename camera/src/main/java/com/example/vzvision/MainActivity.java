package com.example.vzvision;

import com.device.*;
import com.database.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
//import android.support.v7.widget.GridLayout;
//import android.support.v7.app.ActionBarActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.graphics.*;


import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;


import java.util.Date;
import java.text.SimpleDateFormat;


import java.util.Map;
import java.util.HashMap;


import com.vz.monitor.player.*;

import android.widget.*;

import com.vz.PlateResult;
import com.vz.tcpsdk;


public class MainActivity extends Activity implements tcpsdk.OnDataReceiver {

    public static final int StopVedio = 0x20001;
    public static final int StartVedio = 0x20002;

    public static final int SelectVedio = 0x20009;
    public static final int ConfigDeivce = 0x20010;
    public static final int DClickVedio = 0x200011;
    public static final int PlateImage = 0x200012;


    public static final String deviceNameLabel = "DeviceName";
    public static final String deviceIpLabel = "DeviceIp";
    public static final String devicePortLabel = "DevicePort";
    public static final String UserNameLabel = "UserName";
    public static final String UserPasswordLabel = "UserPassowrd";

    private DisplayMetrics dm;
    private android.widget.GridLayout layout;

    private LayoutInflater mInflater;

    private List<MediaPlayer> mediaPlayerGroup;

    private SlideMenu slideMenu;

    //	private RelativeLayout layout_vedio;
    private DeviceManage deviceManage;

    private int selectId;

    private GlobalVariable m_gb = null;

    private RelativeLayout mainLayout;
    private CellLayout celllayout;

    private Map<Integer, DeviceSet> vedioGroup;

    private BussionPopWindow mPop = null;


    private DeviceInfoTable m_DeviceInfoTable = null;
    private boolean m_zoomInFlag = false;
    private LinearLayout m_ImgGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.print(" System.out.print(viewid);");

        setContentView(R.layout.activity_camera_main);

        //	deviceManage = new DeviceManage();

        tcpsdk.getInstance().setup();

        //requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        plateHelper so = new plateHelper(MainActivity.this, "yitijiDatabase.db", null, 1);
        plateCallbackInfoTable pct = new plateCallbackInfoTable();
        pct.setDataBaseHelper(so);


        SnapImageTable sit = new SnapImageTable();
        sit.setDataBaseHelper(so);


        m_gb = GlobalVariable.getInstence();

        m_gb.setplateCallbackInfoTable(pct);
        m_gb.setSnapImageTable(sit);

        m_DeviceInfoTable = new DeviceInfoTable();
        m_DeviceInfoTable.setDataBaseHelper(so);


        m_gb.getplateCallbackInfoTable().ClearAll();
        m_gb.getSnapImageTable().ClearAll();

        //	m_DeviceInfoTable.ClearAll();

        int count = m_DeviceInfoTable.getRowCount();


        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        try {
            celllayout = findViewById(R.id.gridLayoutMain);
        } catch (Exception e) {
            Log.e("visizion", e.toString());
        }

        m_ImgGroup = (LinearLayout) findViewById(R.id.linerLayout_main_ImgGroup);


        ImageView image = (ImageView) findViewById(R.id.imageView_Trigger);
        //	image.setId(DeviceID );
        image.setOnClickListener(clickListener);

//		image = (ImageView)findViewById(R.id.ImageView_PlateInfo);
//		image.setId(PlateInfoID );
//		image.setOnClickListener(clickListener);

        image = (ImageView) findViewById(R.id.ImageView_Serial);
        //	image.setId(SerialID );
        image.setOnClickListener(clickListener);

        image = (ImageView) findViewById(R.id.ImageView_SnapPicture);
        //	image.setId(CpaturePicID );
        image.setOnClickListener(clickListener);

        image = (ImageView) findViewById(R.id.ImageView_voice);
        //	image.setId(VoiceID );
        image.setOnClickListener(clickListener);

        image = (ImageView) findViewById(R.id.ImageView_WhiteList);
        //	image.setId(WhlistID );
        image.setOnClickListener(clickListener);

        image = (ImageView) findViewById(R.id.ImageView_videoConfig);
        //	image.setId(ConfigVedioID );
        image.setOnClickListener(clickListener);
        image.setVisibility(View.GONE);

        image = (ImageView) findViewById(R.id.ImageView_openGate);
        //	image.setId(ConfigVedioID );
        image.setOnClickListener(clickListener);


        slideMenu = (SlideMenu) findViewById(R.id.slide_menu);
        ImageView menuImg = (ImageView) findViewById(R.id.title_bar_menu_btn);
        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.title_bar_menu_btn) {
                    if (slideMenu.isMainScreenShowing()) {
                        slideMenu.openMenu();
                    } else {
                        slideMenu.closeMenu();
                    }

                }

            }
        });

        TextView plateInfoView = (TextView) slideMenu.findViewById(R.id.TextView_PlateInfo);
        //	plateInfoView.setId(PlateInfoID );
        plateInfoView.setOnClickListener(clickListener);

        TextView snapView = (TextView) slideMenu.findViewById(R.id.TextView_CpaturePicInfo);
        //	snapView.setId(SnapImageID );
        snapView.setOnClickListener(clickListener);


        TextView aboutView = (TextView) slideMenu.findViewById(R.id.TextView_about);
        //	snapView.setId(SnapImageID );
        aboutView.setOnClickListener(clickListener);

        mainLayout = (RelativeLayout) findViewById(R.id.MainLayout);

        celllayout.setColumnNum(2);
        vedioGroup = new HashMap<Integer, DeviceSet>();
        for (int i = 0; i < 2; i++) {
            DeviceInfo di = new DeviceInfo(10 + i);

            m_DeviceInfoTable.GetCallbackInfo(10 + i, di);

            VedioSetVeiw vsv = new VedioSetVeiw(MainActivity.this);

            vsv.sethandle(handler);
            vsv.setId(di.id);

            DeviceSet ds = new DeviceSet(di, vsv);

            ds.setPlateInfoCallBack(this, 1);


            celllayout.addView(vsv, i);


            vedioGroup.put(10 + i, ds);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //启动LOG服务
//		  Intent i  = new Intent();  
//        i.setClass(MainActivity.this, LogService.class);  
//        startService(i);  

    }

    @Override
    protected void onStop() {
        super.onStop();

//		Intent i  = new Intent();  
//        i.setClass(MainActivity.this, LogService.class);  
//        stopService(i);  
    }

    @Override
    protected void onPause() {
        super.onPause();

//		java.util.Iterator it = vedioGroup.entrySet().iterator();
//		while(it.hasNext()){
//		   java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
//		   DeviceSet ds = (DeviceSet)entry.getValue();
//		    
//		   if( (ds != null) && ds.open())
//			{
//			   ds.pause();
//			}
//		}

    }

    @Override
    protected void onResume() {
        super.onResume();

//		java.util.Iterator it = vedioGroup.entrySet().iterator();
//		while(it.hasNext()){
//		   java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
//		   DeviceSet ds = (DeviceSet)entry.getValue();
//		    
//		   if( (ds != null) && ds.open())
//			{
//			   ds.resum();
//			}
//		}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        tcpsdk.getInstance().cleanup();

        m_gb.getplateCallbackInfoTable().ClearAll();
        m_gb.getSnapImageTable().ClearAll();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

//		int width = layout.getWidth();
//		int heiht = layout.getHeight();

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {   //横屏
            m_ImgGroup.setVisibility(View.GONE);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {   //竖屏
            m_ImgGroup.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mPop != null && mPop.isShowing()) {
            mPop.dismiss();
            return true;
        }


        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showDailog("是否要退出一体机工具?");

        }

        return super.onKeyDown(keyCode, event);
    }

    private void showDailog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("确认退出");
        builder.setMessage(msg);
//      builder.setCancelable(false);  
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                if (vedioGroup != null) {
                    java.util.Iterator it = vedioGroup.entrySet().iterator();
                    while (it.hasNext()) {
                        java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
                        DeviceSet ds = (DeviceSet) entry.getValue();
                        ds.stopVideo();
                        ds.close();

                        DeviceInfo di = ds.getDeviceInfo();

                        if (di != null)
                            m_DeviceInfoTable.put(di.id, di.DeviceName, di.ip, di.port, di.username, di.userpassword);

                    }

                    vedioGroup.clear();
                    celllayout.removeAllViews();
                }


                finish();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }

    public void onDataReceive(int handle, PlateResult plateResult, int uNumPlates, int eResultType,
                              byte[] pImgFull, int nFullSize, byte[] pImgPlateClip, int nClipSize) {
        try {
            DeviceSet ds = this.getDeviceSetFromHandle(handle);

            if (ds == null) {
                Toast.makeText(MainActivity.this, "车牌回调数据失败:未找到设备", Toast.LENGTH_SHORT).show();
            }

            DeviceInfo di = ds.getDeviceInfo();

            String dateText = new String();

            dateText += plateResult.struBDTime.bdt_year;
            dateText += "/";

            dateText += plateResult.struBDTime.bdt_mon;
            dateText += "/";

            dateText += plateResult.struBDTime.bdt_mday;
            dateText += " ";

            dateText += plateResult.struBDTime.bdt_hour;
            dateText += ":";

            dateText += plateResult.struBDTime.bdt_min;
            dateText += ":";

            dateText += plateResult.struBDTime.bdt_sec;


            String plateText = new String(plateResult.license, "GBK");


            Log.e("yg", plateText);

            if (!m_gb.getplateCallbackInfoTable().addCallbackInfo(di.DeviceName, plateText, dateText, pImgFull, pImgPlateClip)) {
                Toast.makeText(MainActivity.this, "添加车牌回调数据失败", Toast.LENGTH_SHORT).show();
            }

            Log.i("visizion", "decodeByteArray begin");

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;//图片宽高都为原来的二分之一，即图片为原来的四分之一 
            options.inInputShareable = true;
            Bitmap bmp = null;
            try {

                bmp = BitmapFactory.decodeByteArray(pImgFull, 0, pImgFull.length, options);
                if (bmp != null) {
                    Message msg = new Message();

                    msg.what = PlateImage;

                    msg.arg1 = ds.getDeviceInfo().id;
                    msg.obj = bmp;
                    Bundle data = new Bundle();
                    data.putString("plate", plateText);
                    msg.setData(data);

                    handler.sendMessage(msg);
                }
            } catch (OutOfMemoryError e) {
                Log.e("Map", "Tile Loader (241) Out Of Memory Error " + e.getLocalizedMessage());
                System.gc();
            } catch (Exception e) {

            } finally {
                Log.i("visizion", "decodeByteArray end");
            }


        } catch (UnsupportedEncodingException e) {

            Toast.makeText(MainActivity.this, "不支持的解码异常", Toast.LENGTH_SHORT).show();
        }

    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SelectVedio: {
                    int vediosetid = msg.arg1;

                    java.util.Iterator it = vedioGroup.entrySet().iterator();
                    while (it.hasNext()) {
                        java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
                        DeviceSet ds = (DeviceSet) entry.getValue();

                        if ((Integer) entry.getKey() == vediosetid) {
                            MainActivity.this.selectId = vediosetid;
                            ds.select();
                        } else {
                            ds.unselect();
                            //vsv.setVisibility(View.GONE);
                        }
                    }
                }
                break;
                case DClickVedio: {
                    int vediosetid = msg.arg1;

                    DeviceSet ds = getDeviceSetFromId(vediosetid);

                    ViewSetInnerType type = (ViewSetInnerType) msg.obj;

                    if (!m_zoomInFlag) {
                        celllayout.ZoomIn(vediosetid - 9);

                        if (ds != null) {
                            if (type == ViewSetInnerType.Vedio) {
                                ds.ZoomInVedio();
                            } else {
                                ds.ZoomInImage();
                            }
                        }


                        m_zoomInFlag = true;
                    } else {
                        if (ds != null)
                            if (type == ViewSetInnerType.Vedio) {
                                ds.ZoomOutVedio();
                            } else {
                                ds.ZoomOutImage();
                            }


                        celllayout.recover();
                        m_zoomInFlag = false;
                    }

                }
                break;

                case ConfigDeivce: {
                    int vediosetid = msg.arg1;

                    DeviceSet ds = getDeviceSetFromId(vediosetid);

                    if (ds != null) {
                        Intent intent = new Intent(MainActivity.this, DeviceActivity.class);

                        intent.putExtra(deviceNameLabel, ds.getDeviceInfo().DeviceName);
                        intent.putExtra(deviceIpLabel, ds.getDeviceInfo().ip);
                        intent.putExtra(devicePortLabel, ds.getDeviceInfo().port);
                        intent.putExtra(UserNameLabel, ds.getDeviceInfo().username);
                        intent.putExtra(UserPasswordLabel, ds.getDeviceInfo().userpassword);

                        MainActivity.this.startActivityForResult(intent, 0);
                        break;
                    }

                }
                case StopVedio: {
                    int vediosetid = msg.arg1;

                    DeviceSet ds = MainActivity.this.getDeviceSetFromId(vediosetid);
                    if (ds != null) {
                        ds.stopVideo();
                    }

                }
                break;
                case StartVedio: {
                    int vediosetid = msg.arg1;


                    DeviceSet ds = MainActivity.this.getDeviceSetFromId(vediosetid);
                    if (ds != null) {
                        ds.playVideo();
                    }
                }
                break;
                case PlateImage: {
                    Bitmap bmp = (Bitmap) msg.obj;

                    DeviceSet ds = MainActivity.this.getDeviceSetFromId(msg.arg1);

                    if (bmp != null) {


                        ds.setPlateImage(bmp);
                    }

                    Bundle bundle = msg.getData();

                    ds.setTrriglePlateText(bundle.getString("plate"));


                }
                break;
                default:
                    Toast.makeText(MainActivity.this, "未知消息", Toast.LENGTH_SHORT).show();
                    break;
            }

        }

        ;
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (RESULT_OK == resultCode) {
            Bundle bundle = intent.getExtras();

            String devicename = bundle.getString(MainActivity.deviceNameLabel);
            String deviceip = bundle.getString(MainActivity.deviceIpLabel);
            String deviceport = bundle.getString(MainActivity.devicePortLabel);
            String userName = bundle.getString(MainActivity.UserNameLabel);
            String userPassword = bundle.getString(MainActivity.UserPasswordLabel);


            DeviceSet ds = this.getDeviceSetFromId(selectId);

            if (ds != null) {
                DeviceInfo di = ds.getDeviceInfo();

//				di.DeviceName = devicename;
//				di.ip = deviceip;
//				di.port = Integer.parseInt(deviceport);
//				di.username = userName;
//				di.userpassword = userPassword;

                //  if(di.ip != deviceip)
                {

                    ds.stopVideo();
                    ds.close();


                    if (ds.open(devicename, deviceip, Integer.parseInt(deviceport), userName, userPassword)) {

                        ds.playVideo();
                    }

                }
            }

        }

        super.onActivityResult(requestCode, resultCode, intent);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.TextView_CpaturePicInfo) {
                Intent intent = new Intent(MainActivity.this, SnapImageActivity.class);
                MainActivity.this.startActivity(intent);

            } else if (id == R.id.TextView_PlateInfo) {
                Intent intent = new Intent(MainActivity.this, PlateActivity.class);
                MainActivity.this.startActivity(intent);


            } else if (id == R.id.ImageView_WhiteList) {
                DeviceSet ds = MainActivity.this.getDeviceSetFromId(selectId);
                if (ds != null && ds.getopenFlag()) {

                    m_gb.setDeviceSet(ds);

                    Intent intent = new Intent(MainActivity.this, WlistActivity.class);

                    MainActivity.this.startActivity(intent);

                } else
                    Toast.makeText(MainActivity.this, "请先选中或打开设备", Toast.LENGTH_SHORT).show();


            } else if (id == R.id.ImageView_voice) {
                if (mPop != null && mPop.isShowing()) {
                    mPop.dismiss();

                }
                DeviceSet ds = MainActivity.this.getDeviceSetFromId(selectId);
                if (ds != null && ds.getopenFlag()) {
                    mPop = new VoicePopWindow(MainActivity.this, ds);

                    mPop.show();


                } else
                    Toast.makeText(MainActivity.this, "请先选中或打开设备", Toast.LENGTH_SHORT).show();


            } else if (id == R.id.ImageView_Serial) {
                if (mPop != null && mPop.isShowing()) {
                    mPop.dismiss();

                }
                DeviceSet ds = MainActivity.this.getDeviceSetFromId(selectId);
                if (ds != null && ds.getopenFlag()) {
                    mPop = new SerialPopWindow(MainActivity.this, ds);

                    mPop.show();

                } else
                    Toast.makeText(MainActivity.this, "请先选中或打开设备", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.imageView_Trigger) {
                DeviceSet ds = MainActivity.this.getDeviceSetFromId(selectId);
                if (ds != null && ds.getopenFlag()) {
                    ds.forceTrigger();
                    Toast.makeText(MainActivity.this, "触发成功", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "请先选中或打开设备", Toast.LENGTH_SHORT).show();


            } else if (id == R.id.ImageView_openGate) {
                if (mPop != null && mPop.isShowing()) {
                    mPop.dismiss();

                }
                DeviceSet ds = MainActivity.this.getDeviceSetFromId(selectId);
                if (ds != null && ds.getopenFlag()) {
                    mPop = new GatePopWindow(MainActivity.this, ds);

                    mPop.show();


                } else
                    Toast.makeText(MainActivity.this, "请先选中或打开设备", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.ImageView_SnapPicture) {
                DeviceSet ds = getDeviceSetFromId(MainActivity.this.selectId);
                if (ds != null && ds.getopenFlag()) {
                    byte[] imgBuffer = new byte[1024 * 200];
//                        tcpsdk.getInstance().setIoOutput(ds.getDeviceInfo().handle, (short) 0,1);
                    int snapImgLength = ds.getSnapImageData(imgBuffer, imgBuffer.length);

                    if (snapImgLength > 0)   //成功
                    {
                        Date now = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

                        String currentTime = dateFormat.format(now);
                        byte[] realimgBuffer = new byte[snapImgLength];

                        System.arraycopy(imgBuffer, 0, realimgBuffer, 0, snapImgLength);

                        m_gb.getSnapImageTable().add(currentTime, realimgBuffer);
                        Toast.makeText(MainActivity.this, "截图成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "截图失败", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(MainActivity.this, "请先选中或者打开设备", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.ImageView_videoConfig) {
                DeviceSet ds = getDeviceSetFromId(selectId);
                if (ds == null || !ds.getopenFlag()) {
                    Toast.makeText(MainActivity.this, "请先选中或者打开设备", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(MainActivity.this, VideoConfigActivity.class);
                m_gb.setDeviceSet(ds);
                MainActivity.this.startActivity(intent);


            } else if (id == R.id.TextView_about) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                MainActivity.this.startActivity(intent);

            }
        }
    };

    public DeviceSet getDeviceSetFromId(int id) {


        java.util.Iterator it = vedioGroup.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
            DeviceSet ds = (DeviceSet) entry.getValue();

            if ((Integer) entry.getKey() == id) {
                return ds;
            }
        }


        return null;
    }

    public DeviceSet getDeviceSetFromHandle(int handle) {


        java.util.Iterator it = vedioGroup.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
            DeviceSet ds = (DeviceSet) entry.getValue();

            if ((ds != null) && (handle == ds.getDeviceInfo().handle)) {
                return ds;
            }
        }


        return null;
    }

}


 