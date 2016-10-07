package com.cervezoteca.anjov.domain.exception;

/**
 * Created by angelvazquez on 25/7/16.
 */

public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();
}
