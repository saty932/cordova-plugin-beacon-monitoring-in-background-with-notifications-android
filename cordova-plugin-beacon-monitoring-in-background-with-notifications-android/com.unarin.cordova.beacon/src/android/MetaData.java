package com.unarin.cordova.beacon;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

class MetaData {
    private Bundle metaData;
    protected Context mContext;

    MetaData() {
    }

    public void loadMetaData(Context context) {
        this.mContext = context;
        this.metaData = getMetaDataBundle(context);
    }

    private Bundle getMetaDataBundle(Context context) {
        Bundle result = null;

        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            result = info.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
        }

        return result;
    }

    private String getString(String key) {
        String result = null;
        if (this.metaData != null) {
            result = this.metaData.getString(key);
        }
        return result;
    }

    int parseInt(String key, int defaultValue) {
        int result = defaultValue;
        String val = getString(key);
        if (val != null) {
            try {
                result = Integer.parseInt(val);
            } catch (Exception e){
                //e.printStackTrace();
            }
        }
        return result;
    }

    String getString(String key, String defaultValue) {
        String result = defaultValue;
        String val = getString(key);
        if (val != null) {
            result = val;
        }
        return result;
    }

}
