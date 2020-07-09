package org.warehouse.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.warehouse.model.Item;

import java.lang.reflect.Type;

public class JsonUtil {

    public static Page<Item> fromJson(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Pageable.class, new PageableInstanceCreator());
        Gson g = gsonBuilder.create();
        Type pageType = new TypeToken<PageImpl<Item>>() {
        }.getType();
        return g.fromJson(json, pageType);
    }

    private static class PageableInstanceCreator implements InstanceCreator<Pageable> {

        @Override
        public Pageable createInstance(Type type) {
            return null;
        }

    }

}
