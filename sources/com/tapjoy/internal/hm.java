package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class hm {
    final o a = new o(this.c, "noMoreToday.date");
    public final o b = new o(this.c, "noMoreToday.actionIds");
    private final SharedPreferences c;

    public hm(Context context) {
        this.c = context.getApplicationContext().getSharedPreferences("fiverocks", 0);
        b();
    }

    static String a() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    public final void b() {
        String a2 = this.a.a();
        if (a2 != null && !a().equals(a2)) {
            this.a.a((String) null);
            this.b.a((String) null);
        }
    }
}
