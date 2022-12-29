package com.theboreddev.either.stream;

import com.theboreddev.either.Dependency;
import com.theboreddev.either.Either;

import java.util.stream.Stream;

public interface StreamDependency extends Dependency<Stream<Integer>> {
}

class SuccessStreamDependency implements StreamDependency {

    private final Stream<Integer> data;

    SuccessStreamDependency(Stream<Integer> data) {
        this.data = data;
    }

    @Override
    public Either<Exception, Stream<Integer>> call() {
        return Either.success(data);
    }
}

class FailureStreamDependency implements StreamDependency {

    @Override
    public Either<Exception, Stream<Integer>> call() {
        return Either.failure(new IllegalStateException("Something went wrong!"));
    }
}
