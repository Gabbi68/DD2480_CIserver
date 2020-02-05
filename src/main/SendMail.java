package main;
<<<<<<< Updated upstream
=======

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.AuthenticationException;
import java.util.Properties;

// source: https://javaee.github.io/javamail/
// source: https://github.com/eclipse-ee4j/jaf/releases
>>>>>>> Stashed changes

public class SendMail {
  private String email;

  public SendMail(String address) {
    email = address;
  }

  public String setEmail(String address) {
    email = address;
  }

  public String getEmail() {
    return email;
  }
}
