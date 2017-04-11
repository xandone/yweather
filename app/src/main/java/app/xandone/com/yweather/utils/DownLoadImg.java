package app.xandone.com.yweather.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import app.xandone.com.yweather.BaseApplication;
import app.xandone.com.yweather.config.Config;
import app.xandone.com.yweather.interf.DownLoadImgInterf;

/**
 * Created by xandone on 2017/1/19.
 */
public class DownLoadImg implements Runnable {
    private String url;
    private RequestManager requestManager;
    private DownLoadImgInterf downLoadImgInterf;
    private File currentFile;
    private String imgName;

    public DownLoadImg(String url, DownLoadImgInterf downLoadImgInterf) {
        this.url = url;
        this.downLoadImgInterf = downLoadImgInterf;
        requestManager = Glide.with(BaseApplication.sContext);
    }

    @Override
    public void run() {
        Bitmap bitmap = null;
        try {
            bitmap = requestManager
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            if (bitmap != null) {
                saveToDir(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null && currentFile.exists()) {
                BaseApplication.sContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(new File(currentFile.getPath()))));
                downLoadImgInterf.saveSuccess(imgName);
            } else {
                BaseApplication.sContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(new File(currentFile.getPath()))));
                downLoadImgInterf.saveFail();
            }
        }
    }

    public void saveToDir(Bitmap bmp) {
        File imgDir = new File(Config.DEFAULT_SAVE_IMG_PATH, Config.APP_NAME);
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }
        String name = System.currentTimeMillis() + ".jpg";
        currentFile = new File(imgDir, name);
        imgName = currentFile.getPath();
        Log.d("xandone", imgName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (Exception e) {

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
