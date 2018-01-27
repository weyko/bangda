package com.weyko.shops.network.http.download;

/**
 * Created by zhong.xiwei on 2017/8/9.
 */

public class FileLoadEvent {
    long total;
    long bytesLoaded;
    int progress;
    String tag;

    public FileLoadEvent() {
    }
    public FileLoadEvent(long total, long bytesLoaded, String tag) {
        this.total = total;
        this.bytesLoaded = bytesLoaded;
        this.tag = tag;
    }

    public FileLoadEvent(long total, long bytesLoaded) {
        this.total = total;
        this.bytesLoaded = bytesLoaded;
        progress= (int) (bytesLoaded*100/total);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getBytesLoaded() {
        return bytesLoaded;
    }

    public void setBytesLoaded(long bytesLoaded) {
        this.bytesLoaded = bytesLoaded;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public int getProgress(){
        return progress;
    }
}
