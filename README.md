#RecyclerView万能适配器

##依赖
`
compile 'com.tianfeng:recycleradapter:1.0.0'
`

## 如果出现异常
![找不到库文件](https://github.com/TF27674569/RxPhoto/blob/master/app/image/%E5%BC%82%E5%B8%B8.png)

在项目根目录的build.gradle中添加</br>
```java
  repositories {
        jcenter()
        ...
        maven {
            url  "https://dl.bintray.com/tf27674569/maven"
        }

    }
```

##使用
1.加载网络图片请调用函数
```java
EAdapter.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, ImageView imageView, String url) {
                // 此处是你使用的库文件
            }
        });

```

2.适配器的使用
```java
EAdapter.Builder.load(strings)
                .item(R.layout.activity_main)
                .bind()
                .onItemClick()
                .onItemLongClick()
                .item(View)
                .manager()
                .support()
                .into();
```
3.注意
```java
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

```
存在此判断 参数顺序会以 view support layoutid的顺序









