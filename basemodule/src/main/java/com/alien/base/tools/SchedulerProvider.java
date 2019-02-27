package com.alien.base.tools;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider {

    public <T> ObservableTransformer<T, T> ioToMainObservableScheduler() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(getIOThreadScheduler())
                        .observeOn(getMainThreadScheduler());
            }
        };
    }

    public <T> SingleTransformer ioToMainSingleScheduler(){
        return new SingleTransformer() {
            @Override
            public SingleSource apply(Single upstream) {
                return upstream.subscribeOn(getIOThreadScheduler())
                        .observeOn(getMainThreadScheduler());
            }
        };
    }


    public CompletableTransformer ioToMainCompletableScheduler() {
        return new CompletableTransformer() {
            @Override
            public CompletableSource apply(Completable upstream) {
                return upstream.subscribeOn(getIOThreadScheduler())
                        .observeOn(getMainThreadScheduler());
            }
        };
    }


    public <T> FlowableTransformer<T, T> ioToMainFlowableScheduler(){
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(getIOThreadScheduler())
                        .observeOn(getMainThreadScheduler());
            }
        };
    }


    public <T> MaybeTransformer ioToMainMaybeScheduler(){
        return new MaybeTransformer() {
            @Override
            public MaybeSource apply(Maybe upstream) {
                return upstream.subscribeOn(getIOThreadScheduler())
                        .observeOn(getMainThreadScheduler());
            }
        };
    }

    public Scheduler getIOThreadScheduler() {
        return Schedulers.io();
    }

    public Scheduler getMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
