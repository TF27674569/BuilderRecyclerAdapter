package org.recycler.base;

import org.recycler.ViewHolder;

/**
 * Description :
 * <p/>
 * Created : TIAN FENG
 * Date : 2018/4/15
 * Email : 27674569@qq.com
 * Version : 1.0
 */
public interface Binder<T> {

    void bind(ViewHolder viewHolder, T item, int position);
}
