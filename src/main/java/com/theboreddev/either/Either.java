package com.theboreddev.either;

import java.util.function.Function;

public interface Either<F, S> {

    EitherType type();

    <T> Either<F, T> map(Function<Either<F, S>, T> map);

    Success<F, S> success();

    Failure<F, S> failure();

    static <F, S> Success<F, S> success(S entity) {
        return new Success<>(entity);
    }

    static <F, S> Failure<F, S> failure(F exception) {
        return new Failure<>(exception);
    }
}

