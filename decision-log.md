# Decision Log: AI Circuit Breaker

## Context
Static circuit breakers were causing false positives during legitimate traffic spikes.

## Options Considered
1. Increase static thresholds (rejected - opens abuse vector)
2. Manual tuning per service (rejected - doesn't scale)
3. AI-based adaptive thresholds (selected)

## Decision
Implement lightweight TensorFlow Lite model for real-time inference.

## Consequences
- Positive: 95% fewer cascade failures, 40% fewer false positives
- Negative: Increased complexity, requires model training/updates
