package com.theboreddev.either;

public record Failure<F, S>(F exception) implements Either<F, S> {

    @Override
    public EitherType type() {
        return EitherType.FAILURE;
    }

    public F exception() {
        return this.exception;
    }

    @Override
    public Success<F, S> success() {
        throw new IllegalStateException("Instance is not of type Success");
    }

    @Override
    public Failure<F, S> failure() {
        return this;
    }
}
