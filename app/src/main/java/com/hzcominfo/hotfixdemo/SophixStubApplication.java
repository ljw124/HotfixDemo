package com.hzcominfo.hotfixdemo;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Keep;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * 此类不应与项目中的其他类有任何互相调用的逻辑，必须完全做到隔离。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
public class SophixStubApplication extends SophixApplication {

    private final String TAG = "SophixStubApplication";

    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyApplication.class)
    static class RealApplicationStub {}

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        initSophix();
    }
    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData("30453476", "0bd948bd641e1b4033288171ab4f798e", "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCuupFtyuwcxv8l46ZBJd0SZzk/GR4aR0yRir5cbiJdQ6kDY3X3HQvxRtNto3hCIBmvDawFDDN853th4pR6MrpE41MCKdmHu2wtxNKIi9/f+zSrd9H42d6olCxyb9I1z/A8JdUoFU+zwRspcAWn+g5sYffowlakTOfTf/C1KjZJvLT8QXxGRdpOkssGirltPgMIrr5hxJwELK63ofAExbewNarYxDgx2igISnuxs4iv7iX1oTzNDNBknNq7rcAH8wZLDGoON/zvtc8kpbYDBUt3P8Jrmx8BfGx5+ovmrR5GJKmOMEHRffJFq3ekEc3VdvvXBykKsoM64B61ccTrJb3JAgMBAAECggEAVqYGwJ/E/3Bfz3ZZWC1iuGGgLf5Sw01gVzyqskObnwySoC+OrIEnDvvElbZfSMuKcRkimU43qlVRBnXKk97cKJVi8Mu4KGt1di0JCtXyq6xzriS5Z+MDXC4+7HEvmagIxossbbhD0iyNP7th0q9av7zFqBPXy5qnzGbCA9VvHpk4FwZOb8y6paUsVRjDL/hnL37FZwPUcjZ5snbbfHwLad/MQXANqB5NYxvA63/ZUrXrdy9bLWPeV0o1Estszc+reP2+OzVVofgYI7iT2/NKssJykfPXD69O12I67RJatc6WvDpqx8SoCn9S0w3tB8v423OrA4VKjM5ZGv81+15dEQKBgQDiYZDGCAc9v2rF+IjiBqxVIV/amBJJPOYgrMrsh3VNbDSoX8+Lc1vowB/UKwUm5MdpmAgvlEVu9RdRRLYbt6MiKkSiMKqb33LH1RIzXe/FyNFx8hAfRdUG0P8Zf3K+keMIKmoi/TdwE1dDwOU9DSDTTKs4IdHGy5ita1ObBjzJVwKBgQDFlvQYZ47HhFnBO3bTxYFFPF0Pqf15M6OZB/fkbZhxRtiJeSRylF4hBf3RmEGkeaWZoqwIPNnQkeRnXZF3L1nulVlqV9ZIBTh09YWGM3lZZZuMhnlAjE+IpNik6mJr/ai4JcoJAjfT1vYsXq2m4yxXrjX0cecFc/d2AU0SJgid3wKBgGnFxlwQ0jlSn7PiPSrGjWy1TFEZbqnTj37C3YmIfRFwtmXZZmK0wsPMSNLcSurySA4ZX/HDKHIAiCWLoj7vHYTQFQNnE8ACtmGSN4/Smz5hZyMDQ+8otLEbo6WpO9a42UgucSrp/yYZk1Nv5GwTHzw6/WEbxCVLaYnVtNmhH3O/AoGAWcEqfHN+Li92vgoO/j+3awXymAz18Tp91CQEfSUznDE8Z6Y54jp3T7TzCWSKXPZNPS7hypQptU6wXCvqyiMeTPxWs7HWyrxkWuy0jLORKgS31eu35JRYBJAtQSyfLML7AztXPSRey8HX7WvMogSLvgCKzdF3ychV3qrUoavZOi0CgYBOZLYymoqPaeLwTljsR0ke/49uZhQP//B/vs+ERTPoloX/LolCgzAFqTQyZ7d+ZdHX1yLXAnhANtc2IeWNUOtPy38Cgt6d5u5kaI3HVmCH92OBxm7LaZC4B0e4s20vASY0vdwVEIk9bBwvCjhSdXhv68LixU8yPYPWkfqsdThn0Q==")
                .setEnableDebug(true) //正式发布该参数必须为false, false会对补丁做签名校验, 否则就可能存在安全漏洞风险
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                        }
                    }
                }).initialize();
    }
}
