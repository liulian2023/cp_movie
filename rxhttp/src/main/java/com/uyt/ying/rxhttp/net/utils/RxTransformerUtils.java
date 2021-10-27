package com.uyt.ying.rxhttp.net.utils;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xxxx
 * @description 用于RxJava线程切换
 * @create 2019/9/3
 */
public class RxTransformerUtils {

    public static <T> ObservableTransformer<T, T> observableTransformer() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<T, T> flowableTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    final static ObservableTransformer NEW_MAIN = upstream -> upstream.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread());

    static <T> ObservableTransformer<T, T> new2mainSchedulers() {
        return NEW_MAIN;
    }

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> newthread_main() {
        return (ObservableTransformer<T, T>) new2mainSchedulers();
    }

    final static ObservableTransformer IO_MAIN = upstream -> upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    static <T> ObservableTransformer<T, T> io2mainSchedulers() {
        return IO_MAIN;
    }

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> io_main() {
        return (ObservableTransformer<T, T>) io2mainSchedulers();
    }
}
