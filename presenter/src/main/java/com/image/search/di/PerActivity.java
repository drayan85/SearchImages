package com.image.search.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Paramanathan Ilandrayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
