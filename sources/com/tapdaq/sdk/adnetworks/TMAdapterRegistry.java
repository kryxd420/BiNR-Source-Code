package com.tapdaq.sdk.adnetworks;

import com.tapdaq.sdk.common.TMAdapter;
import java.util.Locale;

public class TMAdapterRegistry {
    private static String ADAPTER_PACKAGE_NAME_FORMAT = "com.tapdaq.adapters.TM%sAdapter";

    public static void RegisterAdapters() {
        for (int i = 0; i < TMMediationNetworks.getTotalNetworks(); i++) {
            TMAdapter GetAdapter = GetAdapter(TMMediationNetworks.getName(i));
            if (GetAdapter != null) {
                TDMediationServiceProvider.getMediationService().registerAdapter(GetAdapter);
            }
        }
    }

    public static TMAdapter GetAdapter(String str) {
        try {
            Object newInstance = Class.forName(String.format(Locale.ENGLISH, ADAPTER_PACKAGE_NAME_FORMAT, new Object[]{str})).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (newInstance == null || !(newInstance instanceof TMAdapter)) {
                return null;
            }
            return (TMAdapter) newInstance;
        } catch (Exception unused) {
            return null;
        }
    }
}
