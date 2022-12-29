package com.theboreddev.either.stream;

import com.theboreddev.either.Either;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamReturnEitherTest {

    @Test
    void shouldReturnAStreamSuccessfully() {

        final Stream<Integer> data = Stream.of(1, 2, 3, 4, 5);

        StreamDependency dependency = new SuccessStreamDependency(data);

        Either<Exception, Stream<Integer>> result = dependency.call();

        assertThat(result.type()).isEqualTo(Either.EitherType.SUCCESS);
        assertThat(result.success().entity().toList()).isEqualTo(List.of(1, 2, 3, 4, 5));
    }

    @Test
    void shouldReturnAFailureWhenStreamCouldNotBeRetrieved() {

        StreamDependency dependency = new FailureStreamDependency();

        Either<Exception, Stream<Integer>> result = dependency.call();

        assertThat(result.type()).isEqualTo(Either.EitherType.FAILURE);
        assertThat(result).isEqualTo(Either.failure(new IllegalStateException("Something went wrong!")));
    }
}
