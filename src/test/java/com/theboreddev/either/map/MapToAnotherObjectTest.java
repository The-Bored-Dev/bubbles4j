package com.theboreddev.either.map;

import com.theboreddev.either.Either;
import com.theboreddev.either.EitherType;
import com.theboreddev.either.Result;
import com.theboreddev.either.simple.FailureCall;
import com.theboreddev.either.simple.SimpleDependency;
import com.theboreddev.either.simple.SuccessCall;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapToAnotherObjectTest {

    @Test
    void shouldTransformEitherToADifferentObject() {

        final SimpleDependency success = new SuccessCall();

        Either<Exception, ResultWrapper> result = success.call().map(either -> new ResultWrapper(either.success().entity()));

        assertThat(result.type()).isEqualTo(EitherType.SUCCESS);
        assertThat(result).isEqualTo(Either.success(new ResultWrapper(new Result("OK"))));
        assertThat((result.success().entity())).isEqualTo(new ResultWrapper(new Result("OK")));
    }

    @Test
    void shouldPropagateFailureAndDoNothingOnMap() {

        final SimpleDependency failure = new FailureCall();

        Either<Exception, ResultWrapper> result = failure.call().map(either -> new ResultWrapper(either.success().entity()));

        assertThat(result.type()).isEqualTo(EitherType.FAILURE);
        assertThat(result.failure().exception()).isInstanceOf(IllegalStateException.class);
        assertThat(result.failure().exception().getMessage()).isEqualTo("Something went wrong!");
    }
}
