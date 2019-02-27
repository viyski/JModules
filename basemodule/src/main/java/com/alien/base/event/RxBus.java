package com.alien.base.event;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {

    private Subject<Object> mBus = PublishSubject.create();
    private Map<Class, Object> mStickyEventMap = new ConcurrentHashMap<>();

    private static RxBus instance;

    private RxBus(){
    }

    public static RxBus getInstance(){
        if (instance == null){
            synchronized (RxBus.class){
                if (instance == null)
                    instance = new RxBus();
            }
        }
        return instance;
    }

    public void post(Object object){
        mBus.onNext(object);
    }

    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    public <T> Observable<T> toObservable(LifecycleOwner owner, Class<T> eventType) {
        LifecycleProvider<Lifecycle.Event> provider = AndroidLifecycle.createLifecycleProvider(owner);
        Observable observable = mBus.ofType(eventType).compose(provider.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public void reset(){
        instance = null;
    }

    public void postStickyEvent(Object event){
        synchronized (mStickyEventMap) {
            mStickyEventMap.put(event.getClass(), event);
        }
        post(event);
    }

    public <T> Observable<T> toObservableSticky(LifecycleOwner owner, final Class<T> eventType) {
        synchronized(mStickyEventMap) {
            LifecycleProvider<Lifecycle.Event> provider = AndroidLifecycle.createLifecycleProvider(owner);
            Observable observable = mBus.ofType(eventType).compose(provider.bindToLifecycle());
            observable.observeOn(AndroidSchedulers.mainThread());

            final Object event = mStickyEventMap.get(eventType);
            if (event != null){
                return observable.mergeWith(Observable.create(new ObservableOnSubscribe() {
                    @Override
                    public void subscribe(ObservableEmitter emitter) throws Exception {
                        emitter.onNext(eventType.cast(event));
                    }
                }));
            }else{
                return observable;
            }
        }
    }

    public <T> T getStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.get(eventType));
        }
    }


    public <T> T removeStickyEvent(Class<T> eventType) {
        synchronized (mStickyEventMap) {
            return eventType.cast(mStickyEventMap.remove(eventType));
        }
    }

    public void removeAllStickyEvents() {
        synchronized (mStickyEventMap) {
            mStickyEventMap.clear();
        }
    }

}
