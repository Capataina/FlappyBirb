package com.flappy.birb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class FlappyTheBirb {
    private Vector2 position;
    private float radius; // Size of the bird represented as a dot
    private float gravity;
    private float birdVelocity;

    public FlappyTheBirb(int x, int y, float radius, float gravity, float birdVelocity) {
        position = new Vector2(x, y);
        this.radius = radius; // Set the size of the dot
        this.gravity = gravity;
        this.birdVelocity = birdVelocity;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }

    // This method will be called from your render method in the main game screen
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 0, 1); // Yellow color
        shapeRenderer.circle(position.x, position.y, radius);
        shapeRenderer.end();
    }

    public void BirbControls(float delta) {
        float downwardAcceleration = gravity;
        birdVelocity -= downwardAcceleration * delta;
        birdVelocity *= 0.96f;

        this.position.y += birdVelocity * delta;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            birdVelocity += 500;
        }

        // System.out.println(birdVelocity);
    }

    // Helper method for getting the circular bounds of the bird
    public Circle getBoundingCircle() {
        return new Circle(position.x, position.y, radius);
    }

}
