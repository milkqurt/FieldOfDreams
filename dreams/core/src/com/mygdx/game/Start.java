package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.boards.*;
import com.mygdx.game.game.Letter;
import com.mygdx.game.game.Task;
import com.mygdx.game.textures.Background;
import com.mygdx.game.textures.Dram;

public class Start extends ApplicationAdapter {
    SpriteBatch batch;
    Dram dram;
    Background background;
    LettersBoard lettersBoard;
    Board board;
    QuestionBoard questionBoard;
    ScoreBoard scoreBoard;
    TVHost tvHost;
    private Viewport viewport;
    private Vector2 coord;

    @Override
    public void create() {
        coord = new Vector2();
        viewport = new FitViewport(800, 600);
        batch = new SpriteBatch();
        dram = new Dram(200, 200, 160);
        background = new Background();
        lettersBoard = new LettersBoard(535, 10, 8);
        board = new Board(50, 510);
        board.setHiddenWord(Task.getAnswer(0));
        questionBoard = new QuestionBoard(400, 480, 24);
        questionBoard.setQuestion(Task.getQuestion(0));
        scoreBoard = new ScoreBoard(400, 550);
        tvHost = new TVHost(600, 200);
    }

    @Override
    public void render() {
        update();
        ScreenUtils.clear(1, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        dram.render(batch);
        lettersBoard.render(batch);
        board.render(batch);
        questionBoard.render(batch);
        scoreBoard.render(batch);
        tvHost.render(batch);
        batch.end();
    }

    public void update() {
        dram.update();
        tvHost.update();
        if (Gdx.input.justTouched()) {
            System.out.println(getClickCoord().x + " " + getClickCoord().y);

            if (dram.isPress(getClickCoord().x, getClickCoord().y)) {
                dram.twist(300);
            }

            if (lettersBoard.isPress(getClickCoord()) && (lettersBoard.isEnable())) {
                lettersBoard.setEnable(false);
                int index = lettersBoard.getIndex(getClickCoord());
                lettersBoard.openLetter(index);
                scoreBoard.calculateScore(dram.getIndex(), board.numberOfChar(Letter.getChars().charAt(index)));
                scoreBoard.setScoreText("СЧЕТ: " + scoreBoard.getScore());
                board.openChar(Letter.getChars().charAt(index));
            }
        }
        if (dram.isStopped()) {
            lettersBoard.setEnable(true);
            tvHost.show(180, dram.getIndex());
            System.out.println("dram stopped ");
            System.out.println(dram.getIndex());
        }

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width, height, true);
        getViewport().apply();
    }

    public Vector2 getClickCoord() {
        getViewport().unproject(coord.set(Gdx.input.getX(), Gdx.input.getY()));
        return coord;
    }

    public Viewport getViewport() {
        return viewport;
    }
}
