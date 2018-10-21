package com.ay3524.connectionchange;

public interface ConnectionStatusCallback {
    void onNetworkConnectionChanged(boolean isOnline);
}
