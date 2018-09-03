package com.block.vtCoin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build;

/**
 * 网络状态监控util
 * Created by zdc on 2016/2/24.
 */
public class NetUtil {
    public static final int NETWORN_NONE = -1;
    public static final int NETWORN_WIFI = 1;
    public static final int NETWORN_MOBILE = 0;
    public static final int NETWORN_ETHERNET = 9;

    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        State state;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connManager.getAllNetworks();
            NetworkInfo networkInfo;
            int result = NETWORN_NONE;
            for (Network network:networks) {
                networkInfo = connManager.getNetworkInfo(network);
                state = networkInfo.getState();
                if (state == State.CONNECTED || state == State.CONNECTING) {
                    result =  networkInfo.getType();
                }
            }
            return result;
        }else{
            for (int i = 0 ; i <= ConnectivityManager.TYPE_VPN; i ++){
                NetworkInfo networkInfo = connManager.getNetworkInfo(i);
                if(networkInfo != null){
                    state = networkInfo.getState();
                    if (state == State.CONNECTED || state == State.CONNECTING) {
                        return i;
                    }
                }
            }
        }
        return NETWORN_NONE;
    }
}