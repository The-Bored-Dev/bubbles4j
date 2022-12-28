package com.theboreddev.either;

public interface Dependency {
    Either<Exception, Result> call();
}

record Result(String value) {}
