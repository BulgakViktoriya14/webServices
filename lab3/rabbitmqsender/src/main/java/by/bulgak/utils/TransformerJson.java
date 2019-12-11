package by.bulgak.utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class TransformerJson implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object o) {
        return gson.toJson(o);
    }
}
