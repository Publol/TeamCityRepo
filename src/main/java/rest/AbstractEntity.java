package rest;

public class AbstractEntity {

    private String body;

    protected AbstractEntity(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
