package org.kitdroid.proxyhelper.proxy;

import java.util.List;

/**
 * Created by huiyh on 2016/4/18.
 */
public class ProxyEntity {
    private String host;
    private int port;
    private List<String> exclusionList;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<String> getExclusionList() {
        return exclusionList;
    }

    public void setExclusionList(List<String> exclusionList) {
        this.exclusionList = exclusionList;
    }
}
