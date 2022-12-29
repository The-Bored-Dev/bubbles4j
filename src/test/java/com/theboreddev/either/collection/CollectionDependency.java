package com.theboreddev.either.collection;

import com.theboreddev.either.Either;
import com.theboreddev.either.Result;

import java.util.List;

public interface CollectionDependency {
    Either<Exception, List<Result>> call();
}
