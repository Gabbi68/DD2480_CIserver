package main;

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
