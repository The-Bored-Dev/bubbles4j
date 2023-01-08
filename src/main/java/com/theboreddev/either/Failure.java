package com.theboreddev.either;

import java.util.Objects;

public class Failure<F, S> extends Either<F, S> {
    private final F exception;


    Failure(F exception) {
        this.exception = exception;
    }



    @Override
    public EitherType type() {
        return EitherType.FAILURE;
    }

    @Override
    public F get() {
        return (F) failureHandler.apply(this);
    }

    @Override
    public Failure<F, S> failure() {
        return this;
    }

    @Override
    public Success<F, S> success() {
        throw new IllegalStateException("Instance is not of type Success");
    }


    public F exception() {
        return this.exception;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Failure<?, ?> failure = (Failure<?, ?>) o;
        return Objects.equals(exception, failure.exception);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exception);
    }

    @Override
    public String toString() {
        return "Failure{" +
                "exception=" + exception +
                '}';
    }
}
