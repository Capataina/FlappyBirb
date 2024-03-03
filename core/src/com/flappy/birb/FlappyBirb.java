package com.flappy.birb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


public class FlappyBirb extends Game {

    private FlappyTheBirb bird;
    private Ground ground;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont gameOverFont;
    // private float numberOfCollisions;
    private List<Pipes> listOfPipes;
    private float timeSinceLastPipe;
    private boolean gameEnded = false;

    @Override
    public void create() {
        bird = new FlappyTheBirb(100, 300, 10, 900, 0); // Example position and size
        ground = new Ground(0, 0);
        shapeRenderer = new ShapeRenderer();
        gameOverFont = new BitmapFont();
        batch = new SpriteBatch();
        listOfPipes = new ArrayList<>();
        timeSinceLastPipe = 0;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.31f, 0.73f, 0.77f, 1); // Clear the screen with black

        timeSinceLastPipe += Gdx.graphics.getDeltaTime();

        if (timeSinceLastPipe > 2) {  // Spawn every 2 seconds
            listOfPipes.add(new Pipes(Gdx.graphics.getWidth(), (int) ground.getTopPoint()));  // Add a new pipe
            timeSinceLastPipe = 0;
        }

        for (Pipes pipe : listOfPipes) {
            pipe.update(Gdx.graphics.getDeltaTime());
            pipe.draw(shapeRenderer);

            if (hasCollidedWithUpperPipe(pipe) || hasCollidedWithBottomPipe(pipe)) {
                GameEnd();
            }
        }

        bird.draw(shapeRenderer); // Draw the bird as a yellow dot
        ground.draw(shapeRenderer);

        if (!gameEnded) {
            bird.BirbControls(Gdx.graphics.getDeltaTime());
        } else {
            GameEnd();
        }

        if (hasCollidedWithGround()) {
            GameEnd();
        }

    }

    private boolean hasCollidedWithGround() {
        // Using Intersector for more flexible overlap checks in the future
        return Intersector.overlaps(bird.getBoundingCircle(), ground.getBoundingRect());
    }

    private boolean hasCollidedWithUpperPipe(Pipes pipe) {
        return Intersector.overlaps(bird.getBoundingCircle(), pipe.UpperRectCollision());
    }

    private boolean hasCollidedWithBottomPipe(Pipes pipe) {
        return Intersector.overlaps(bird.getBoundingCircle(), pipe.BottomRectCollision());
    }

    private void GameEnd() {
        gameEnded = true;
        shapeRenderer.end();
        gameOverFont.getData().setScale(3.0f);
        GlyphLayout layout = new GlyphLayout(gameOverFont, "You Died!");
        float textWidth = layout.width;
        batch.begin();
        gameOverFont.draw(batch, "you ded lmao gg", (float) Gdx.graphics.getWidth() / 2 - textWidth, (float) Gdx.graphics.getHeight() / 2);
        batch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        gameOverFont.dispose();
        batch.dispose();
    }
}
