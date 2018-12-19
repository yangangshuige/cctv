package com.device;

import java.util.Timer;

import android.graphics.Bitmap;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;

import com.example.vzvision.CameraActivity;

import com.example.vzvision.ViewSetInnerType;
import com.todayin.cctv.R;
import com.vz.monitor.player.MediaPlayer;
//import com.example.vzvision.*;

public class VedioSetVeiw extends LinearLayout {
    public static final int clickMediaPlayerId = 0x1001;
    public static final int LONG_TIME_NO_DATA = 0x1001;
//	private final int     clickPlayId = 0x1002;
//	private final int     clickStopId = 0x1003;
//	private final int     clickConfigId = 0x1004;

    //	private LayoutInflater layoutInflater;
//	private RelativeLayout  layout;
    private ImageView startView;
    private ImageView stopView;
    private ImageView configView;
    private TextView DeviceNameEdit;
    private TextView ErrorEdit;
    private int colornum = 0;
    private boolean buttonVisible = false;
    private Handler parentHandler = null;
    private MediaPlayer mediaPlayer = null;
    private boolean playFlag = false;
    private TextView FrameTime;
    private TextView PicTime;

    private MyImageView plateImageView;

    private boolean ErrorEditDisplayFlag = true;

    private LinearLayout mainLayout;
    private RelativeLayout vedioLayout;
    private TextView trriglePlate;

    public VedioSetVeiw(Context context) {
        super(context);
        ((Activity) getContext()).getLayoutInflater().inflate(R.layout.vedioset, this);

        startView = (ImageView) this.findViewById(R.id.imageView_start);
        stopView = (ImageView) this.findViewById(R.id.imageView_stop);

        configView = (ImageView) this.findViewById(R.id.imageView_config);
        DeviceNameEdit = (TextView) this.findViewById(R.id.textView_VideoDeviceName);

//	    FrameTime =  (TextView)this.findViewById( R.id.textView_FrameTime);
        PicTime = (TextView) this.findViewById(R.id.textView_PicTime);

        mainLayout = (LinearLayout) findViewById(R.id.relativeLayout_vedioset_main);
        DeviceNameEdit.setText("�豸1");

        ErrorEdit = (TextView) this.findViewById(R.id.textView_plateID);
        ErrorEdit.setText("����Ƶ");

        plateImageView = (MyImageView) this.findViewById(R.id.imageView_snapPlate);
        this.registerDoubleClickListener(plateImageView, mediaOnDoubleClick);

        trriglePlate = (TextView) this.findViewById(R.id.textView_trriglePlate);

        //plateImgLayout  =  (RelativeLayout)findViewById( R.id.RelativeLayout_plateImg);

        vedioLayout = (RelativeLayout) findViewById(R.id.LinearLayout_Vedio);

        mediaPlayer = new MediaPlayer(context); //(MediaPlayer)findViewById( R.id.mediaPlayer_device);//

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        //   lp.setMargins(2, 2, 2, 2);

        mediaPlayer.setLayoutParams(lp);

        vedioLayout.addView(mediaPlayer, 0);

        //mediaPlayer.setOnClickListener(mediaClickListener);
        mediaPlayer.setId(clickMediaPlayerId);
        mediaPlayer.setHandler(handler);

        this.registerDoubleClickListener(mediaPlayer, mediaOnDoubleClick);

        startView.setOnClickListener(mediaClickListener);
        // startView.setId(clickPlayId);

        stopView.setOnClickListener(mediaClickListener);
        //   stopView.setId(clickStopId);

        configView.setOnClickListener(mediaClickListener);
        // configView.setId(clickConfigId);


        VedioSetVeiw.this.setWillNotDraw(false);


        startView.setVisibility(View.INVISIBLE);
        // stopView.setVisibility(View.INVISIBLE);
        configView.setVisibility(View.INVISIBLE);


//        timetask = new DisplayTask();
//
//        timer.schedule(timetask, 2000);
    }

