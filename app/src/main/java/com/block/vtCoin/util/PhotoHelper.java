package com.block.vtCoin.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.block.net.util.L;
import com.block.net.util.StringUtils;
import com.block.vtCoin.main.BApplication;
import com.block.vtCoin.constant.Constant;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description
 * @Author liuBin
 * @Date 2017/8/8.
 */
public class PhotoHelper {
    /**
     * 从已有图库中获取相片
     * @param context 上下文对象
     * @param requestCode 请求码
     */
    public static void selectMyPhotoFromGallery(Activity context, int requestCode) {
        Intent _intent = new Intent(Intent.ACTION_PICK);//选择数据
        _intent.setType("image/*");//
        context.startActivityForResult(_intent, requestCode);
    }

    /*从相机新拍相片中获取图片*/
    public static String selectForCamera(Activity context, String pPhotoFile, int requestCode) {

        if (TextUtils.isEmpty(pPhotoFile)) {
            pPhotoFile = generateFileName();
        }
        String mTmpCameraFilePath = pathForNewCameraPhoto(pPhotoFile);
        Uri mCurrentPhotoFileUri = Uri.fromFile(new File(mTmpCameraFilePath));
        try {
            Intent _intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            _intent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoFileUri);
            context.startActivityForResult(_intent, requestCode);
        } catch (ActivityNotFoundException e) {
            ToastUtil.getInstance().showBottomMessage(context, "没有找到相机设备！");
        }
        return mTmpCameraFilePath;
    }

    /*从相机新拍相片中获取图片*/
    public static Uri selectMyPhotoForCamera(Activity context, String pPhotoFile, int requestCode) {

        if (StringUtils.isBlank(pPhotoFile)) {
            pPhotoFile = generateFileName();
        }
        String mTmpCameraFilePath = pathForNewCameraPhoto(pPhotoFile);
        Uri mCurrentPhotoFileUri = Uri.fromFile(new File(mTmpCameraFilePath));
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoFileUri);
            context.startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException e) {
            ToastUtil.getInstance().showBottomMessage(context,"没有找到相机设备！");
        }
        return mCurrentPhotoFileUri;
    }

    /*从相机新拍相片中获取图片*/
    public static Uri selectMyPForCamera(Activity context, String pPhotoFile, int requestCode) {
        Uri mCurrentPhotoFileUri;
        if (StringUtils.isBlank(pPhotoFile)) {
            pPhotoFile = generateFileName();
        }
        String mTmpCameraFilePath = pathForNewCameraPhoto(pPhotoFile);
        /*为了解决听安手机,7.0*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authority = context.getPackageName() + ".fileprovider";
            mCurrentPhotoFileUri = FileProvider.getUriForFile(context, authority, new File(mTmpCameraFilePath));
        } else {
            mCurrentPhotoFileUri = Uri.fromFile(new File(mTmpCameraFilePath));
        }
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoFileUri);
            context.startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException e) {
            ToastUtil.getInstance().showBottomMessage(context,"没有找到相机设备！");
        }
        return mCurrentPhotoFileUri;
    }

    /*根据当前的时间进行生成文件名*/
    private static String generateFileName() {
        return StringUtils.md5(String.valueOf(System.currentTimeMillis())) + ".png";
    }

    /*获取Camera拍摄的照片绝地路径*/
    private static String pathForNewCameraPhoto(String fileName) {
        return pathForNewCameraPhoto(null, fileName);
    }

    private static String pathForNewCameraPhoto(String dir, String fileName) {
        if (dir == null) {
            dir = AppManager.AppPath + "Users/Face/";
        }
        File file = FileUtils.makeFolder(dir);
        File iFile = new File(file.getAbsolutePath(), fileName);
        if (!iFile.exists())
            return iFile.getAbsolutePath();
        else
            return null;
    }

    /**
     * 裁剪图片
     * @param context 上下文对象
     * @param pPhotoUri 原图片的uri路径
     * @param pResultCode 裁剪的请求码
     * @param isXY 是否设置1：1的裁剪框比例
     * @return 裁剪后的图片路径
     */
    public static String doCropPhoto(Activity context, Uri pPhotoUri, int pResultCode, boolean isXY) {
        String mTmpCameraFilePath = pathForNewCameraPhoto(generateFileName());
        Uri mCurrentPhotoFileUri = Uri.fromFile(new File(mTmpCameraFilePath));
        Intent _intent = new Intent("com.android.camera.action.CROP");
        _intent.setDataAndType(pPhotoUri, "image/*");
        _intent.putExtra("crop", "true");
        // 裁剪框的比例 1：1
        if (isXY) {
            _intent.putExtra("aspectX", 1);
            _intent.putExtra("aspectY", 1);
        }
        // 裁剪后输出图片的尺寸大小
        _intent.putExtra("outputX", 300);
        _intent.putExtra("outputY", 300);

        _intent.putExtra("outputFormat", "png");// 图片格式
        _intent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoFileUri);
        context.startActivityForResult(_intent, pResultCode);
        return mTmpCameraFilePath;
    }

    /**
     * 压缩图片
     * @param photoPath 原图片路径
     * @return 压缩后的图片路径
     */
    public static String doCompressPhoto(String photoPath) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            // inJustDecodeBounds设置为true,这样使用该option decode出来的Bitmap是null，
            // 只是把长宽存放到option中
            options.inJustDecodeBounds = true;
            options.inDither = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            // 此时bitmap为null
            BitmapFactory.decodeFile(photoPath,options);
            int inSampleSize = 1; // 1表示不缩放
            // 计算宽高缩放比例
            int inSampleSizeW = options.outWidth / Constant.PHOTOCOMPRESSEDWIDTH;
            int inSampleSizeH = options.outHeight / Constant.PHOTOCOMPRESSEDHEIGHT;
            if (inSampleSizeW > inSampleSizeH) {
                inSampleSize = inSampleSizeW;
            }else {
                inSampleSize = inSampleSizeH;
            }
            // 设置缩放比例
            options.inSampleSize = inSampleSize;
            // 一定要记得将inJustDecodeBounds设为false，否则Bitmap为null
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            while (baos.toByteArray().length / 1024 > Constant.PHOTOCOMPRESSEDSIZE && quality > 6) {
                baos.reset();
                quality -= 6;
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            }
            bitmap.recycle();
