package com.akuya.gunactivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;

import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class gun extends ViewPager {
    /**
     * 定时器
     */
    private Timer timer;

    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.arg1 == 1){
                //向右滑动
                arrowScroll(FOCUS_RIGHT);
            }

            if(msg.arg1 == 2){
                //回滚至第一页
                setCurrentItem(0);
            }
        };
    };

    /**
     * 开始自动滚动
     * @param delay 等待时间
     * @param period 间隔时间
     */
    public synchronized void startAutoScroll(long delay, long period){
        timer = new Timer();
        timer.schedule(new MyTask(), delay, period);
    }
    /**
     * 停止自动滚动
     */
    public synchronized void stopAutoScroll(){
        timer.cancel();
    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            scrollAction();
        }
    }

    /**
     * 自动滚动
     */
    private void scrollAction(){
        //获取当前页码
        int pageNo = getCurrentItem();
        //获取总页码
        int pageCount = getAdapter().getCount() - 1;

        Message msg = new Message();

        if(pageNo < pageCount){
            msg.arg1 = 1;
        }else if(pageNo == pageCount){
            msg.arg1 = 2;
        }

        handler.sendMessage(msg);
    }

}
