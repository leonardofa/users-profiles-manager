package br.com.leonardo.estudo.usermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class UsersProfilesManagerApiApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    var app = new SpringApplication(UsersProfilesManagerApiApplication.class);
//        app.addListeners(new Base64ProtocolResolver());
    app.run(args);
  }

}
