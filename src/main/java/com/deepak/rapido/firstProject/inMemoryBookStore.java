package com.deepak.rapido.firstProject;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class inMemoryBookStore {
  private Map<Long, Book> books = new HashMap<>();
  inMemoryBookStore() {
    books.put(1L, new Book(1L,"Learning vert.x with java"));
    books.put(2L, new Book(2L,"This is teh second book"));
  }
  public JsonArray getAll() {
    JsonArray all = new JsonArray();
    books.values().forEach(book -> {
      all.add(JsonObject.mapFrom(book));
    });
    return all;
  }

  public void add(final Book entry) {
     books.put(entry.getIsbn(), entry);
  }
}
