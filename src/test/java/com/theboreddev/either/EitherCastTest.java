package com.theboreddev.either;

import com.theboreddev.either.simple.FailureCall;
import com.theboreddev.either.simple.SimpleDependency;
import com.theboreddev.either.simple.SuccessCall;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EitherCastTest {

    @Test
    void shouldGetSuccessInstanceFromEither() {

        SimpleDependency dependency = new SuccessCall();

        Either<Exception, Result> result = dependency.call();

        assertThat(result.success()).isEqualTo(Either.success(new Result("OK")));
    }

    @Test
    void shouldThrowExceptionWhenTryingToConvertASuccessToFailure() {

        SimpleDependency dependency = new SuccessCall();

        Either<Exception, Result> result = dependency.call();

        assertThatThrownBy(result::failure)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Instance is not of type " + Failure.class.getSimpleName());
    }

    @Test
    void shouldGetFailureInstanceFromEither() {

        SimpleDependency dependency = new FailureCall();

        Either<Exception, Result> result = dependency.call();

        assertThat(result.type()).isEqualTo(EitherType.FAILURE);
        assertThat(result.failure().exception()).isInstanceOf(IllegalStateException.class);
        assertThat(result.failure().exception().getMessage()).isEqualTo("Something went wrong!");
    }

    @Test
    void shouldThrowExceptionWhenTryingToConvertAFailureToSuccess() {

        SimpleDependency dependency = new FailureCall();

        Either<Exception, Result> result = dependency.call();

        assertThatThrownBy(result::success)
                .isInstanceOf(IllegalStateException.class)
                        .hasMessage("Instance is not of type " +Success.class.getSimpleName());
    }
}
