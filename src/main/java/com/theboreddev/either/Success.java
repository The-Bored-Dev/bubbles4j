package com.theboreddev.either;

import java.util.function.Function;

public record Success<F, S>(S entity) implements Either<F, S> {

    @Override
    public EitherType type() {
        return EitherType.SUCCESS;
    }

    @Override
    public <T> Either<F, T> map(Function<Either<F, S>, T> map) {
        return new Success<>(map.apply(this));
    }


    public S entity() {
        return this.entity;
    }

    @Override
    public Success<F, S> success() {
        return this;
    }

    @Override
    public Failure<F, S> failure() {
        throw new IllegalStateException("Instance is not of type Failure");
    }
}
