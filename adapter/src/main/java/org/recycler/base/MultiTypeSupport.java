package org.recycler.base;

/**
 * Description : 多item适配
 * <p/>
 * Created : TIAN FENG
 * Date : 2018/4/15
 * Email : 27674569@qq.com
 * Version : 1.0
 */
public interface MultiTypeSupport<T> {
    // 根据当前位置或者条目数据返回布局
    int getLayoutId(T item, int position);
}
