package com.theboreddev.either;

import java.util.function.Function;

public record Failure<F, S>(F exception) implements Either<F, S> {

    @Override
    public EitherType type() {
        return EitherType.FAILURE;
    }

    @Override
    public <T> Either<F, T> map(Function<Either<F, S>, T> map) {
        return new Failure<>(this.exception);
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
