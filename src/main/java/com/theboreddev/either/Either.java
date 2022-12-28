package com.theboreddev.either;

import java.util.Objects;

public abstract class Either<F extends Exception, S> {
    abstract EitherType type();

    public static <F extends Exception, S> Failure<F, S> failure(F exception) {
        return new Either.Failure<>(exception);
    }

    public static <F extends Exception, S> Success<F, S> success(S entity) {
        return new Either.Success<>(entity);
    }

    static final class Success<F extends Exception, S> extends Either<F, S> {
        private final S s;

        private Success(S s) {
            this.s = s;
        }

        S entity() {
            return s;
        }

        @Override
        EitherType type() {
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

    static final class Failure<F extends Exception, S> extends Either<F, S> {
        private final F f;

        private Failure(F f) {
            this.f = f;
        }

        F exception() {
            return f;
        }

        @Override
        EitherType type() {
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

    enum EitherType {
        SUCCESS,
        FAILURE
    }

    private Either() {}
}