    //	 public MediaPlayer getMediaPlayer()
//	 {
//		 return mediaPlayer;
//	 }
    @Override
    public void setVisibility(int visibility) {

//        mainLayout.setVisibility(visibility);
//        DeviceNameEdit.setVisibility(visibility);
////        plateImageView.setVisibility(visibility);
//
//        if (ErrorEditDisplayFlag)
//            ErrorEdit.setVisibility(visibility);
//        mediaPlayer.setVisibility(visibility);
        super.setVisibility(visibility);
    }


    public void setErrorText(String text) {
        if (ErrorEdit != null)
            ErrorEdit.setText(text);
    }

    public void setDeviceName(String text) {
        if (DeviceNameEdit != null)
            DeviceNameEdit.setText(text);
    }

    public void setErrorTextIsVisible(boolean flag) {
        if (flag) {
            ErrorEdit.setVisibility(View.VISIBLE);
            ErrorEditDisplayFlag = true;
        } else {
            ErrorEditDisplayFlag = false;
            ErrorEdit.setVisibility(View.INVISIBLE);
        }
    }

    public void StartPlay() {
        if (mediaPlayer == null)
            return;

        if (mediaPlayer.getUrlip() == "") {
            setErrorTextIsVisible(true);
            setErrorText("���ȴ��豸");

            return;
        }

        if (mediaPlayer.isVideoPlaying())
            mediaPlayer.stopPlay();

        mediaPlayer.startPlay();

        startView.setImageResource(R.drawable.stop);
        playFlag = true;


    }

    public void StopPlay() {
        if (mediaPlayer.isVideoPlaying())
            mediaPlayer.stopPlay();

        startView.setImageResource(R.drawable.play);
        playFlag = false;

    }

    public void setUrlip(String ip) {
        mediaPlayer.setUrlip(ip);

    }

    public void pause() {
        mediaPlayer.pause();

    }

    public void resum() {
        mediaPlayer.resum();
    }

    public void setPlateImage(Bitmap bmp) {
        if (bmp != null)
            this.plateImageView.setImageBitmap(bmp);
    }

    public void setTrriglePlateText(String plateText) {
        String s = plateText;
        trriglePlate.setText(plateText);
    }

