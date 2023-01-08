package com.theboreddev.either.overrides;

import com.theboreddev.either.Either;
import com.theboreddev.either.EitherType;
import com.theboreddev.either.Result;
import com.theboreddev.either.map.ResultWrapper;
import com.theboreddev.either.simple.FailureCall;
import com.theboreddev.either.simple.SuccessCall;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OverrideHandlersTest {

    @Test
    void shouldOverrideDefaultSuccessHandler() {

        SuccessCall success = new SuccessCall();

        Either<Exception, Result> result = success.call()
                .onSuccessDo(s -> new ResultWrapper(s.entity()))
                .onFailureDo(f -> new ResultWrapper(new Result("FAILED")))
                .build();

        assertThat(result.type()).isEqualTo(EitherType.SUCCESS);
        assertThat(result.get()).isEqualTo(new ResultWrapper(new Result("OK")));
    }

    @Test
    void shouldOverrideDefaultSuccessHandlerInSecondPlace() {

        SuccessCall success = new SuccessCall();

        Either<Exception, Result> result = success.call()
                .onFailureDo(f -> new ResultWrapper(new Result("FAILED")))
                .onSuccessDo(s -> new ResultWrapper(s.entity()))
                .build();

        assertThat(result.type()).isEqualTo(EitherType.SUCCESS);
        assertThat(result.get()).isEqualTo(new ResultWrapper(new Result("OK")));
    }

    @Test
    void shouldOverrideDefaultFailureHandler() {

        FailureCall failure = new FailureCall();

        Either<Exception, Result> result = failure.call()
                .onFailureDo(f -> new ResultWrapper(new Result("FAILED")))
                .onSuccessDo(s -> new ResultWrapper(s.entity()))
                .build();

        assertThat(result.type()).isEqualTo(EitherType.FAILURE);
        assertThat(result.get()).isEqualTo(new ResultWrapper(new Result("FAILED")));
    }

    @Test
    void shouldOverrideDefaultFailureHandlerInSecondPlace() {

        FailureCall failure = new FailureCall();

        Either<Exception, Result> result = failure.call()
                .onSuccessDo(s -> new ResultWrapper(s.entity()))
                .onFailureDo(f -> new ResultWrapper(new Result("FAILED")))
                .build();

        assertThat(result.type()).isEqualTo(EitherType.FAILURE);
        assertThat(result.get()).isEqualTo(new ResultWrapper(new Result("FAILED")));
    }
}
