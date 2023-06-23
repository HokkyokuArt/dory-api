package moda.peon.tools.doryapi;

import lombok.extern.slf4j.Slf4j;
import moda.peon.tools.doryapi.exceptions.ErrorMessage;
import moda.peon.tools.doryapi.exceptions.UUIDNotProvided;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "moda.peon.tools.doryapi")
@Slf4j
public class ExceptionTranslator {


    @ExceptionHandler(UUIDNotProvided.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage processError(UUIDNotProvided ex){
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage processGeneralException(Throwable ex) {
        return new ErrorMessage(
                ex.getClass().getSimpleName(),
                ex.getLocalizedMessage(),
                Arrays.stream(ex.getStackTrace())
                        .filter(s -> s.getClassName().startsWith("moda.peon"))
                        .limit(3L)
                        .map(s -> s.getClassName() + ": " + s.getLineNumber())
                        .collect(Collectors.toList()));
    }
}
