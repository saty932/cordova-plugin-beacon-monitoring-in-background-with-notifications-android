package com.unarin.cordova.beacon;

class Config extends MetaData {
    private static final String PREFIX = "com.unarin.cordova.beacon.";

    private static final String CAT_NOTIFY = "Notification.";
    private static final String CAT_SERVER = "Server.";

    public Config() {
    }

    public int getNotificationSmallIcon() {
        int result = android.R.drawable.ic_menu_mylocation;//17301598;//17301598=android.R.drawable.ic_popup_reminder
        int iconId = parseInt(PREFIX + CAT_NOTIFY + "SmallIcon", 0);
        if (iconId <= 0) {
            String resName = getString(PREFIX + CAT_NOTIFY + "SmallIcon", null);
            String defType = getString(PREFIX + CAT_NOTIFY + "SmallIcon.defType", "drawable");
            if (resName != null && defType != null) {
                iconId = mContext.getResources().getIdentifier(resName, defType, mContext.getPackageName());
            }
        }
        if (iconId > 0) {
            result = iconId;
        }
        return result;
    }

    public int getNotificationColor() {
        return parseInt(PREFIX + CAT_NOTIFY + "Color",255);
    }

    public String getNotificationTicker() {
        return getString(PREFIX + CAT_NOTIFY + "Ticker", null);
    }

    public String getNotificationContentTitle() {
        return getString(PREFIX + CAT_NOTIFY + "ContentTitle", "PromoNear");
    }

    public String getNotificationContentText() {
        return getString(PREFIX + CAT_NOTIFY + "ContentText", "Are You In Beacon Region");
    }

    public String getNotificationIntentPackageName() {
        return getString(PREFIX + CAT_NOTIFY + "Intent.packageName", "io.ionic.starter");
    }

    public String getNotificationIntentClassName() {
        return getString(PREFIX + CAT_NOTIFY + "Intent.className", null);
    }

    public String getServerUrl() {
        return getString(PREFIX + CAT_SERVER + "Url", null);
    }

    public int getServerConnectTimeout() {
        return parseInt(PREFIX + CAT_SERVER + "ConnectTimeout", 10000);
    }

    public int getServerReadTimeout() {
        return parseInt(PREFIX + CAT_SERVER + "ReadTimeout", 30000);
    }

}
