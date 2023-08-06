package com.example.springdocgraal.parameterobject;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;

public class SpringDocRuntimeHints implements RuntimeHintsRegistrar {

  @Override
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
    Field methodsParameterContainingClassField = FieldUtils.getDeclaredField(MethodParameter.class, "containingClass", true);
    hints.reflection().registerField(methodsParameterContainingClassField);

    Field methodsParameterParameterTypeField = FieldUtils.getDeclaredField(MethodParameter.class, "parameterType", true);
    hints.reflection().registerField(methodsParameterParameterTypeField);
  }
}
