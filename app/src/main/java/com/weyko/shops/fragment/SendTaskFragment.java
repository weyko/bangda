package com.weyko.shops.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.weyko.shops.MainActivity;
import com.weyko.shops.R;
import com.weyko.shops.adapter.CommonAdapter;
import com.weyko.shops.adapter.ViewHolder;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.base.BaseBean;
import com.weyko.shops.base.BaseFragment;
import com.weyko.shops.config.Constant;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.FragmentHomeBinding;
import com.weyko.shops.manager.CheckManager;
import com.weyko.shops.manager.LocationManager;
import com.weyko.shops.manager.PermissionManager;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.task.get.TaskInfoBean;
import com.weyko.shops.task.send.SendData;
import com.weyko.shops.task.send.TaskInfoActivity;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.util.TimeUtils;
import com.weyko.shops.view.DrawableClickableEditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created  by: weyko on 2017/6/1.
 */

public class SendTaskFragment extends BaseFragment<FragmentHomeBinding> implements TextWatcher, View.OnClickListener, PoiSearch.OnPoiSearchListener, AMapLocationListener{
    private static final int MAX_SEARCH_SIZE = 10;//最大查询条数
    private CommonAdapter<PoiItem>adapter;
    private List<PoiItem>list;
    private List<PoiItem>startList;
    private List<PoiItem>destinationList;
    private String editCity="";
    private int currentPage=0;
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private PoiResult poiResult; // poi返回的结果
    private boolean isSelected=false;
    private PermissionManager permissionManager;
    private boolean isStartMode=false;
    private PoiItem startItem;
    private PoiItem destinationItem;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editCity=getString(R.string.address_default);
        initData();
        permissionManager=new PermissionManager();
        permissionManager.checkPermisson(this,PermissionManager.RequestPermisson.PERMISSION_LOCATION, new PermissionManager.OnPermissionListener() {
            @Override
            public void onPermissionCheckResult(boolean isAllow) {
                if(isAllow) LocationManager.getInstance().upLoadLocation(getActivity(),SendTaskFragment.this);
                showContentView();
            }
        });
        permissionManager.checkPermisson(this,PermissionManager.RequestPermisson.PERMISSION_SDCARD_WRITE,null);
        binding.setModel(new SendData());
    }
    protected void initData() {
        list=new ArrayList<>();
        startList=new ArrayList<>();
        destinationList=new ArrayList<>();
        if(adapter==null) {
            adapter = new CommonAdapter<PoiItem>(getActivity(), list, R.layout.layout_search_item) {
                @Override
                public void convert(ViewHolder helper, final PoiItem item) {
                    final  String address=item.getProvinceName() + item.getCityName() + item.getDirection()+item.getAdName();
                    helper.setText(R.id.tv_title_search_item,item.getTitle());
                    helper.setText(R.id.tv_address,address);
                    helper.getConvertView().setOnClickListener(new PerfectClickListener() {
                        @Override
                        protected void onNoDoubleClick(View v) {
                            isSelected=true;
                            ShowUtil.hideSoftWindow(getActivity(),binding.tvDestinationMain);
                            if (isStartMode) {
                                startItem = item;
                                binding.startPositionMain.setText(address+item.getTitle());
                            } else {
                                binding.tvDestinationMain.setText(address+item.getTitle());
                                destinationItem = item;
                            }
                            showAddress();
                        }
                    });
                }
            };
        }else{
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
        binding.tvSendMain.setOnClickListener(this);
        hideSoftWindow(binding.llMainHomeFragment);
        binding.cbAreaMain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isStartMode=b;
                if(b) {
                    binding.getModel().setStartPosition("");
                    binding.startPositionMain.setText("");
                }
                binding.startPositionMain.setVisibility(b?View.VISIBLE:View.GONE);
            }
        });
        initAddressWindow();
     }

    /**
     * 设置地址列表显示，显示则隐藏，隐藏则显示
     * @return
     */
    public boolean showAddress(){
        boolean isShow=binding.viewAddress.llLayoutAddress.getVisibility()==View.VISIBLE;
        binding.viewAddress.llLayoutAddress.setVisibility(isShow?View.GONE:View.VISIBLE);
        return !isShow;
    }

    /**
     * 初始化地址列表
     */
    private void initAddressWindow(){
        binding.viewAddress.lvListLayoutAddress.setAdapter(adapter);
        MainActivity mainActivity= (MainActivity) getActivity();
        mainActivity.setShowView(binding.viewAddress.llLayoutAddress);
        ViewGroup.LayoutParams layoutParams = binding.viewAddress.llLayoutAddress.getLayoutParams();
        layoutParams.height= BaseApplication.getInstance().getScreenHeight()*2/5;
        binding.viewAddress.llLayoutAddress.setLayoutParams(layoutParams);
        binding.startPositionMain.setOnClickListener(this);
        binding.tvDestinationMain.setOnClickListener(this);
        binding.viewAddress.tvCloseAddress.setOnClickListener(this);
        binding.viewAddress.cetInputAddress.setDrawableRightListener(new DrawableClickableEditText.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                if(isStartMode)
                   binding.startPositionMain.setText("");
                else
                  binding.tvDestinationMain.setText("");
                binding.viewAddress.cetInputAddress.setText("");
                binding.viewAddress.cetInputAddress.requestFocus();
                list.clear();
                adapter.notifyDataSetChanged();
            }
        });
        binding.viewAddress.cetInputAddress.addTextChangedListener(this);
    }
    /**
     * 根据关键字查询
     * @param searchKey
     */
    private void doSearch(String searchKey) {
        if(isSelected){
            isSelected=false;
            return;
        }
        query = new PoiSearch.Query(searchKey, "", editCity);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(MAX_SEARCH_SIZE);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        poiSearch = new PoiSearch(getActivity(), query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String changerTxt = charSequence.toString();
        ShowUtil.d("weyko","searchText="+changerTxt);
        if(!TextUtils.isEmpty(changerTxt)){
            setDrawableRight(binding.viewAddress.cetInputAddress,false);
            doSearch(changerTxt);
        }else{
            setDrawableRight(binding.viewAddress.cetInputAddress,true);
            if(binding.viewAddress.llLayoutAddress.getVisibility()==View.VISIBLE) {
                list.clear();
                if(isStartMode)startList.clear();else destinationList.clear();
                adapter.notifyDataSetChanged();
            }
        }
    }
    private void setDrawableRight(DrawableClickableEditText view, boolean isHide){
        view.setCompoundDrawablesWithIntrinsicBounds(0,0,isHide?0:R.mipmap.icon_close_white,0);
    }
    @Override
    public void afterTextChanged(Editable editable) {

    }
    private CheckManager checkManager;
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tv_send_main){
            if(checkManager==null)checkManager=new CheckManager();
            final SendData model = binding.getModel();
            final String money = binding.etMoneyMain.getText().toString();
            model.setMoneyReward(TextUtils.isEmpty(money)?-1:Double.valueOf(money));
            final MainActivity activity = (MainActivity) getActivity();
            if(checkManager.checkSendTaskData(activity,model,isStartMode)){
                model.setInstruct(UDPConfig.ACTION_SEND_TASK);
                model.setUserId(SaveDataUtil.getInstance().getSSOUserId());
                if(isStartMode){
                    if(startItem!= null){
                        LatLonPoint latLonPoint = startItem.getLatLonPoint();
                        model.setStartLatitude(latLonPoint.getLatitude());
                        model.setStartLgitude(latLonPoint.getLongitude());
                    }
                    if(destinationItem!=null){
                        LatLonPoint latLonPoint = destinationItem.getLatLonPoint();
                        model.setDestLatitude(latLonPoint.getLatitude());
                        model.setDestLgitude(latLonPoint.getLongitude());
                    }
                }else{
                    if(destinationItem!=null){
                        LatLonPoint latLonPoint = destinationItem.getLatLonPoint();
                        model.setDestLatitude(latLonPoint.getLatitude());
                        model.setDestLgitude(latLonPoint.getLongitude());
                        model.setStartLatitude(latLonPoint.getLatitude());
                        model.setStartLgitude(latLonPoint.getLongitude());
                        model.setStartPosition(model.getDestination());
                    }
                }
                SendPackageToServer .getInstance().sendData(activity, ConvertTool.toJsonStr(model), BaseBean.class, new SendPackageToServer.OnSendDataListener() {
                    @Override
                    public void onSendBackResult(Object result) {
                        if(result!=null){
                            BaseBean bean= (BaseBean) result;
                            if(bean.isOk()){
                                Intent intent = new Intent(activity, TaskInfoActivity.class);
                                TaskInfoBean infoBean=new TaskInfoBean();
                                TaskInfoBean.TaskInfoData data=infoBean.new TaskInfoData();
                                data.setTaskName(model.getTaskName());
                                data.setMoneyReward(model.getMoneyReward());
                                data.setTaskDescribe(model.getTaskDescribe());
                                data.setDestination(model.getDestination());
                                data.setStartPosition(model.getStartPosition());
                                data.setSendTime(TimeUtils.getCurrentTime(TimeUtils.TimeFormatType.TIME_FOEMAT_STANDARD));
                                data.setCrosstown(isStartMode);
                                data.setTaskState(-1);
                                infoBean.setData(data);
                                intent.putExtra(Constant.KEY_TASK_INFO,infoBean);
                                activity.startActivity(intent);
                            }else{
                                ShowUtil.showToast(activity,bean.getMsg());
                            }
                        }
                    }
                });
            }
        }else if(view.getId()==R.id.start_position_main){
            isStartMode=true;
            notifyList(true,true);
        }else if(view.getId()==R.id.tv_destination_main){
            isStartMode=false;
            notifyList(false,true);
        }else if(view.getId()==R.id.tv_close_address){
            hideSoftWindow(binding.viewAddress.cetInputAddress);
            showAddress();
        }
    }

    /**
     * 刷新地址列表
     * @param isStartMode 是否选择跨区派送
     * @param bandleAddress 是否触发显示地址列表
     */
    private void notifyList(boolean isStartMode,boolean bandleAddress){
        list.clear();
        list.addAll(isStartMode?startList:destinationList);
        binding.viewAddress.cetInputAddress.setText("");
        binding.viewAddress.cetInputAddress.setHint(isStartMode?R.string.send_start_address:R.string.send_address);
        adapter.notifyDataSetChanged();
        if(bandleAddress){
            isSelected=false;
            binding.viewAddress.cetInputAddress.requestFocus();
            showAddress();
        }
    }
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    list.clear();
                    list.addAll(poiItems);
                    adapter.notifyDataSetChanged();
                    if(list.size()>0) {
                        if (isStartMode) {
                            startList.clear();
                            startList.addAll(list);
                            startItem = startList.get(0);
                        } else {
                            destinationList.clear();
                            destinationList.addAll(list);
                            destinationItem = destinationList.get(0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BaseApplication.isPayPage=true;
    }
    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        isSelected=true;
        String address = aMapLocation.getAddress();
        Log.d("weyko","address="+address);
        editCity=aMapLocation.getCity();
        String province = aMapLocation.getProvince();
        binding.tvDestinationMain.setText(address);
        LatLonPoint latLonPoint=new LatLonPoint(aMapLocation.getLatitude(),aMapLocation.getLongitude());
        PoiItem poiItem=new PoiItem("",latLonPoint,aMapLocation.getAoiName(),"");
        poiItem.setProvinceName(province);
        poiItem.setCityName(editCity);
        poiItem.setDirection(address.replace(province+editCity,""));
        poiItem.setAdName("");
        destinationItem = poiItem;
        destinationList.add(poiItem);
        notifyList(false,false);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BaseApplication.isPayPage=false;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
