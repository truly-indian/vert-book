package com.deepak.rapido.firstProject;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MainVerticle extends AbstractVerticle {
  private final inMemoryBookStore store = new inMemoryBookStore();
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //System.out.println(store.getAll().encode());
    Router books = Router.router(vertx);
    books.route().handler(BodyHandler.create());
    //This is the get all books router handler
    books.get("/books").handler(req -> req.response().putHeader(HttpHeaders.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON)
       .end(store.getAll().encode()));

    //This is the Homepage router handler
    books.get("/").handler(req -> req.response().putHeader(HttpHeaders.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON)
      .end(new JsonObject().put("message","This is the homepage").encode()));

    // this is the post books router handler
    books.post("/books").handler(req -> {
       //read the body
      final JsonObject requestBody =  req.getBodyAsJson();
      store.add(requestBody.mapTo(Book.class));
      System.out.println("Request body received is: "+ requestBody);
      req.response().putHeader(HttpHeaders.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON)
        .end(requestBody.encode());

    });
    vertx.createHttpServer().requestHandler(books).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }
}
