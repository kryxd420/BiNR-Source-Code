package com.tapdaq.sdk.model.analytics.stats;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.tapdaq.sdk.helpers.TDGson;
import com.tapdaq.sdk.model.waterfall.TDWaterfallItem;
import com.tapjoy.TJAdUnitConstants;
import java.lang.reflect.Type;
import java.util.List;

public class TMStatsDataAdapter implements JsonDeserializer<TMStatsDataBase>, JsonSerializer<TMStatsDataBase> {
    public JsonElement serialize(TMStatsDataBase tMStatsDataBase, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }

    public TMStatsDataBase deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        TMStatsDataBase createMediationAdRequest = createMediationAdRequest(jsonElement);
        if (createMediationAdRequest == null) {
            createMediationAdRequest = createMediationAdTimeout(jsonElement);
        }
        if (createMediationAdRequest == null) {
            createMediationAdRequest = createMediationSDKTimeout(jsonElement);
        }
        if (createMediationAdRequest == null) {
            createMediationAdRequest = createMediationImpressionAd(jsonElement);
        }
        if (createMediationAdRequest == null) {
            createMediationAdRequest = createMediationAd(jsonElement);
        }
        if (createMediationAdRequest == null) {
            createMediationAdRequest = createMediation(jsonElement);
        }
        return createMediationAdRequest == null ? createIAPStat(jsonElement) : createMediationAdRequest;
    }

    /* access modifiers changed from: package-private */
    public TMStatsDataBase createMediationAdRequest(JsonElement jsonElement) {
        String str;
        String str2;
        String str3;
        Integer num;
        String str4;
        Long l;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("demand_type");
        JsonElement jsonElement3 = asJsonObject.get("ad_unit");
        JsonElement jsonElement4 = asJsonObject.get("ad_unit_id");
        JsonElement jsonElement5 = asJsonObject.get("placement_tag");
        JsonElement jsonElement6 = asJsonObject.get("waterfall_id");
        JsonElement jsonElement7 = asJsonObject.get("waterfall_position");
        JsonElement jsonElement8 = asJsonObject.get("date_created_in_millis");
        JsonElement jsonElement9 = asJsonObject.get("credentials_type");
        JsonElement jsonElement10 = asJsonObject.get("network");
        JsonElement jsonElement11 = asJsonObject.get("version_id");
        JsonElement jsonElement12 = asJsonObject.get("date_fulfilled_in_millis");
        JsonElement jsonElement13 = asJsonObject.get("reason");
        if (jsonElement5 == null) {
            str = null;
        } else {
            str = jsonElement5.getAsString();
        }
        if (jsonElement3 == null) {
            str2 = null;
        } else {
            str2 = jsonElement3.getAsString();
        }
        if (jsonElement3 == null) {
            str3 = null;
        } else {
            str3 = jsonElement4.getAsString();
        }
        if (jsonElement7 == null) {
            num = null;
        } else {
            num = Integer.valueOf(jsonElement7.getAsInt());
        }
        if (jsonElement13 == null) {
            str4 = null;
        } else {
            str4 = jsonElement13.getAsString();
        }
        if (jsonElement12 == null) {
            l = null;
        } else {
            l = Long.valueOf(jsonElement12.getAsLong());
        }
        if (jsonElement12 != null) {
            return new TMStatsDataMediationAdRequest(jsonElement6.getAsString(), str4, jsonElement2.getAsString(), num, Long.valueOf(jsonElement8.getAsLong()), jsonElement10.getAsString(), jsonElement9.getAsString(), str2, str3, str, jsonElement11.getAsString(), l);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public TMStatsDataBase createMediationImpressionAd(JsonElement jsonElement) {
        String str;
        String str2;
        String str3;
        Integer num;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("demand_type");
        JsonElement jsonElement3 = asJsonObject.get("ad_unit");
        JsonElement jsonElement4 = asJsonObject.get("ad_unit_id");
        JsonElement jsonElement5 = asJsonObject.get("placement_tag");
        JsonElement jsonElement6 = asJsonObject.get("waterfall");
        JsonElement jsonElement7 = asJsonObject.get("waterfall_id");
        JsonElement jsonElement8 = asJsonObject.get("waterfall_position");
        JsonElement jsonElement9 = asJsonObject.get("date_created_in_millis");
        JsonElement jsonElement10 = asJsonObject.get("credentials_type");
        JsonElement jsonElement11 = asJsonObject.get("network");
        JsonElement jsonElement12 = asJsonObject.get("version_id");
        if (jsonElement5 == null) {
            str = null;
        } else {
            str = jsonElement5.getAsString();
        }
        if (jsonElement3 == null) {
            str2 = null;
        } else {
            str2 = jsonElement3.getAsString();
        }
        if (jsonElement3 == null) {
            str3 = null;
        } else {
            str3 = jsonElement4.getAsString();
        }
        if (jsonElement8 == null) {
            num = null;
        } else {
            num = Integer.valueOf(jsonElement8.getAsInt());
        }
        List list = (List) TDGson.Create().fromJson(jsonElement6, new TypeToken<List<TDWaterfallItem>>() {
        }.getType());
        if (list == null || list.isEmpty()) {
            return null;
        }
        return new TMStatsDataMediationImpression(jsonElement7.getAsString(), jsonElement2.getAsString(), list, num, Long.valueOf(jsonElement9.getAsLong()), jsonElement11.getAsString(), jsonElement10.getAsString(), str2, str3, str, jsonElement12.getAsString());
    }

    /* access modifiers changed from: package-private */
    public TMStatsDataBase createMediationAd(JsonElement jsonElement) {
        String str;
        String str2;
        String str3;
        Integer num;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("demand_type");
        JsonElement jsonElement3 = asJsonObject.get("ad_unit");
        JsonElement jsonElement4 = asJsonObject.get("ad_unit_id");
        JsonElement jsonElement5 = asJsonObject.get("placement_tag");
        JsonElement jsonElement6 = asJsonObject.get("waterfall_id");
        JsonElement jsonElement7 = asJsonObject.get("waterfall_position");
        JsonElement jsonElement8 = asJsonObject.get("date_created_in_millis");
        JsonElement jsonElement9 = asJsonObject.get("credentials_type");
        JsonElement jsonElement10 = asJsonObject.get("network");
        JsonElement jsonElement11 = asJsonObject.get("version_id");
        if (jsonElement5 == null) {
            str = null;
        } else {
            str = jsonElement5.getAsString();
        }
        if (jsonElement3 == null) {
            str2 = null;
        } else {
            str2 = jsonElement3.getAsString();
        }
        if (jsonElement3 == null) {
            str3 = null;
        } else {
            str3 = jsonElement4.getAsString();
        }
        if (jsonElement7 == null) {
            num = null;
        } else {
            num = Integer.valueOf(jsonElement7.getAsInt());
        }
        if (jsonElement6 != null) {
            return new TMStatsDataMediationAd(jsonElement6.getAsString(), jsonElement2.getAsString(), num, Long.valueOf(jsonElement8.getAsLong()), jsonElement10.getAsString(), jsonElement9.getAsString(), str2, str3, str, jsonElement11.getAsString());
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public TMStatsDataBase createMediationAdError(JsonElement jsonElement) {
        String str;
        String str2;
        String str3;
        Integer num;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("demand_type");
        JsonElement jsonElement3 = asJsonObject.get("ad_unit");
        JsonElement jsonElement4 = asJsonObject.get("ad_unit_id");
        JsonElement jsonElement5 = asJsonObject.get("placement_tag");
        JsonElement jsonElement6 = asJsonObject.get("waterfall_id");
        JsonElement jsonElement7 = asJsonObject.get("waterfall_position");
        JsonElement jsonElement8 = asJsonObject.get("date_created_in_millis");
        JsonElement jsonElement9 = asJsonObject.get("credentials_type");
        JsonElement jsonElement10 = asJsonObject.get("network");
        JsonElement jsonElement11 = asJsonObject.get("version_id");
        JsonElement jsonElement12 = asJsonObject.get("errorcode");
        JsonElement jsonElement13 = asJsonObject.get(TJAdUnitConstants.String.VIDEO_ERROR);
        if (jsonElement5 == null) {
            str = null;
        } else {
            str = jsonElement5.getAsString();
        }
        if (jsonElement3 == null) {
            str2 = null;
        } else {
            str2 = jsonElement3.getAsString();
        }
        if (jsonElement3 == null) {
            str3 = null;
        } else {
            str3 = jsonElement4.getAsString();
        }
        if (jsonElement7 == null) {
            num = null;
        } else {
            num = Integer.valueOf(jsonElement7.getAsInt());
        }
        Integer valueOf = Integer.valueOf(jsonElement12.getAsInt());
        String asString = jsonElement13.getAsString();
        if (jsonElement6 == null || asString == null) {
            return null;
        }
        return new TMStatsDataMediationAdError(jsonElement6.getAsString(), jsonElement2.getAsString(), num, Long.valueOf(jsonElement8.getAsLong()), jsonElement10.getAsString(), jsonElement9.getAsString(), str2, str3, str, jsonElement11.getAsString(), valueOf, asString);
    }

    /* access modifiers changed from: package-private */
    public TMStatsDataBase createMediation(JsonElement jsonElement) {
        String str;
        String str2;
        String str3;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("ad_unit");
        JsonElement jsonElement3 = asJsonObject.get("ad_unit_id");
        JsonElement jsonElement4 = asJsonObject.get("placement_tag");
        JsonElement jsonElement5 = asJsonObject.get("date_created_in_millis");
        JsonElement jsonElement6 = asJsonObject.get("credentials_type");
        JsonElement jsonElement7 = asJsonObject.get("network");
        JsonElement jsonElement8 = asJsonObject.get("version_id");
        if (jsonElement4 == null) {
            str = null;
        } else {
            str = jsonElement4.getAsString();
        }
        if (jsonElement2 == null) {
            str2 = null;
        } else {
            str2 = jsonElement2.getAsString();
        }
        if (jsonElement2 == null) {
            str3 = null;
        } else {
            str3 = jsonElement3.getAsString();
        }
        if (jsonElement7 == null || jsonElement8 == null) {
            return null;
        }
        return new TMStatsDataMediation(Long.valueOf(jsonElement5.getAsLong()), jsonElement7.getAsString(), jsonElement6.getAsString(), str2, str3, str, jsonElement8.getAsString());
    }

    /* access modifiers changed from: package-private */
    public TMStatsDataBase createMediationSDKTimeout(JsonElement jsonElement) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("date_created_in_millis");
        JsonElement jsonElement3 = asJsonObject.get("credentials_type");
        JsonElement jsonElement4 = asJsonObject.get("network");
        JsonElement jsonElement5 = asJsonObject.get("version_id");
        JsonElement jsonElement6 = asJsonObject.get("timeout_in_milliseconds");
        if (jsonElement4 == null || jsonElement5 == null || jsonElement6 == null) {
            return null;
        }
        return new TMStatsDataSDKTimeout(Long.valueOf(jsonElement2.getAsLong()), jsonElement4.getAsString(), jsonElement3.getAsString(), jsonElement5.getAsString(), Long.valueOf(jsonElement6.getAsLong()));
    }

    /* access modifiers changed from: package-private */
    public TMStatsDataBase createMediationAdTimeout(JsonElement jsonElement) {
        Integer num;
        String str;
        String str2;
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("date_created_in_millis");
        JsonElement jsonElement3 = asJsonObject.get("demand_type");
        JsonElement jsonElement4 = asJsonObject.get("ad_unit");
        JsonElement jsonElement5 = asJsonObject.get("ad_unit_id");
        JsonElement jsonElement6 = asJsonObject.get("placement_tag");
        JsonElement jsonElement7 = asJsonObject.get("waterfall_id");
        JsonElement jsonElement8 = asJsonObject.get("waterfall_position");
        JsonElement jsonElement9 = asJsonObject.get("credentials_type");
        JsonElement jsonElement10 = asJsonObject.get("network");
        JsonElement jsonElement11 = asJsonObject.get("version_id");
        if (jsonElement8 == null) {
            num = null;
        } else {
            num = Integer.valueOf(jsonElement8.getAsInt());
        }
        JsonElement jsonElement12 = asJsonObject.get("timeout_in_milliseconds");
        if (jsonElement4 == null) {
            str = null;
        } else {
            str = jsonElement4.getAsString();
        }
        if (jsonElement4 == null) {
            str2 = null;
        } else {
            str2 = jsonElement5.getAsString();
        }
        if (jsonElement10 == null || jsonElement11 == null || jsonElement12 == null || jsonElement7 == null) {
            return null;
        }
        return new TMStatsDataAdTimeout(jsonElement7.getAsString(), jsonElement3.getAsString(), num, Long.valueOf(jsonElement2.getAsLong()), jsonElement10.getAsString(), jsonElement9.getAsString(), str, str2, jsonElement6.getAsString(), jsonElement11.getAsString(), Long.valueOf(jsonElement12.getAsLong()));
    }

    /* access modifiers changed from: package-private */
    public TMStatsDataBase createIAPStat(JsonElement jsonElement) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = asJsonObject.get("date_created_in_millis");
        JsonElement jsonElement3 = asJsonObject.get("product_name");
        JsonElement jsonElement4 = asJsonObject.get("product_price");
        JsonElement jsonElement5 = asJsonObject.get("product_locale");
        if (jsonElement3 != null) {
            return new TMStatsDataIAP(jsonElement2.getAsLong(), jsonElement3.getAsString(), Double.valueOf(jsonElement4.getAsDouble()), jsonElement5.getAsString());
        }
        return null;
    }
}
