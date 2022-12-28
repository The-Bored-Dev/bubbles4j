package com.theboreddev.either;

public class FailureCall implements Dependency {
    @Override
    public Either<Exception, Result> call() {
        return Either.failure(new IllegalStateException("Something went wrong!"));
    }
}
