package com.tonyodev.fetch.callback;

import android.support.annotation.NonNull;
import com.tonyodev.fetch.Fetch;

public interface FetchTask {
    void onProcess(@NonNull Fetch fetch);
}
