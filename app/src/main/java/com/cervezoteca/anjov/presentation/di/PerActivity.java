package com.cervezoteca.anjov.presentation.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by angelvazquez on 26/7/16.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
