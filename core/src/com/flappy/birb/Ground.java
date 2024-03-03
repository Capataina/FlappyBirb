package com.flappy.birb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ground {
    private Vector2 position;
    private final float width = Gdx.graphics.getWidth();
    private final float height = 50f;

    public Ground(int x, int y) {
        position = new Vector2(x, y);
    }

    public float getTopPoint() {
        return this.position.y + this.height;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.85f, 0.95f, 0.52f, 1); // Yellow color
        shapeRenderer.rect(position.x, position.y, this.width, this.height);
        shapeRenderer.end();
    }

    public Rectangle getBoundingRect() {
        return new Rectangle(this.position.x, this.position.y, width, height);
    }
}
