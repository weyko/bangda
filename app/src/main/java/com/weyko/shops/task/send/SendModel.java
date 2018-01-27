package com.weyko.shops.task.send;

import com.weyko.shops.network.udp.SendPackageToServer;

/**
 * Description:
 * Created  by: weyko on 2017/6/29.
 */

public class SendModel <T>{
    private int instruct;
    private String sendContent;
    private Class<T> clzz;
    private SendPackageToServer.OnSendDataListener onSendDataListener;

    public SendModel(int instruct,String sendContent, Class<T> clzz, SendPackageToServer.OnSendDataListener onSendDataListener) {
        this.instruct=instruct;
        this.sendContent = sendContent;
        this.clzz = clzz;
        this.onSendDataListener = onSendDataListener;
    }
    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public Class<T> getClzz() {
        return clzz;
    }

    public void setClzz(Class<T> clzz) {
        this.clzz = clzz;
    }

    public SendPackageToServer.OnSendDataListener getOnSendDataListener() {
        return onSendDataListener;
    }

    public void setOnSendDataListener(SendPackageToServer.OnSendDataListener onSendDataListener) {
        this.onSendDataListener = onSendDataListener;
    }
}
