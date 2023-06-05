package ru.itis.semesterworkspring.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@NoArgsConstructor
@Component
public class ConfirmationCodeGenerator {
    private int code = 0;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void generateCode(){
        Random random = new Random();
        setCode(random.nextInt(9000) + 1000);
    }
}
