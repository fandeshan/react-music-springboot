package com.example.demo;

import com.example.demo.controller.*;
import com.example.demo.service2.builder.exception.ResponseMapperException;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    GlobalController globalController;

    @Autowired
    LyricController lyricController;

    @Autowired
    MusicController musicController;

    @Autowired
    PlayListController playListController;

    @Autowired
    DiscoverImageController discoverImageController;

    @Autowired
    TopListController topListController;

    @Test
    public void test1() throws IOException, ScriptException, ResponseMapperException {
        System.out.println(musicController.music("1363948882"));
    }


    @Test
    public void test2() throws IOException, ScriptException, ResponseMapperException {
        System.out.println(lyricController.lyric("1363948882"));
    }

    @Test
    public void test3() throws IOException, ResponseMapperException {
        GlobalController.RequestGlobal requestGlobal = new GlobalController.RequestGlobal();
        requestGlobal.setUrl("https://music.163.com/weapi/song/enhance/player/url/v1?csrf_token=");
        requestGlobal.setEncode("params=JI6pXECf7VERwdJMCoJat7qwE69wZk6wrdkmBbH5Njce0qhcew5Zc1IKgpIBug30zGqlranOlTYQtoKQ3LM1knlhw8637fhzsQb9eNO%2Bw%2BjtzqM5CM%2FDUdZF%2FwnzzR96&encSecKey=adb2ee2e6ecd644cce57e58a7b4b6fc61e29acee8c8638f76447e6701f8f34c3603fdfff72fbd5ed8df767e1e87a3120aceafcf81b2147f12335d3927179169e2c69f7ebd471391774fbc54805a9e5f2fe96c69c2639c82ca9e504e9a3ca869b3b8294e00a12aa88ed3a85c304cbd3efaa72eb2b9ff33ac70babed7822c99d81");
        System.out.println(globalController.global(requestGlobal));
    }


    @Test
    public void test4() throws IOException, ResponseMapperException {
        System.out.println(topListController.playlist());

    }

    @Test
    public void test5() throws IOException, ResponseMapperException {
        System.out.println(discoverImageController.image());
    }

    @Test
    public void test6() throws IOException, ScriptException, ResponseMapperException {
        JSONObject jsonObject = JSONObject.fromObject(getMusicJsonStr("C:\\Users\\fande\\Desktop\\music.json"));
        //System.out.println(jsonObject.toString());
        JSONObject netplays = jsonObject.getJSONObject("myplaylist_1b28664d-3a5c-40a7-ee93-e450a21b71c2");
        JSONArray tracks = netplays.getJSONArray("tracks");
        StringBuffer ids = new StringBuffer();

        for (int i = 0; i < tracks.size(); i++) {
            ids.append(tracks.getJSONObject(i).getString("id").replace("netrack_","")).append(",");
            if (i > 3){
                break;
            }
        }
        System.out.println(ids.toString().substring(0,ids.length() -1));
        //"myplaylist_1b28664d-3a5c-40a7-ee93-e450a21b71c2 -> {JSONObject@9246}  size = 3"
        JSONObject music163Return = JSONObject.fromObject(musicController.music(ids.toString().substring(0, ids.length() - 1)).toString());
        writeJsonToFile(music163Return.toString(),"C:\\Users\\fande\\Desktop\\music_163return.json");
        System.out.println();
    }

    @Test
    public void test7() throws IOException, ScriptException, ResponseMapperException {
        String musicJsonStr = getMusicJsonStr("C:\\Users\\fande\\Desktop\\music_163return.json");
        JSONObject jsonObject = JSONObject.fromObject(musicJsonStr);
        JSONObject dataJson = jsonObject.getJSONObject("data");
        JSONArray dataJsons2 = dataJson.getJSONArray("data");
        StringBuffer html= new StringBuffer("<html><body>");
        for (int i = 0; i < dataJsons2.size(); i++) {
            String url = dataJsons2.getJSONObject(i).getString("url");
            String id = dataJsons2.getJSONObject(i).getString("id");
            html.append("<a href=\""+url+"\" >"+id+"</a><br/>");
        }
        html.append("</html></body>");
        writeJsonToFile(html.toString(),"C:\\Users\\fande\\Desktop\\music.html");
    }


    @Test
    public void test8() throws Exception{
        Thread thread = getThread("http://m801.music.126.net/20221118160510/ca06065b5765331f9e39c9cb729fcdb3/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/7100047136/a44e/a0a3/f96c/1da7b5daae45b4664f27490f533c0c08.mp3?authSecret=000001848997fefa18440aaba37304db","E:\\CloudMusic\\222.mp3");
        thread.start();
        thread.join();
    }

    @Test
    public void test9() throws Exception{
        File destFile = new File("");
        File tempFile = new File(destFile.getParent()+ File.separator + "temp_"+destFile.getName());
        FileUtils.copyFile(destFile,tempFile);
        Mp3File mp3File = new Mp3File(tempFile.getAbsolutePath());
        ID3v2 tag = mp3File.getId3v2Tag();

    }

    private static String getMusicJsonStr(String filePath){
        File file = new File(filePath);
        BufferedReader br = null;
        InputStreamReader isr = null;
        StringBuffer sb = new StringBuffer();
        try {
            isr = new InputStreamReader(new FileInputStream(file),"utf-8");
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine())!= null){
                sb.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("数据文件出错");
        }finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr!= null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    private static void writeJsonToFile(String str,String filePath){
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(filePath)));
            pw.println(str);
            pw.close();
        }catch (Exception e){

        }

    }
    //默认文件每次读取字节数
    public static final int BYTE_COUNT = 40960;

    //重试次数
    private int retryCount = 30;

    //优化内存占用
    private static final BlockingQueue<byte[]> BLOCKING_QUEUE = new LinkedBlockingQueue<>();

    //链接连接超时时间（单位：毫秒）
    private long timeoutMillisecond = 1000L;


    private Thread getThread(String urls,String filePath) {
        return new Thread(() -> {
            int count = 1;
            HttpURLConnection httpURLConnection = null;
            OutputStream outputStream = null;
            InputStream inputStream1 = null;
            FileOutputStream outputStream1 = null;
            byte[] bytes;
//            try {
//                bytes = BLOCKING_QUEUE.take();
//            } catch (InterruptedException e) {
//                bytes = new byte[BYTE_COUNT];
//            }
            bytes = new byte[BYTE_COUNT];
            //重试次数判断
            while (count <= retryCount) {
                try {
                    //模拟http请求获取ts片段文件
                    URL url = new URL(urls);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout((int) timeoutMillisecond);

                    //httpURLConnection.setRequestProperty("authSecret", "000001848997fefa18440aaba37304db");
                    httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
                    //httpURLConnection.setRequestProperty("cookie", "000001848997fefa18440aaba37304db");
//                    for (Map.Entry<String, Object> entry : requestHeaderMap.entrySet())
//                        httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue().toString());
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setReadTimeout((int) timeoutMillisecond);
                    httpURLConnection.setDoInput(true);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    try {
                        outputStream = new FileOutputStream(filePath);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        continue;
                    }
                    int len;
                    while ((len = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
//                        synchronized (this) {
//                            downloadBytes = downloadBytes.add(new BigDecimal(len));
//                        }
                    }
                    outputStream.flush();
                    inputStream.close();
//                    inputStream1 = new FileInputStream(filePath);
//                    int available = inputStream1.available();
//                    if (bytes.length < available)
//                        bytes = new byte[available]   ;
//                    inputStream1.read(bytes);
//                    File file = new File(dir + FILESEPARATOR + i + ".xyz");
//                    outputStream1 = new FileOutputStream(file);
//                    //开始解密ts片段，这里我们把ts后缀改为了xyz，改不改都一样
//                    byte[] decrypt = decrypt(bytes, available, key, iv, method);
//                    if (decrypt == null)
//                        outputStream1.write(bytes, 0, available);
//                    else outputStream1.write(decrypt);
//                    //finishedFiles.add(file);
                    break;
                } catch (Exception e) {
                    if (e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException) {
                        //Log.e("解密失败！");
                        System.out.println("解密失败！");
                        break;
                    }
                    System.out.println("第" + count + "获取链接重试！\t"+ urls);
                    //Log.d("第" + count + "获取链接重试！\t" + urls);
                    count++;
//                        e.printStackTrace();
                } finally {
                    try {
                        if (inputStream1 != null)
                            inputStream1.close();
                        if (outputStream1 != null)
                            outputStream1.close();
                        if (outputStream != null)
                            outputStream.close();
                        BLOCKING_QUEUE.put(bytes);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
            if (count > retryCount)
                //自定义异常
                throw new IllegalArgumentException("连接超时！");
            //finishedCount++;
//                Log.i(urls + "下载完毕！\t已完成" + finishedCount + "个，还剩" + (tsSet.size() - finishedCount) + "个！");
        });
    }
}
