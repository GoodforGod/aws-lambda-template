package io.lambda;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 21.3.2021
 */
public class Book {

    private String id;
    private String name;

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
