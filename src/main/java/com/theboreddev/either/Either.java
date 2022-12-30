package com.theboreddev.either;

public interface Either<F, S> {

    EitherType type();

    Success<F, S> success();

    Failure<F, S> failure();

    static <F, S> Success<F, S> success(S entity) {
        return new Success<>(entity);
    }

    static <F, S> Failure<F, S> failure(F exception) {
        return new Failure<>(exception);
    }
}

