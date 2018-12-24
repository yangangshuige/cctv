package com.example.vzvision;

import com.device.*;
import com.database.*;
import java.io.UnsupportedEncodingException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.KeyEvent;
import android.graphics.*;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;
import java.util.Map;
import java.util.HashMap;
import com.todayin.cctv.R;
import com.todayin.cctv.utils.ThreadManager;
import com.vz.PlateResult;
import com.vz.tcpsdk;


public class CameraActivity extends Activity implements tcpsdk.OnDataReceiver, View.OnClickListener {

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
    private SlideMenu slideMenu;
    private int selectId;
    private GlobalVariable m_gb = null;
    private CellLayout celllayout;
    private Map<Integer, DeviceSet> vedioGroup;
    private BussionPopWindow mPop = null;
    private DeviceInfoTable m_DeviceInfoTable = null;
    private boolean m_zoomInFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.print(" System.out.print(viewid);");

        setContentView(R.layout.activity_camera_main);
        tcpsdk.getInstance().setup();
        plateHelper so = new plateHelper(CameraActivity.this, "yitijiDatabase.db", null, 1);
        plateCallbackInfoTable pct = new plateCallbackInfoTable();
        pct.setDataBaseHelper(so);


        SnapImageTable sit = new SnapImageTable();
        sit.setDataBaseHelper(so);


