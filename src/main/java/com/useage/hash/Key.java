package com.useage.hash;

import java.util.Objects;

public class Key {
    private Integer id;

    public Key(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Key key = (Key) o;
        return Objects.equals(id, key.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
