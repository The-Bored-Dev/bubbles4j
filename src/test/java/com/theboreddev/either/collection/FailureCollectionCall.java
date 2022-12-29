package com.theboreddev.either.collection;

import com.theboreddev.either.Either;
import com.theboreddev.either.Result;

import java.util.List;

public class FailureCollectionCall implements CollectionDependency {
    @Override
    public Either<Exception, List<Result>> call() {
        return Either.failure(new IllegalStateException("Something went wrong!"));
    }
}
