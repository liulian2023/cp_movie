package com.uyt.ying.rxhttp.net.common;

import android.app.Activity;

import com.uyt.ying.rxhttp.net.dialog.DialogUtils;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * created  by xxxx on 2019/11/12.
 */
public class ProgressUtils {

    public static <T> ObservableTransformer<T, T> applyProgressBar(
            @NonNull final Activity activity, String msg) {
        final WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);
        final DialogUtils dialogUtils = new DialogUtils();
        dialogUtils.showProgress(activityWeakReference.get());
        return upstream -> upstream.doOnSubscribe(disposable -> {
        }).doOnTerminate(() -> {
            Activity context;
            if ((context = activityWeakReference.get()) != null && !context.isFinishing()) {
                dialogUtils.dismissProgress();
            }
        }).doOnSubscribe((Consumer<Disposable>) disposable -> {

        });
    }

    public static <T> ObservableTransformer<T, T> applyProgressBar(@NonNull final Activity activity) {
        return applyProgressBar(activity, "");
    }
}
