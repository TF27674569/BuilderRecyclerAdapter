package org.recycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.recycler.base.Binder;
import org.recycler.base.ImageLoader;
import org.recycler.base.MultiTypeSupport;
import org.recycler.base.OnClickListener;
import org.recycler.base.OnLongClickListener;

import java.util.List;

/**
 * Description :
 * <p/>
 * Created : TIAN FENG
 * Date : 2018/4/15
 * Email : 27674569@qq.com
 * Version : 1.0
 */
public class EAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    /**
     * 兼容图片框架
     */
    public static void setImageLoader(ImageLoader imageLoader){
        ViewHolder.sImageLoader = imageLoader;
    }

    private Builder.Params<T> P;

    private EAdapter(Builder.Params<T> params) {
        P = params;

        if (P.mManager==null){
            P.mManager = new LinearLayoutManager(P.mRecyclerView.getContext());
        }

        P.mRecyclerView.setLayoutManager( P.mManager);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 存在View 以贴膜为准
        if (P.mItemView != null) {
            return new ViewHolder(P.mRecyclerView.getContext(),P.mItemView);
        }

        // 存在多布局时 以多布局为准
        if (P.mSupport != null) {
            View itemView = LayoutInflater.from(P.mRecyclerView.getContext()).inflate(viewType, parent, false);
            return new ViewHolder(P.mRecyclerView.getContext(),itemView);
        }

        if (P.mLayoutId != -1) {
            View itemView = LayoutInflater.from(P.mRecyclerView.getContext()).inflate(P.mLayoutId, parent, false);
            return new ViewHolder(P.mRecyclerView.getContext(),itemView);
        }

        throw new NullPointerException("您是否设置了item的布局");
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 数据绑定回调
        if (P.mBinder != null) {
            T item = P.mData == null ? null : P.mData.get(position);
            P.mBinder.bind(holder, item, position);
        }

        // 条目点击回调
        if (P.mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    P.mClickListener.onClick(v, position);
                }
            });
        }

        // 条目长按回调
        if (P.mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return P.mLongClickListener.onLongClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return P.mData == null ? 0 : P.mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        T item = P.mData == null ? null : P.mData.get(position);
        return P.mSupport == null ? 0 : P.mSupport.getLayoutId(item, position);
    }

    public static class Builder {
        private Params P;

        private Builder(List<?> data) {
            P = new Params<>(data);
        }

        /**
         * 数据
         */
        public static <T> Builder load(List<T> data) {
            return new Builder(data);
        }

        /**
         * item的Id
         */
        public Builder item(int layoutId) {
            P.mLayoutId = layoutId;
            return this;
        }

        /**
         * 条目view
         */
        public Builder item(View itemView) {
            P.mItemView = itemView;
            return this;
        }

        /**
         * 数据绑定回调
         */
        public <T> Builder bind(Binder<T> binder) {
            P.mBinder = binder;
            return this;
        }

        /**
         * 多条目适配
         */
        public <T> Builder support(MultiTypeSupport<T> support) {
            P.mSupport = support;
            return this;
        }

        /**
         * 条目点击
         */
        public Builder onItemClick(OnClickListener listener) {
            P.mClickListener = listener;
            return this;
        }

        /**
         * manager
         */
        public Builder manager(RecyclerView.LayoutManager layoutManager) {
            P.mManager = layoutManager;
            return this;
        }

        /**
         * 条目长按
         */
        public Builder onItemLongClick(OnLongClickListener listener) {
            P.mLongClickListener = listener;
            return this;
        }

        /**
         * 设置target
         */
        public EAdapter into(RecyclerView target) {
            P.mRecyclerView = target;
            return new EAdapter<>(P);
        }


        private static class Params<T> {
            private List<T> mData;
            private int mLayoutId = -1;
            private View mItemView;
            private Binder<T> mBinder;
            private MultiTypeSupport<T> mSupport;
            private RecyclerView mRecyclerView;
            private OnClickListener mClickListener;
            private OnLongClickListener mLongClickListener;
            private RecyclerView.LayoutManager mManager;

            private Params(List<T> data) {
                mData = data;
            }
        }


    }
}
