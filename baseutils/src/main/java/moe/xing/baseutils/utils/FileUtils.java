package moe.xing.baseutils.utils;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

import moe.xing.baseutils.Init;
import moe.xing.baseutils.R;

/**
 * Created by Qi xingchen on 2016/7/26 0026.
 * <p>
 * 文件相关帮助类
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class FileUtils {

    /**
     * 获取新的缓存文件(优先外部)
     *
     * @param name 文件名
     * @return 文件
     * @throws IOException 文件无法创建或者名称对应的不是文件
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @NonNull
    public static File getCacheFile(@NonNull String name) throws IOException {
        File cacheFile = new File(getCacheDir(), name);
        if (!cacheFile.getParentFile().exists()) {
            cacheFile.getParentFile().mkdirs();
        }

        cacheFile.createNewFile();
        if (!cacheFile.exists() || !cacheFile.isFile()) {
            throw new IOException(Init.getApplication().getString(R.string.error_in_make_file));
        }
        return cacheFile;
    }

    /**
     * 获取缓存文件夹下的子文件夹
     *
     * @param name 文件夹名
     * @return 文件夹
     * @throws IOException 文件夹无法创建
     */
    public static File getCacheDir(@NonNull String name) throws IOException {
        File cacheFile = new File(getCacheDir(), name);
        if (!cacheFile.exists()) {
            if (!cacheFile.mkdirs()) {
                throw new IOException(Init.getApplication().getString(R.string.error_in_make_dir));
            }
        }
        return cacheFile;
    }

    /**
     * 外置储存区是否存在
     *
     * @return <code>true</code>存在
     * <code>false</code>不存在
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * 获取缓存文件夹(总文件夹)
     *
     * @return 缓存文件夹(优先外置)
     */
    @NonNull
    public static File getCacheDir() {
        if (isExternalStorageWritable()) {
            //noinspection ConstantConditions
            return Init.getApplication().getExternalCacheDir();
        } else {
            return Init.getApplication().getCacheDir();
        }
    }

    /**
     * 获取数据文件夹(优先外部)
     *
     * @return 文件夹
     */
    @NonNull
    public static File getDataDir() {
        if (isExternalStorageWritable()) {
            //noinspection ConstantConditions
            return Init.getApplication().getExternalFilesDir(null);
        } else {
            return Init.getApplication().getFilesDir();
        }
    }

    /**
     * 获取新的数据文件(优先外部)
     *
     * @param name 文件名
     * @return 文件
     * @throws IOException 文件无法创建或者名称对应的不是文件
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @NonNull
    public static File getDataFile(@NonNull String name) throws IOException {
        File dataFile = new File(getDataDir(), name);
        if (!dataFile.getParentFile().exists()) {
            dataFile.getParentFile().mkdirs();
        }

        dataFile.createNewFile();
        if (!dataFile.exists() || !dataFile.isFile()) {
            throw new IOException(Init.getApplication().getString(R.string.error_in_make_file));
        }
        return dataFile;
    }

    /**
     * 复制文件
     *
     * @param src 源文夹
     * @param dst 复制到的文件
     * @throws IOException 复制错误
     */
    @WorkerThread
    public static void CopyFile(File src, File dst) throws IOException {
        FileInputStream inStream = new FileInputStream(src);
        CopyFile(inStream, dst);
    }

    /**
     * 复制文件
     *
     * @param src 源文夹
     * @param dst 复制到的文件
     * @throws IOException 复制错误
     */
    @WorkerThread
    public static void CopyFile(FileDescriptor src, File dst) throws IOException {
        FileInputStream inStream = new FileInputStream(src);
        CopyFile(inStream, dst);
    }

    /**
     * 复制文件
     *
     * @param inStream 源文夹流
     * @param dst      复制到的文件
     * @throws IOException 复制错误
     */
    @WorkerThread
    public static void CopyFile(FileInputStream inStream, File dst) throws IOException {
        FileOutputStream outStream = new FileOutputStream(dst);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();
    }

    /**
     * 将 Asset 文件拷贝到缓存
     *
     * @param filename 文件名
     * @return 被储存的文件
     * @throws IOException 文件无法创建或者名称对应的不是文件,assert 无法打开,写入无法终止等
     */
    @WorkerThread
    @NonNull
    protected static File copyAssetFile(@NonNull String filename) throws IOException {
        AssetManager assetManager = Init.getApplication().getAssets();

        File dst = getCacheFile(filename);


        InputStream in = assetManager.open(filename);
        OutputStream out = new FileOutputStream(dst);

        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.flush();
        out.close();

        return dst;
    }

    /**
     * 将 stream 转换为 string
     */
    protected static String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    /**
     * 将 string 写入 FOS
     */
    protected static void writeToFile(String data, FileOutputStream fos) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
        outputStreamWriter.write(data);
        outputStreamWriter.close();
    }

    /**
     * 从 Url 获取文件名
     * 移除 ? 后的所有内容
     * 将 % 转换为 _
     *
     * @param url 文件的 Url
     * @return 文件名
     */
    @NonNull
    public static String getFileNameFromUrl(@NonNull String url) {
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }
        int lastBackslash = url.lastIndexOf("/");
        if (lastBackslash == -1 || lastBackslash >= url.length() + 1) {
            return "noName";
        }
        url = url.substring(url.lastIndexOf("/") + 1).replace("%", "_");

        return url;
    }

    /**
     * 从 Uri 查询文件名
     *
     * @param uri 文件的 Uri
     * @return 文件名
     */
    public static String getFileNameFromUri(@NonNull Uri uri) {
        Cursor cursor = Init.getApplication().getContentResolver()
                .query(uri, null, null, null, null, null);
        try {
            // moveToFirst() returns false if the cursor has 0 rows.  Very handy for
            // "if there's anything to look at, look at it" conditionals.
            if (cursor != null && cursor.moveToFirst()) {

                // Note it's called "Display Name".  This is
                // provider-specific, and might not necessarily be the file name.
                return cursor.getString(
                        cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        return "noname";
    }

    /**
     * 将 bitmap 转为文件
     *
     * @param bitmap 需要转换的图片
     * @return 转换得到的文件
     * @throws IOException 转换失败
     */
    public static File bitmapToFile(@NonNull Bitmap bitmap, @NonNull File file) throws IOException {
        FileOutputStream fOut = new FileOutputStream(file);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fOut);
        fOut.flush();
        fOut.close();
        return file;
    }

    /**
     * 通知系统新图片储存
     *
     * @param file 新储存的图片
     */
    public static void notifyImageSaved(@NonNull File file) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        Init.getApplication().sendBroadcast(mediaScanIntent);
    }
}
