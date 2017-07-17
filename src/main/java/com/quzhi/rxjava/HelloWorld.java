package com.quzhi.rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhiqu on 7/16/17.
 * <p>
 * Playground for RxJava
 */

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        map();
    }

    private static void helloWorld() {
        Flowable.just("Hello world").subscribe(System.out::println);
    }

    private static void compute() throws Exception {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000); // <--- wait for the flow to finish
    }

    private static void map() {
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);
    }
}

