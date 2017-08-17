package cm.it.secret.gasmyr.toolkit.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gasmyr.mougang on 5/30/17.
 */

public class GreetingMessage {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GreetingMessage() {

    }

    public GreetingMessage(String message) {

        this.message = message;
    }
    private List<GreetingMessage> getData(){
        List<GreetingMessage> messages=new ArrayList<>();
        messages.add(new GreetingMessage());

        return messages;

    }
}
