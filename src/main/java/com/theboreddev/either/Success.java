package com.theboreddev.either;

public record Success<F, S>(S entity) implements Either<F, S> {

    @Override
    public EitherType type() {
        return EitherType.SUCCESS;
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
