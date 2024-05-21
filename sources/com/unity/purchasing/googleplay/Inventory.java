package com.unity.purchasing.googleplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Inventory {
    Set<String> mPurchaseHistoryOfSub = new HashSet();
    Map<String, Purchase> mPurchaseMap = new HashMap();
    public Map<String, SkuDetails> mSkuMap = new HashMap();

    Inventory() {
    }

    public boolean hasPurchaseHistory(String str) {
        return this.mPurchaseHistoryOfSub.contains(str);
    }

    public List<String> getSubscriptionsWithHistory() {
        return new ArrayList(this.mPurchaseHistoryOfSub);
    }

    public SkuDetails getSkuDetails(String str) {
        return this.mSkuMap.get(str);
    }

    public Purchase getPurchase(String str) {
        return this.mPurchaseMap.get(str);
    }

    public boolean hasPurchase(String str) {
        return this.mPurchaseMap.containsKey(str);
    }

    public boolean hasDetails(String str) {
        return this.mSkuMap.containsKey(str);
    }

    public void erasePurchase(String str) {
        if (this.mPurchaseMap.containsKey(str)) {
            this.mPurchaseMap.remove(str);
        }
    }

    /* access modifiers changed from: package-private */
    public List<String> getAllOwnedSkus() {
        return new ArrayList(this.mPurchaseMap.keySet());
    }

    /* access modifiers changed from: package-private */
    public List<String> getAllSkus(String str) {
        ArrayList arrayList = new ArrayList();
        for (SkuDetails next : this.mSkuMap.values()) {
            if (next.getType().equals(str)) {
                arrayList.add(next.getSku());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<String> getAllOwnedSkus(String str) {
        ArrayList arrayList = new ArrayList();
        for (Purchase next : this.mPurchaseMap.values()) {
            if (next.getItemType().equals(str)) {
                arrayList.add(next.getSku());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public List<Purchase> getAllPurchases() {
        return new ArrayList(this.mPurchaseMap.values());
    }

    /* access modifiers changed from: package-private */
    public void addSkuDetails(SkuDetails skuDetails) {
        this.mSkuMap.put(skuDetails.getSku(), skuDetails);
    }

    /* access modifiers changed from: package-private */
    public void addPurchase(Purchase purchase) {
        this.mPurchaseMap.put(purchase.getSku(), purchase);
    }

    /* access modifiers changed from: package-private */
    public void addPurchaseToSubscriptionPurchaseHistory(String str) {
        this.mPurchaseHistoryOfSub.add(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("skuDetails = ");
        sb.append("[");
        for (String next : this.mSkuMap.keySet()) {
            sb.append(next);
            sb.append(" = ");
            sb.append(getSkuDetails(next));
            sb.append(", ");
        }
        sb.append("]");
        sb.append(", purchases = ");
        sb.append("[");
        for (String next2 : this.mPurchaseMap.keySet()) {
            sb.append(next2);
            sb.append(" = ");
            sb.append(getSkuDetails(next2));
            sb.append(", ");
        }
        sb.append("]");
        return "{Inventory: " + sb.toString() + "}";
    }
}
