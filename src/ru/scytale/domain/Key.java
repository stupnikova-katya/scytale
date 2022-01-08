package ru.scytale.domain;

import java.io.Serializable;

/**
 * Ключ скиталы.
 */
public class Key implements Serializable {

    private Integer n;

    private Integer m;

    public Key(Integer n, Integer m) {
        this.n = n;
        this.m = m;
    }

    public Key(int[] keyPair) {
        this.n = keyPair[0];
        this.m = keyPair[1];
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return n + " " + m;
    }
}
