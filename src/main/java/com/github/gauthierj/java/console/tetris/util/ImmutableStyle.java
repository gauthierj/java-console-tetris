package com.github.gauthierj.java.console.tetris.util;

import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.PACKAGE, ElementType.ANNOTATION_TYPE})
@Value.Style(jdkOnly = true, get = {"*"}, typeImmutable = "*Impl")
public @interface ImmutableStyle {
}
