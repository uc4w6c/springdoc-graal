package com.example.springdocgraal.parameterobject;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springdoc.core.extractor.DelegatingMethodParameter;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("parameter/object/reflection")
public class ReflectionController {
  @GetMapping
  public String index() {
    List<MethodParameter> methodParameterList = Stream.of(ParameterObjectController.ParameterObjectRequest.class.getRecordComponents())
        .filter(d -> d.getName().equals("name"))
        .map(RecordComponent::getAccessor)
        .map(method -> new MethodParameter(method, -1))
        .toList();

    MethodParameter result = methodParameterList.get(0).clone();
    try {
      Field containingClassField = FieldUtils.getDeclaredField(result.getClass(), "containingClass", true);
      System.out.println("containingClassField:" + containingClassField.toString());
      containingClassField.set(result, ParameterObjectController.ParameterObjectRequest.class);
      Field parameterTypeField = FieldUtils.getDeclaredField(result.getClass(), "parameterType", true);
      System.out.println("parameterTypeField:" + parameterTypeField.toString());
      parameterTypeField.set(result, null);
    }
    catch (IllegalAccessException e) {
      System.out.println("Error");
    }
    return "Test";
  }
}
