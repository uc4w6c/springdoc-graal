package com.example.springdocgraal.parameterobject;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://github.com/springdoc/springdoc-openapi/issues/2312
 * リフレクションでParameterObjectのメソッドを取得していたが、GraalVMのHintsに追加されていなかったためエラーが発生していた
 * 参考: https://spring.pleiades.io/spring-boot/docs/current/reference/html/native-image.html#native-image.advanced.custom-hints
 */
@RestController
@RequestMapping("parameter/object")
public class ParameterObjectController {
  // @GetMapping
  public ParameterObjectRequest index(@ParameterObject ParameterObjectRequest request) {
    System.out.println(request);
    return request;
  }

  public record ParameterObjectRequest(String name, int age) {}
}
