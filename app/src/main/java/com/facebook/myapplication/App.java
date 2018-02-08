package com.facebook.myapplication;

import android.app.Application;
import android.content.Intent;

import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.TtsMode;
import com.facebook.myapplication.service.BadgeService;
import com.facebook.myapplication.service.MyBaiduTTListener;

import cn.jpush.android.api.JPushInterface;

/**
 * 类名: App
 * 此类用途: ---
 *
 * @Author: peixi
 * @Date: 2018-02-01 11:41
 * @Email: cr7inmanchester.com
 * @FileName: com.facebook.myapplication.App.java
 */
public class App extends Application {
    public static int badgeCount = 0;
    // ================== 初始化参数设置开始 ==========================
    /**
     * 发布时请替换成自己申请的appId appKey 和 secretKey。注意如果需要离线合成功能,请在您申请的应用中填写包名。
     * 本demo的包名是com.baidu.tts.sample，定义在build.gradle中。
     */
    protected String appId = "10802203";

    protected String appKey = "bpGp49DavxAiXcCiDoNcZzYp";

    protected String secretKey = "tPtLNu96FtYbsKWAyZGIE9hYoZ5DDqxx";

    // TtsMode.MIX; 离在线融合，在线优先； TtsMode.ONLINE 纯在线； 没有纯离线
    private TtsMode ttsMode = TtsMode.ONLINE;

    // ================选择TtsMode.ONLINE  不需要设置以下参数; 选择TtsMode.MIX 需要设置下面2个离线资源文件的路径
    private static final String TEMP_DIR = "/sdcard/baiduTTS"; // 重要！请手动将assets目录下的3个dat 文件复制到该目录

    // 请确保该PATH下有这个文件
    private static final String TEXT_FILENAME = TEMP_DIR + "/" + "bd_etts_text.dat";

    // 请确保该PATH下有这个文件 ，m15是离线男声
    private static final String MODEL_FILENAME =
            TEMP_DIR + "/" + "bd_etts_common_speech_m15_mand_eng_high_am-mix_v3.0.0_20170505.dat";

    // ===============初始化参数设置完毕，更多合成参数请至getParams()方法中设置 =================
    public static SpeechSynthesizer mSpeechSynthesizer;

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        startService(new Intent(this, BadgeService.class));

        // 1. 获取实例
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(this);

        // 2. 设置listener
        mSpeechSynthesizer.setSpeechSynthesizerListener(new MyBaiduTTListener());

        // 3. 设置appId，appKey.secretKey
        mSpeechSynthesizer.setAppId(appId);
//        checkResult(result, "setAppId");
        mSpeechSynthesizer.setApiKey(appKey, secretKey);
//        checkResult(result, "setApiKey");
        //设置语音播报的模式
        mSpeechSynthesizer.initTts(ttsMode);


    }



}
