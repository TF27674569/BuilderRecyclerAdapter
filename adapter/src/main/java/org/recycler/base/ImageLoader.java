package org.recycler.base;

import android.content.Context;
import android.widget.ImageView;

/**
 * Description :
 * <p/>
 * Created : TIAN FENG
 * Date : 2018/4/15
 * Email : 27674569@qq.com
 * Version : 1.0
 */
public interface ImageLoader {
    /**
     * 设置图片
     */
    void displayImage(Context context, ImageView imageView, String url);
}
