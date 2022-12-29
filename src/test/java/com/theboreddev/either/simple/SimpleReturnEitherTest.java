package com.theboreddev.either.simple;

import com.theboreddev.either.Either;
import com.theboreddev.either.Either.EitherType;
import com.theboreddev.either.Result;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleReturnEitherTest {

    @Test
    void shouldHandleASuccessfulResponseAccordingly() {

        final SimpleDependency success = new SuccessCall();

        Either<Exception, Result> result = success.call();

        assertThat(result.type()).isEqualTo(EitherType.SUCCESS);
        assertThat(result).isEqualTo(Either.success(new Result("OK")));
        assertThat((result.success().entity())).isEqualTo(new Result("OK"));
    }

    @Test
    void shouldHandleAFailureResponseAccordingly() {

        final SimpleDependency failure = new FailureCall();

        Either<Exception, Result> result = failure.call();

        assertThat(result.type()).isEqualTo(EitherType.FAILURE);
        assertThat(result).isEqualTo(Either.failure(new IllegalStateException("Something went wrong!")));
        assertThat(result.failure().exception()).isInstanceOf(IllegalStateException.class);
    }
}
