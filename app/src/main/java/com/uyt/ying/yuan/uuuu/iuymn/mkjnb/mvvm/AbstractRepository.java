package com.uyt.ying.yuan.uuuu.iuymn.mkjnb.mvvm;

import androidx.lifecycle.MutableLiveData;

import com.uyt.ying.rxhttp.net.common.RetrofitFactory;
import com.uyt.ying.rxhttp.net.utils.RxTransformerUtils;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class AbstractRepository {
    private CompositeDisposable mCompositeDisposable;

    public MutableLiveData<String> loadState;


    public AbstractRepository() {
        loadState = new MutableLiveData<>();
    }

    protected void postState(String state) {
        if (loadState != null) {
            loadState.postValue(state);
        }
    }


    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable = RetrofitFactory.getCompositeDisposableInstance();
        mCompositeDisposable.add(disposable);
    }

    protected <T> Subscriber<T> addSubscriber(Flowable<T> flowable, Subscriber<T> subscriber) {
        return flowable.compose(RxTransformerUtils.flowableTransformer()).subscribeWith(subscriber);
    }

    public <T> void addSubscription(Observable<T> observable, Observer<T> observer) {
        observable.compose(RxTransformerUtils.observableTransformer())
                .subscribe(observer);
    }

    public void unDisposable() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }


    /**
     * 解除订阅
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }
}
