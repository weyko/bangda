package com.weyko.shops.config;

/**
 * Description:UDP协议配置
 * Created  by: weyko on 2017/6/23.
 */

public class UDPConfig {
    /**
     * 服务器端口号
     */
    public final static int SERVER_PORT=6000;
    /**
     * 服务器IP地址
     */
    public static String SERVER_IP="123.207.111.63";//192.168.1.101
    /**
     * 数据缓存大小
     */
    public final static int BUFF_SIZE=1024;
    /**
     * 数据缓存格式
     */
    public final static String BUFF_FORMAT="gbk";
    /**
     * 接受后台数据包超时时间10秒
     */
    public final static int TIME_OUT=10*1000;
    /**
     * 接受后台数据包心跳时间5秒
     */
    public final static int TIME_RECIVE_HEART=5*1000;

    /*************UDP 发包指令*************/
    /**
     * 注册
     * 登录
     */
    public final static int ACTION_REGISTER=1;
    /**
     */
    public final static int ACTION_LOGIN=2;
    /**
     * 发布任务
     */
    public final static int ACTION_SEND_TASK=3;
    /**
     * 转发任务
     */
    public final static int ACTION_GET_TASK=4;
    /**
     * 承接任务（抢单）
     */
    public final static int ACTION_GOT_TASK=5;
    /**
     * 请求付款
     */
    public final static int ACTION_REQUEST_PAY=6;
    /**
     * 确认付款
     */
    public final static int ACTION_AGREE_PAY=7;
    /**
     * 退出登录
     */
    public final static int ACTION_LOGINOUT=8;
    /**
     * 经纬度
     */
    public final static int ACTION_LOCATION=9;
    /**
     * 免密登录
     */
    public final static int ACTION_LOGIN_CACHE=11;
    /**
     * 心跳包（新任务）
     */
    public final static int ACTION_HEART_TASK_NEW=12;
    /**
     * 心跳包（支付账单）
     */
    public final static int ACTION_HEART_TASK_PAY=13;
    /**
     * 心跳包（支付完成）
     */
    public final static int ACTION_HEART_TASK_COMPLETE=14;
    /**
     * 第三方登录
     */
    public final static int ACTION_THIRD_LOGIN=15;
    /**
     * 重置密码
     */
    public final static int ACTION_RESET_PWD=16;
    /**
     * 获取邀请码
     */
    public final static int ACTION_INVITE_INFO=17;
    /**
     * 我的钱包
     */
    public final static int ACTION_MY_WALLET=18;
    /**
     * 接单列表
     */
    public final static int ACTION_TASK_LIST=19;
    /**
     * 我的任务
     */
    public final static int ACTION_MY_TASK_LIST=20;
    /**
     * 任务详情
     */
    public final static int ACTION_TASK_INFO=21;
    /**
     * 余额详情
     */
    public final static int ACTION_BALANCE_INFO=22;
    /**
     * 积分详情
     */
    public final static int ACTION_INTEGRAL_INFO=23;
    /**
     * 检查版本
     */
    public final static int ACTION_CHECK_VERSION=24;
}
