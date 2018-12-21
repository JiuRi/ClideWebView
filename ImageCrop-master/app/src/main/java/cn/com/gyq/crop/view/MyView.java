package cn.com.gyq.crop.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.com.gyq.crop.R;

public class MyView extends RelativeLayout {

    private ImageView mImageView;
    private WebView mWebView;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View inflate = View.inflate(context, R.layout.myview, this);
        mImageView = (ImageView)inflate. findViewById(R.id.imageview);
        mWebView = (WebView) inflate.findViewById(R.id.webview);
        mWebView.loadUrl("http://www.jikedaohang.com/");
        initWeb2();
    }

    public void setImageBitmap(Bitmap bm) {
        mImageView.setImageBitmap(bm);
    }
    public void setImageResource(int resId) {
        mImageView.setImageResource(resId);
    }
    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public Drawable getDrawable() {
      return  mImageView.getDrawable();
    }
    public Matrix getImageMatrix() {
        return  mImageView.getImageMatrix();
    }
    private void initWeb2() {
        //20181030 三星S8+出现网页文本字体变大，这里设置文本缩放为100%后解决，不知道是不是三星手机
        //根据屏幕密度缩放了网页文本
        mWebView.getSettings().setTextZoom(100);
        mWebView.setInitialScale(150);
        //支持App内部javascript交互
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        if(Build.VERSION.SDK_INT >= 22) {
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }
        //设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true);
        //扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(true);
        //设置是否出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //这个是国外网站Stack Overflow推荐提升加载速度的方式
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        mWebView.setWebChromeClient(new WebChromeClient());
    }
}
