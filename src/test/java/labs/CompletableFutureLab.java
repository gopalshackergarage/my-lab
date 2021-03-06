package labs;/*
 * Copyright (c) 2019 - Present, Gopal S Akshintala
 * This source code is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
 * 	http://creativecommons.org/licenses/by-sa/4.0/
 */

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureLab {

    @Test
    void isNotLazy() throws ExecutionException, InterruptedException {
        final var handle = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Hello - " + Thread.currentThread().getName()); // Runs on thread other than arrow.hk.main.
                    return "Hello";
                }).thenCompose(s ->
                        CompletableFuture
                                .supplyAsync(() -> {
                                    System.out.println("World - " + Thread.currentThread().getName());
                                    return s + " World";
                                }));
    }
}
