package com.theboreddev.either;

public class SuccessCall implements Dependency {
    @Override
    public Either<Exception, Result> call() {
        return Either.success(new Result("OK"));
    }
}
