package com.example;

import reactor.core.publisher.Mono;
import java.util.function.Supplier;

public class AICircuitBreaker {
    
    private final AIModel model;
    private volatile State state = State.CLOSED;
    
    public <T> Mono<T> execute(Supplier<Mono<T>> supplier) {
        if (state == State.OPEN) {
            return Mono.error(new CircuitOpenException());
        }
        
        return supplier.get()
            .doOnError(e -> recordFailure(e))
            .doOnSuccess(s -> recordSuccess());
    }
    
    private void recordFailure(Throwable e) {
        double prediction = model.predictShouldOpen();
        if (prediction > 0.7) {
            state = State.OPEN;
        }
    }
    
    private void recordSuccess() {
        state = State.CLOSED;
    }
    
    enum State { OPEN, CLOSED, HALF_OPEN }
}
