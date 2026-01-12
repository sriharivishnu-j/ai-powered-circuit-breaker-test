package com.example;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CircuitBreakerController {
    
    private final AICircuitBreaker circuitBreaker;
    
    public CircuitBreakerController(AICircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }
    
    @GetMapping("/protected")
    public Mono<String> protectedEndpoint() {
        return circuitBreaker.execute(() -> 
            // Your actual service call here
            Mono.just("Success")
        );
    }
}
