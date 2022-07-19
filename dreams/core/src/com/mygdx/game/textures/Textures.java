package com.mygdx.game.textures;

import com.badlogic.gdx.graphics.Texture;

public class Textures {
    private static Texture dram = new Texture("baraban512.png");
    private static Texture arrow = new Texture("strelka.png");
    private static Texture background = new Texture("background64.png");
    private static Texture font32 = new Texture("font32.png");
    private static Texture person = new Texture("person.png");
    private static Texture words = new Texture("ww.png");

    public static Texture getArrow() {
        return arrow;
    }

    public static Texture getBackground() {
        return background;
    }

    public static Texture getFont32() {
        return font32;
    }

    public static Texture getPerson() {
        return person;
    }

    public static Texture getWords() {
        return words;
    }

    public static Texture getDram() {
        return dram;
    }
}