        m_gb = (GlobalVariable) getApplication();

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
        initView();
        setDeviceInfo(2);
//        vedioGroup = new HashMap<Integer, DeviceSet>();
//        for (int i = 0; i < 12; i++) {
//            DeviceInfo di = new DeviceInfo(10 + i);
//            m_DeviceInfoTable.GetCallbackInfo(10 + i, di);
//
//            VedioSetVeiw vsv = new VedioSetVeiw(CameraActivity.this);
//
//            vsv.sethandle(handler);
//            vsv.setId(di.id);
//            DeviceSet ds = new DeviceSet(di, vsv);
//
//            ds.setPlateInfoCallBack(this, 1);
//
//
//            celllayout.addView(vsv, i);
//
//
//            vedioGroup.put(10 + i, ds);
//        }
    }

    private void setDeviceInfo(int num) {
        if (celllayout.getChildCount() > 0) {
            for (int i = 0; i < celllayout.getChildCount(); i++) {
                VedioSetVeiw child = (VedioSetVeiw) celllayout.getChildAt(i);
                child.StopPlay();
            }
            celllayout.removeAllViews();
        }
        vedioGroup = new HashMap<Integer, DeviceSet>();
        for (int i = 0; i < num; i++) {
            DeviceInfo di = new DeviceInfo(10 + i);
            m_DeviceInfoTable.GetCallbackInfo(10 + i, di);

            VedioSetVeiw vsv = new VedioSetVeiw(CameraActivity.this);

            vsv.sethandle(handler);
            vsv.setId(di.id);
            DeviceSet ds = new DeviceSet(di, vsv);

            ds.setPlateInfoCallBack(this, 1);


            celllayout.addView(vsv, i);


            vedioGroup.put(10 + i, ds);
        }
        ThreadManager.execute(new Runnable() {
            @Override
            public void run() {
                java.util.Iterator it = vedioGroup.entrySet().iterator();
                while (it.hasNext()) {
                    java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
                    DeviceSet ds = (DeviceSet) entry.getValue();
                    if (ds != null && ds.open()) {
                        ds.playVideo();
                    }
                }
            }
        });
    }

    private void initView() {
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_ten).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_two) {
            celllayout.setLineNum(1);
            celllayout.setColumnNum(2);
            setDeviceInfo(2);
        }
        if (i == R.id.btn_four) {
            celllayout.setLineNum(2);
            celllayout.setColumnNum(2);
            setDeviceInfo(4);
        }
        if (i == R.id.btn_six) {
            celllayout.setLineNum(3);
            celllayout.setColumnNum(2);
            setDeviceInfo(6);
        }
        if (i == R.id.btn_nine) {
            celllayout.setLineNum(3);
            celllayout.setColumnNum(3);
            setDeviceInfo(9);
        }
        if (i == R.id.btn_ten) {
            celllayout.setLineNum(4);
            celllayout.setColumnNum(3);
            setDeviceInfo(12);
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
        ThreadManager.releasePool();
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
                Toast.makeText(CameraActivity.this, "车牌回调数据失败:未找到设备", Toast.LENGTH_SHORT).show();
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


            String plateText = new String(plateResult.license, "UTF-8");


            Log.e("yg", plateText);

            if (!m_gb.getplateCallbackInfoTable().addCallbackInfo(di.DeviceName, plateText, dateText, pImgFull, pImgPlateClip)) {
                Toast.makeText(CameraActivity.this, "添加车牌回调数据失败", Toast.LENGTH_SHORT).show();
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

            Toast.makeText(CameraActivity.this, "不支持的解码异常", Toast.LENGTH_SHORT).show();
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
                            CameraActivity.this.selectId = vediosetid;
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
                        Intent intent = new Intent(CameraActivity.this, DeviceActivity.class);

                        intent.putExtra(deviceNameLabel, ds.getDeviceInfo().DeviceName);
                        intent.putExtra(deviceIpLabel, ds.getDeviceInfo().ip);
                        intent.putExtra(devicePortLabel, ds.getDeviceInfo().port);
                        intent.putExtra(UserNameLabel, ds.getDeviceInfo().username);
                        intent.putExtra(UserPasswordLabel, ds.getDeviceInfo().userpassword);

                        CameraActivity.this.startActivityForResult(intent, 0);
                        break;
                    }

                }
                case StopVedio: {
                    int vediosetid = msg.arg1;

                    DeviceSet ds = CameraActivity.this.getDeviceSetFromId(vediosetid);
                    if (ds != null) {
                        ds.stopVideo();
                    }

                }
                break;
                case StartVedio: {
                    int vediosetid = msg.arg1;


                    DeviceSet ds = CameraActivity.this.getDeviceSetFromId(vediosetid);
                    if (ds != null) {
                        ds.playVideo();
                    }
                }
                break;
                case PlateImage: {
                    Bitmap bmp = (Bitmap) msg.obj;

                    DeviceSet ds = CameraActivity.this.getDeviceSetFromId(msg.arg1);

                    if (bmp != null) {


                        ds.setPlateImage(bmp);
                    }

                    Bundle bundle = msg.getData();

                    ds.setTrriglePlateText(bundle.getString("plate"));


                }
                break;
                default:
                    Toast.makeText(CameraActivity.this, "未知消息", Toast.LENGTH_SHORT).show();
                    break;
            }

        }

        ;
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (RESULT_OK == resultCode) {
            Bundle bundle = intent.getExtras();

            String devicename = bundle.getString(CameraActivity.deviceNameLabel);
            String deviceip = bundle.getString(CameraActivity.deviceIpLabel);
            String deviceport = bundle.getString(CameraActivity.devicePortLabel);
            String userName = bundle.getString(CameraActivity.UserNameLabel);
            String userPassword = bundle.getString(CameraActivity.UserPasswordLabel);


            DeviceSet ds = this.getDeviceSetFromId(selectId);

            if (ds != null) {
                DeviceInfo di = ds.getDeviceInfo();

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
                Intent intent = new Intent(CameraActivity.this, SnapImageActivity.class);
                CameraActivity.this.startActivity(intent);

            } else if (id == R.id.TextView_PlateInfo) {
                Intent intent = new Intent(CameraActivity.this, PlateActivity.class);
                CameraActivity.this.startActivity(intent);

            } else if (id == R.id.TextView_about) {
                Intent intent = new Intent(CameraActivity.this, AboutActivity.class);
                CameraActivity.this.startActivity(intent);

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


 