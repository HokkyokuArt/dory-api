package moda.peon.tools.doryapi.exceptions;

import lombok.Getter;

@Getter
public class ErrorMessage {
    private final String code;
    private final String description;
    private final Object[] extra;

    public ErrorMessage(String code, String description, Object... extra) {
        this.code = code;
        this.description = description;
        this.extra = extra;
    }
}
