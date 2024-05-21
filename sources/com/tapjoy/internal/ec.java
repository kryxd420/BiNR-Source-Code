package com.tapjoy.internal;

import android.os.AsyncTask;
import org.json.JSONObject;

public abstract class ec extends AsyncTask {
    a d;
    protected final b e;

    public interface a {
        void a();
    }

    public interface b {
        JSONObject a();

        void a(JSONObject jSONObject);
    }

    public ec(b bVar) {
        this.e = bVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        if (this.d != null) {
            this.d.a();
        }
    }
}
