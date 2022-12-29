package com.theboreddev.either.simple;


import com.theboreddev.either.Either;
import com.theboreddev.either.Result;

public interface Dependency {
    Either<Exception, Result> call();
}

