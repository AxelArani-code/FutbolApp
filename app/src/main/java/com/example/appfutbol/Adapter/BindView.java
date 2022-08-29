package com.example.appfutbol.Adapter;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import androidx.annotation.IdRes;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) @Target(FIELD)
public @interface BindView {
    /** View ID to which the field will be bound. */
    @IdRes int value();
}
