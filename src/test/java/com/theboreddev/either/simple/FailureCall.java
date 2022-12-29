package com.theboreddev.either.simple;

import com.theboreddev.either.Either;
import com.theboreddev.either.Result;

public class FailureCall implements Dependency {
    @Override
    public Either<Exception, Result> call() {
        return Either.failure(new IllegalStateException("Something went wrong!"));
    }
}