    private View.OnClickListener mediaClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            if (id == clickMediaPlayerId) {
                Message message = new Message();
                message.what = CameraActivity.SelectVedio;
                message.arg1 = VedioSetVeiw.this.getId();
                parentHandler.sendMessage(message);

            } else if (id == R.id.imageView_start) {
                Message message = new Message();
                if (playFlag)
                    message.what = CameraActivity.StopVedio;
                else
                    message.what = CameraActivity.StartVedio;
                message.arg1 = VedioSetVeiw.this.getId();
                parentHandler.sendMessage(message);

            } else if (id == R.id.imageView_stop) {
                Toast.makeText(VedioSetVeiw.this.getContext(), "ֹͣ", Toast.LENGTH_SHORT).show();

            } else if (id == R.id.imageView_config) {
                Message message = new Message();
                message.what = CameraActivity.ConfigDeivce;
                message.arg1 = VedioSetVeiw.this.getId();
                parentHandler.sendMessage(message);

            }
        }

    };

    @Override
    protected void onDraw(Canvas canvas) {
        if (this.getVisibility() == View.VISIBLE) {
            if (colornum != 0) {
                canvas.drawColor(Color.RED);
            }
        }
    }


    private Timer timer = new Timer();

    private MyTimeTask task = null;

    private class MyTimeTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }

    }

    private DisplayTask timetask = null;

    private class DisplayTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 2;
            handler.sendMessage(message);
        }

    }

    public void sethandle(Handler handle) {
        parentHandler = handle;
    }

    public void Select() {

        DisplayButton();

        colornum = 1;

        VedioSetVeiw.this.invalidate();
    }


    public void unSelect() {
        undisplayButton();

        colornum = 0;
        VedioSetVeiw.this.invalidate();

    }


    public void ZoomOutVedio() {
//        plateImageView.setVisibility(View.VISIBLE);
//        trriglePlate.setVisibility(View.VISIBLE);
    }

    public void ZoomInVedio() {

        plateImageView.setVisibility(View.GONE);
        trriglePlate.setVisibility(View.GONE);
    }

    public void ZoomOutImage() {
//        vedioLayout.setVisibility(View.VISIBLE);
//        trriglePlate.setVisibility(View.VISIBLE);
    }

    public void ZoomInImage() {
        vedioLayout.setVisibility(View.GONE);
        trriglePlate.setVisibility(View.GONE);
    }


    private void DisplayButton() {
        if (!buttonVisible) {
            startView.setVisibility(View.VISIBLE);
            //stopView.setVisibility(View.VISIBLE);
            configView.setVisibility(View.VISIBLE);

            try {
                task = new MyTimeTask();

                timer.schedule(task, 2000);
            } catch (Exception e) {
                int a;
                a = 0;
            }


            buttonVisible = true;
        }
    }

    private void undisplayButton() {

        startView.setVisibility(View.INVISIBLE);
        //   stopView.setVisibility(View.INVISIBLE);
        configView.setVisibility(View.INVISIBLE);

        if (task != null) {
            task.cancel();
            task = null;
        }

        buttonVisible = false;
    }

    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    undisplayButton();
                    break;
                case 2: {
                    FrameTime.setText("frameTime:" + String.valueOf(mediaPlayer.recvFrameTime));
                    PicTime.setText("sdfsdf");

                    timetask = new DisplayTask();
                    timer.schedule(timetask, 2000);

                }
                break;
                case LONG_TIME_NO_DATA:
                    StopPlay();
                    StartPlay();

                    break;
            }

            super.handleMessage(msg);
        }

    };


    public interface OnDoubleClickListener {
        public void OnSingleClick(View v);

        public void OnDoubleClick(View v);
    }

    public static void registerDoubleClickListener(View view, final OnDoubleClickListener listener) {
        if (listener == null) return;
        view.setOnClickListener(new View.OnClickListener() {
            private static final int DOUBLE_CLICK_TIME = 350;        //˫�����ʱ��350����
            private boolean waitDouble = true;

            private Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    listener.OnSingleClick((View) msg.obj);
                }

            };

            //�ȴ�˫��
            public void onClick(final View v) {
                if (waitDouble) {
                    waitDouble = false;        //��ִ��˫���¼�
                    new Thread() {

                        public void run() {
                            try {
                                Thread.sleep(DOUBLE_CLICK_TIME);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }    //�ȴ�˫��ʱ�䣬����ִ�е����¼�
                            if (!waitDouble) {
                                //������˵ȴ��¼�����Ԥִ��˫��״̬������Ϊ����
                                waitDouble = true;
                                Message msg = handler.obtainMessage();
                                msg.obj = v;
                                handler.sendMessage(msg);
                            }
                        }

                    }.start();
                } else {
                    waitDouble = true;
                    listener.OnDoubleClick(v);    //ִ��˫��
                }
            }
        });

    }

    private OnDoubleClick mediaOnDoubleClick = new OnDoubleClick();

    public class OnDoubleClick implements OnDoubleClickListener {
        public void OnSingleClick(View v) {
            Message message = new Message();
            message.what = CameraActivity.SelectVedio;
            message.arg1 = VedioSetVeiw.this.getId();

            parentHandler.sendMessage(message);
        }

        public void OnDoubleClick(View v) {
            Message message = new Message();
            message.what = CameraActivity.DClickVedio;
            message.arg1 = VedioSetVeiw.this.getId();

            if (v.getId() == R.id.imageView_snapPlate) {
                message.obj = ViewSetInnerType.Image;
            } else {
                message.obj = ViewSetInnerType.Vedio;
            }


            parentHandler.sendMessage(message);
        }
    }

}