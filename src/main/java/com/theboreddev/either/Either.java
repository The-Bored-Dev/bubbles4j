package com.theboreddev.either;

import java.util.function.Function;

public abstract class Either<F, S> {
    protected Function<Failure<F, S>, ?> failureHandler = Failure::exception;
    protected Function<Success<F, S>, ?> successHandler = Success::entity;

    public abstract EitherType type();
    public abstract Object get();

    public <T> EitherBuilder onSuccessDo(Function<Success<F, S>, T> action) {
        return new EitherBuilder(this)
                .onSuccessDo(action);
    }

    public <T> EitherBuilder onFailureDo(Function<Failure<F, S>, T> action) {
        return new EitherBuilder(this)
                .onFailureDo(action);
    }

    public abstract Failure<F, S> failure();
    public abstract Success<F, S> success();


    public static <F, S> Success<F, S> success(S entity) {
        return new Success<>(entity);
    }

    public static <F, S> Failure<F, S> failure(F exception) {
        return new Failure<>(exception);
    }

    public class EitherBuilder {
        private final Either<F, S> either;
        protected Function<Failure<F, S>, ?> failureHandler = Failure::exception;
        protected Function<Success<F, S>, ?> successHandler = Success::entity;

        EitherBuilder(Either<F, S> either) {
            this.either = either;
        }

        public EitherBuilder onSuccessDo(Function<Success<F, S>, ?> successHandler) {
            this.successHandler = successHandler;
            return this;
        }

        public EitherBuilder onFailureDo(Function<Failure<F, S>, ?> failureHandler) {
            this.failureHandler = failureHandler;
            return this;
        }

        public Either<F, S> build() {
            either.failureHandler = this.failureHandler;
            either.successHandler = this.successHandler;
            return either;
        }
    }
}

