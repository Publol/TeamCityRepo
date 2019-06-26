package rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardEntityRequest implements AbstractEntity {

    @JsonProperty(value = "cookie")
    public String authKey;

    public boolean flag;

    public CardEntityRequest(String authKey){
        this.authKey = authKey;
        flag = true;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
