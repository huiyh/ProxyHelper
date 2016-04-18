package org.kitdroid.proxyhelper.proxy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import org.kitdroid.helper.ContextMate;
import org.kitdroid.util.JSONUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by huiyh on 2016/4/18.
 */
public class ProxyManager {

    public static final String PROXY_DATA = "proxy_data";
    private static ProxyManager sInstance ;

    private final Context mContext = ContextMate.getApplication();
    private List<ProxyEntity> mProxyList = new ArrayList<ProxyEntity>();

    public static ProxyManager getInstance(){
        if(sInstance == null){
            synchronized (ProxyManager.class){
                if(sInstance == null){
                    sInstance = new ProxyManager();
                }
            }
        }
        return sInstance;
    }

    private ProxyManager() {
        parseProxyList();
    }

    public List<ProxyEntity> getProxys(){
        return Collections.unmodifiableList(mProxyList);
    }

    public void addProxy(ProxyEntity entity){
        mProxyList.add(entity);
        storeProxyList();
    }

    public void removeProxy(ProxyEntity entity){
        if(mProxyList.contains(entity)){
            mProxyList.remove(entity);
            storeProxyList();
        }
    }

    private void storeProxyList() {
        String data = JSONUtils.toJson(mProxyList);
        Editor edit = getPreferences().edit();
        edit.putString(PROXY_DATA,data);
        edit.commit();
    }

    private void parseProxyList() {
        String data = getPreferences().getString(PROXY_DATA,null);
        if(!TextUtils.isEmpty(data)){
            ArrayList<ProxyEntity> proxyEntities = JSONUtils.toList(data, ProxyEntity.class);
            if(proxyEntities != null){
                mProxyList.clear();
                mProxyList.addAll(proxyEntities);
            }
        }
    }

    public SharedPreferences getPreferences(){
        return mContext.getSharedPreferences("proxy_manager",Context.MODE_PRIVATE);
    }
}