//			L.w(String.valueOf(baos.toByteArray().length));

            FileOutputStream fos = new FileOutputStream(photoPath);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return photoPath;
    }

    /**
     * 压缩图片
     */
    public static Bitmap compressImage(Bitmap image, final String uri) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        final Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        File iFile = new File(uri);
        try {
            FileOutputStream out = new FileOutputStream(iFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 按质量进行压缩图片
     * @param image
     * @param uri
     * @return
     */
    public static Bitmap comp(Bitmap image, String uri) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap, uri);// 压缩好比例大小后再进行质量压缩
    }

    /**
     * 按比例进行压缩图片
     * @param image
     * @param uri
     * @param isCache
     * @return
     */
    public static Bitmap compXY(Bitmap image, final String uri, boolean isCache) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        final Bitmap newBitmap = bitmap;
        if (isCache) {
            File iFile = new File(uri);
            try {
                FileOutputStream out = new FileOutputStream(iFile);
                newBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newBitmap;// 压缩好比例大小后再进行质量压缩
    }

    public static Bitmap decodeFile(String path) {
        Bitmap bm = null;
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inDither = false;
        bfOptions.inPurgeable = true;
        bfOptions.inInputShareable = true;
        bfOptions.inTempStorage = new byte[32 * 1024];

        File file = new File(path);
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            if (fs != null)
                bm = BitmapFactory.decodeFileDescriptor(fs.getFD(), null,
                        bfOptions);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }

    /*保存图片到系统图库*/
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "vtcoin");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
        return true;
    }

    public static String UriToImagePath(Context context,Uri uri){
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor =context.getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    public static String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null ) {
            L.i("scheme=null");
            data = uri.getPath();
        }else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {//拍照
            L.i("scheme=file");
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {//从相册中拿
            L.i("scheme=content");
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        L.i(data);
        return data;
    }
}
