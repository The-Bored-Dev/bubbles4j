package com.theboreddev.either;

import java.util.Objects;

public abstract class Either<F extends Exception, S> {
    public abstract EitherType type();

    public static <F extends Exception, S> Failure<F, S> failure(F exception) {
        return new Either.Failure<>(exception);
    }

    public static <F extends Exception, S> Success<F, S> success(S entity) {
        return new Either.Success<>(entity);
    }

    public Either.Success<F, S> success() {
        if (this instanceof Either.Success<F,S>) return (Success<F, S>) this;
        throw new IllegalStateException("Instance is not of type " + Either.Success.class.getSimpleName());
    }

    public Either.Failure<F, S> failure() {
        if (this instanceof Either.Failure<F,S>) return (Failure<F, S>) this;
        throw new IllegalStateException("Instance is not of type " + Either.Failure.class.getSimpleName());
    }

    public static final class Success<F extends Exception, S> extends Either<F, S> {
        private final S s;

        private Success(S s) {
            this.s = s;
        }

        public S entity() {
            return s;
        }

        @Override
        public EitherType type() {
            return EitherType.SUCCESS;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Success<?, ?> success = (Success<?, ?>) o;
            return Objects.equals(s, success.s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(s);
        }

        @Override
        public String toString() {
            return "Success{" +
                    "s=" + s +
                    '}';
        }
    }

    public static final class Failure<F extends Exception, S> extends Either<F, S> {
        private final F f;

        private Failure(F f) {
            this.f = f;
        }

        public F exception() {
            return f;
        }

        @Override
        public EitherType type() {
            return EitherType.FAILURE;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Failure<?, ?> failure = (Failure<?, ?>) o;
            return failure.exception().getClass() == this.exception().getClass() &&
                    failure.exception().getMessage().equals(this.exception().getMessage());
        }

        @Override
        public int hashCode() {
            return Objects.hash(f);
        }

        @Override
        public String toString() {
            return "Failure{" +
                    "f=" + f +
                    '}';
        }
    }

    public enum EitherType {
        SUCCESS,
        FAILURE
    }

    private Either() {}
}
