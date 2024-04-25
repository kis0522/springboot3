package com.example.VaildationSample.validator;

import com.example.VaildationSample.form.CalcForm;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class CalcValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        //체크 대상인지 아닌지 확인
        return CalcForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CalcForm form = (CalcForm) target;

        if(form.getLeftNum() != null && form.getRightNum() != null){
            if(!((form.getLeftNum() % 2 == 1) && (form.getRightNum() % 2 == 0))){
                errors.reject("com.example.ValidationSample.validator.CalcValidator.message");
            }
        }
    }
}
