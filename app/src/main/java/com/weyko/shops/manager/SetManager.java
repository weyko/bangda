package com.weyko.shops.manager;

import com.weyko.shops.util.SaveDataUtil;

/**
 * Description:
 * Created  by: weyko on 2017/7/4.
 */

public class SetManager {
    public static void setIsSoundRemindOpen(boolean isSoundRemindOpen){
        SaveDataUtil.getInstance().putBoolean("isSoundRemindOpen",isSoundRemindOpen);
    }
    public static boolean isSoundRemindOpen(){
        return SaveDataUtil.getInstance().getBoolean("isSoundRemindOpen",true);
    }
    public static void setIsVibrationRemindOpen(boolean isVibrationRemindOpen){
        SaveDataUtil.getInstance().putBoolean("isVibrationRemindOpen",isVibrationRemindOpen);
    }
    public static boolean isVibrationRemindOpen(){
        return SaveDataUtil.getInstance().getBoolean("isVibrationRemindOpen",true);
    }
}
