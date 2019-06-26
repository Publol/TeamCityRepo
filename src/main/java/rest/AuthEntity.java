package rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthEntity implements AbstractEntity {

    @JsonProperty("Auth_token")
    public String authKey;

    public AuthEntity(String authKey) {
        this.authKey = authKey;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
