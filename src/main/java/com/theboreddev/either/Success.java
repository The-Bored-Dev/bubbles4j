package com.theboreddev.either;

import java.util.Objects;

public class Success<F, S> extends Either<F, S> {
    private final S entity;

    Success(S entity) {
        this.entity = entity;
    }

    @Override
    public EitherType type() {
        return EitherType.SUCCESS;
    }

    @Override
    public S get() {
        return (S) this.successHandler.apply(this);
    }

    @Override
    public Failure<F, S> failure() {
        throw new IllegalStateException("Instance is not of type Failure");
    }

    @Override
    public Success<F, S> success() {
        return this;
    }


    public S entity() {
        return this.entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Success<?, ?> success = (Success<?, ?>) o;
        return Objects.equals(entity, success.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity);
    }

    @Override
    public String toString() {
        return "Success{" +
                "entity=" + entity +
                '}';
    }
}
