package com.example.mobile.component;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandling implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        /*ErrorPage errorPage401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/err");//401에러가 났을경우 /err의 경로를 호출한다.( 서버주소 + /error-page/400의 URL을 요청한다는뜻)
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/err");//404에러가 났을경우 /err의 경로를 호출한다.( 서버주소 + /error-page/400의 URL을 요청한다는뜻)
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/err");

        ErrorPage errorPageEX = new ErrorPage(RuntimeException.class, "/err"); //RuntimeException가 발생했을경우에도 설정가능

        factory.addErrorPages(errorPage401, errorPage404, errorPage500, errorPageEX); //에러페이지들을 등록한다.*/
    }
}
