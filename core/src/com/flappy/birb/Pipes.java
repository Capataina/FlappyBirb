package com.flappy.birb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Pipes {
    private static final float GAP_BETWEEN_PIPES = 125; // Customize this value

    private Vector2 positionTop, positionBottom; // Positions for top and bottom pipes
    private final float width;
    private float height;
    private float movementSpeed;

    public Pipes(int x, int y) {
        height = generateRandomHeight();
        positionTop = new Vector2(x, height + GAP_BETWEEN_PIPES + y); // Initially off-screen to the right
        positionBottom = new Vector2(x, y); // Below the gap
        width = 50; // Example width, you can customize
        movementSpeed = 100; // Example speed, adjust as needed

    }

    public void update(float delta) {
        positionTop.x -= movementSpeed * delta;
        positionBottom.x -= movementSpeed * delta;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.64f, 0.957f, 0.314f, 1); // Yellow color
        shapeRenderer.rect(positionTop.x, positionTop.y, this.width, Gdx.graphics.getHeight() - positionBottom.y - this.height - GAP_BETWEEN_PIPES);
        shapeRenderer.rect(positionBottom.x, positionBottom.y, this.width, this.height);
        shapeRenderer.end();
    }

    private float generateRandomHeight() {
        Random random = new Random();
        float minHeight = Gdx.graphics.getHeight() / 2 - GAP_BETWEEN_PIPES; // Example, adjust as needed
        float maxHeight = Gdx.graphics.getHeight() / 2 + GAP_BETWEEN_PIPES;
        return random.nextFloat() * (maxHeight - minHeight);
        // return minHeight;
    }

    public Rectangle UpperRectCollision() {
        return new Rectangle(positionTop.x, positionTop.y, this.width, Gdx.graphics.getHeight() - positionBottom.y - this.height - GAP_BETWEEN_PIPES);
    }

    public Rectangle BottomRectCollision() {
        return new Rectangle(positionBottom.x, positionBottom.y, this.width, this.height);
    }
}
