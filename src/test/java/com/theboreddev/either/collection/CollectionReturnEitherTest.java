package com.theboreddev.either.collection;

import com.theboreddev.either.Either;
import com.theboreddev.either.EitherType;
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

        assertThat(result.type()).isEqualTo(EitherType.SUCCESS);
        assertThat(result).isEqualTo(Either.success(data));
        assertThat(result.get()).isEqualTo(data);
    }

    @Test
    void shouldReturnAFailureWhenSomethingFailed() {

        CollectionDependency dependency = new FailureCollectionCall();

        Either<Exception, List<Result>> result = dependency.call();

        assertThat(result.type()).isEqualTo(EitherType.FAILURE);
        assertThat(result.get()).isInstanceOf(IllegalStateException.class);
        assertThat(result.failure().get().getMessage()).isEqualTo("Something went wrong!");
    }
}
