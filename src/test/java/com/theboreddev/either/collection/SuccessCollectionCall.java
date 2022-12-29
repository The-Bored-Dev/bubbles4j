package com.theboreddev.either.collection;

import com.theboreddev.either.Either;
import com.theboreddev.either.Result;

import java.util.List;

public class SuccessCollectionCall implements CollectionDependency {

    private final List<Result> data;

    public SuccessCollectionCall(List<Result> data) {
        this.data = data;
    }

    @Override
    public Either<Exception, List<Result>> call() {

        return Either.success(data);
    }
}
