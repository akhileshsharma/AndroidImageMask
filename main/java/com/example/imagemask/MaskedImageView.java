package com.example.imagemask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class MaskedImageView extends AppCompatImageView {

    private Context context;

    private Bitmap bitmapBorder;
    private Bitmap bitmapMask;
    private Paint paint;
    private PorterDuffXfermode xfermode;
    private int TYPE=0;

    private Bitmap bitmap;

    private int _width;
    private int _height;

    public MaskedImageView(Context context,int TYPE) {
        this(context, null);
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;

        switch (TYPE) {
            case 1:
                bitmapBorder = decodeBitmap(R.drawable.mask);
                bitmapMask = decodeBitmap(R.drawable.mask);
                break;
            case 2:
                bitmapBorder = decodeBitmap(R.drawable.mask2);
                bitmapMask = decodeBitmap(R.drawable.mask2);
                break;
            case 3:
                bitmapBorder = decodeBitmap(R.drawable.mask3);
                bitmapMask = decodeBitmap(R.drawable.mask3);
                break;
            case 4:
                bitmapBorder = decodeBitmap(R.drawable.mask4);
                bitmapMask = decodeBitmap(R.drawable.mask4);
                break;
            default:
                bitmapBorder = decodeBitmap(R.drawable.mask);
                bitmapMask = decodeBitmap(R.drawable.mask);
                break;
        }


        _width = bitmapBorder.getWidth();
        _height = bitmapBorder.getHeight();
    }

    public MaskedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;



        paint = new Paint();
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(_width, _height);
    }

    private Bitmap decodeBitmap(int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    public void setImageResource(int resourseId) {
        bitmap = decodeBitmap(resourseId);
        invalidate();
    }

    public void setResourseBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap == null) {
            return;
        }
        canvas.drawBitmap(bitmapBorder, 0, 0, paint);
        int saveFlags = Canvas.ALL_SAVE_FLAG;
        canvas.saveLayer(0, 0, _width, _height, null, saveFlags);
        canvas.drawBitmap(bitmapMask, 0, 0, paint);
        paint.setXfermode(xfermode);
        int left = _width / 2 - bitmap.getWidth() / 2;
        int top = _height / 2 - bitmap.getHeight() / 2;
        canvas.drawBitmap(bitmap, left, top, paint);
        paint.setXfermode(null);
        canvas.restore();
    }

}
