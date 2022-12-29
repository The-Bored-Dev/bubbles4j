package com.theboreddev.either.collection;

import com.theboreddev.either.Either;
import com.theboreddev.either.Result;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionReturnEitherTest {

    @Test
    void shouldReturnAnEitherContainingACollection() {

        List<Result> data = List.of(new Result("1"), new Result("2"), new Result("3"));

        SuccessCollectionCall dependency = new SuccessCollectionCall(data);

        Either<Exception, List<Result>> result = dependency.call();

        assertThat(result.type()).isEqualTo(Either.EitherType.SUCCESS);
        assertThat(result).isEqualTo(Either.success(data));
    }

    @Test
    void shouldReturnAFailureWhenSomethingFailed() {

        CollectionDependency dependency = new FailureCollectionCall();

        Either<Exception, List<Result>> result = dependency.call();

        assertThat(result.type()).isEqualTo(Either.EitherType.FAILURE);
        assertThat(result).isEqualTo(Either.failure(new IllegalStateException("Something went wrong!")));
    }
}
