package com.z01a.engine;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class World {
    private Group m_Root = new Group();

    private Level m_Level = null;

    public World()
    {
        m_Root.getChildren().add(new Circle(50,50,50));
    }

    public Group GetRoot() {
        return m_Root;
    }

    public void Initialize() {
    }

    public void UnInitialize() {
    }
}
