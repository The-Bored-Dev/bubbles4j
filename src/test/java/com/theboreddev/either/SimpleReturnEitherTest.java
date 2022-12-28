package com.theboreddev.either;

import com.theboreddev.either.Either.EitherType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleReturnEitherTest {

    @Test
    void shouldHandleASuccessfulResponseAccordingly() {

        final Dependency success = new SuccessCall();

        Either<Exception, Result> result = success.call();

        assertThat(result).isEqualTo(Either.success(new Result("OK")));
        assertThat(result.type()).isEqualTo(EitherType.SUCCESS);
        assertThat(((Either.Success<Exception, Result>)result).entity()).isEqualTo(new Result("OK"));
    }

    @Test
    void shouldHandleAFailureResponseAccordingly() {

        final Dependency failure = new FailureCall();

        Either<Exception, Result> result = failure.call();

        assertThat(result).isEqualTo(Either.failure(new IllegalStateException("Something went wrong!")));
        assertThat(result.type()).isEqualTo(EitherType.FAILURE);
        assertThat(((Either.Failure<Exception, Result>)result).exception()).isInstanceOf(IllegalStateException.class);
    }
}
