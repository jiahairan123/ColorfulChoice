package com.example.dllo.colorfulchoice.video;

import android.os.Handler;
import android.os.Message;

/**
 * Created by ${马庭凯} on 16/9/18.
 */

public class JudgeCollectChange {
    private static JudgeCollectChange judgeCollectChange = null;
    private JudgeCollectChange() {}

    public static JudgeCollectChange getInstance() {
        if (judgeCollectChange == null) {
            synchronized (JudgeCollectChange.class) {
                if (judgeCollectChange == null) {
                    judgeCollectChange = new JudgeCollectChange();
                }
            }
        }
        return judgeCollectChange;
    }

    public void judgeCollectExit(final String id, final Handler mHandler) {
        if(CollectBean.getInstance().getResultBeanList().size() == 0){
            mHandler.sendEmptyMessage(0);
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (CollectBean.getInstance().getResultBeanList().size() > 0) {
                        int i = 0;
                        for (; i < CollectBean.getInstance().getResultBeanList().size(); i++) {
                            if (CollectBean.getInstance().getResultBeanList().get(i).getItemId().equals(id)) {
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = i;
                                mHandler.sendMessage(msg);
                                break;
                            }
                        }
                        if (i == CollectBean.getInstance().getResultBeanList().size()) {
                            Message msg = new Message();
                            msg.what = 0;
                            mHandler.sendMessage(msg);
                        }
                    }
                }
            }).start();
        }
    }
}
