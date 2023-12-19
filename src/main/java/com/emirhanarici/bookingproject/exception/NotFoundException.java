package com.emirhanarici.bookingproject.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public abstract class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -117658738198299825L;

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    /**
     * Constructs a {@code NotFoundException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    protected NotFoundException(String message) {
        super(message);
    }

}
