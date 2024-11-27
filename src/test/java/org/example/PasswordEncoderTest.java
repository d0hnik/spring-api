package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void producesHashFromPlaintext() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        String hash = encoder.encode("admin");

        System.out.println(hash);
    }

}
