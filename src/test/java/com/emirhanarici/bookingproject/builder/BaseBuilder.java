package com.emirhanarici.bookingproject.builder;

import lombok.SneakyThrows;

public class BaseBuilder<T> {


    @SneakyThrows
    public BaseBuilder(Class<T> clazz) {
        this.data = clazz.getDeclaredConstructor().newInstance();
    }

    T data;


    public T build() {
        return data;
    }

}
