package cn.com.gyq.crop;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import cn.com.gyq.crop.view.CropImageView;

public class MainActivity extends Activity {

    CropImageView cropImageView;
    WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        mWebView = (WebView)findViewById(R.id.webview);
       //cropImageView.setImageResource(R.drawable.timg);
        initWeb2();
        mWebView.loadUrl("http://www.jikedaohang.com/");
        findViewById(R.id.cropOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取裁剪的图片
                Bitmap cropBitMap = cropImageView.getCroppedImage();cropImageView.setImageBitmap(cropBitMap);
            }
        });
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
