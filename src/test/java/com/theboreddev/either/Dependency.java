package com.theboreddev.either;

public interface Dependency<T> {
    Either<Exception, T> call();
}
