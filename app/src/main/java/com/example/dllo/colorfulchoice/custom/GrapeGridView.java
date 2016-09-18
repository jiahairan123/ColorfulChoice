package com.example.dllo.colorfulchoice.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created BaiDuMusic by *Vampire* on 16/8/16.
 */
public class GrapeGridView extends GridView {
    private static final String TAG = "Vampire_GrapeGridview";

    public GrapeGridView(Context context) {
        super(context);
// TODO Auto-generated constructor stub
    }
    public GrapeGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
// TODO Auto-generated constructor stub
    }

    public GrapeGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
// TODO Auto-generated constructor stub
    }

    //通过重新dispatchTouchEvent方法来禁止滑动
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
// TODO Auto-generated method stub
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            return true;//禁止Gridview进行滑动
        }
        return super.dispatchTouchEvent(ev);
    }
    
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
