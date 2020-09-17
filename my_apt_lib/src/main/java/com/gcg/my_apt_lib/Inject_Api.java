package com.gcg.my_apt_lib;


import com.gcg.my_an_lib.Inject;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class Inject_Api extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Inject.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        MethodSpec get = MethodSpec.methodBuilder("get")
                .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                .returns(String.class)
                .addStatement("return"+"\"Hello World!\"")
                .build();
        TypeSpec helloWorld = TypeSpec.classBuilder("YsTest")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(get)
                .build();
        JavaFile javaFile = JavaFile.builder("yang.shuai", helloWorld)
                .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
