package com.theboreddev.either.simple;

import com.theboreddev.either.Either;
import com.theboreddev.either.Result;

public class SuccessCall implements SimpleDependency {
    @Override
    public Either<Exception, Result> call() {
        return Either.success(new Result("OK"));
    }
}
