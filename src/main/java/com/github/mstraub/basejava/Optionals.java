package com.github.mstraub.basejava;
import java.util.Optional;

/**
 * Note / Warning: {@link Optional} wraps other {@link Optional}s!
 * 
 * @author mstraub
 *
 */
public class Optionals {

    public static void main(String[] args) {

        Object optional = Optional.of("string");
        Object emptyOptional = Optional.empty();
        Object string = "string";

        System.out.println(Optional.ofNullable(null));
        System.out.println(Optional.ofNullable(optional));
        System.out.println(Optional.ofNullable(emptyOptional));
        System.out.println(Optional.ofNullable(string));

    }

}
