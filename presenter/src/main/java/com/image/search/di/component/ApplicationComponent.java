package com.image.search.di.component;

import com.image.search.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 *
 * @author Pramanathan Ilandrayan <theebankala@gmail.com>
 * @version 1.0.0
 * @since 12th of July 2021
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

}
