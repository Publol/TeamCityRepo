package rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginEntity implements AbstractEntity  {

    @JsonProperty("username")
    private String login;

    @JsonProperty
    private String password;

    public LoginEntity(){

    }

    public LoginEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }


}
