package com.kp.monitor.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.kp.monitor.R;


/**
 * Created by ${Stephen} on 2017-05-10.
 */


public class FunctionGridView extends GridView {

    private static final java.lang.String TAG = FunctionGridView.class.getSimpleName();

    Paint localPaint;//画笔

    public FunctionGridView(Context context) {
        super(context);
    }

    public FunctionGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FunctionGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        int measureHeight = measureHeight(heightMeasureSpec);
        ViewGroup.LayoutParams params = getLayoutParams();
        setLayoutParams(params);
        params.height = measureHeight / 2;
        setLayoutParams(params);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    //根据xml的设定获取高度
    private int measureHeight(int measureSpec) {
        int specSize = MeasureSpec.getSize(measureSpec);

        return specSize;
    }



    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        View localView1 = getChildAt(0);
        int column = getWidth() / localView1.getWidth();//计算出一共有多少列，假设有3列
        int childCount = getChildCount();//子view的总数
        if (null != localPaint) localPaint = null;
        localPaint = new Paint();
        localPaint.setStyle(Paint.Style.STROKE);
//        localPaint.setColor(getContext().getResources().getColor(R.color.colorLightGray));//设置画笔的颜色
        localPaint.setColor(getContext().getResources().getColor(R.color.colorBgBlue));//设置画笔的颜色

        //  这里 最右边的竖线 最左边的竖线  最下面的横线 没画  对于3列满足 对于多列是否满足未经测试

        for (int i = 0; i < childCount; i++) {//遍历子view
            View cellView = getChildAt(i);//获取子view
            if (i < 3) {//第一行
                canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(),cellView.getTop(), localPaint);
            }
            if ((i + 1) % column == 0) {//第三列

                //画子view底部横线
                if(i != (childCount - 1)){  // 最后面view底部横线不要
                    canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(),cellView.getBottom(), localPaint); // 横线
                }

                // 最右边的竖线去掉
//                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(),cellView.getBottom(), localPaint);  // 竖线
            }else if( ((i+1) < childCount) && ((childCount - column) < (i+1))){ // 最后一行的view  除了最后一个
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint); // 竖线
            } else {//如果view不是最后一行
                //画子view的右边竖线
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(),cellView.getBottom(), localPaint);
                //画子view的底部横线
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(),cellView.getBottom(), localPaint);
            }
        }
    }
}