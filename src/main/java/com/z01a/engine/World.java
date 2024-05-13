package com.z01a.engine;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class World {
    private Terrain m_Terrain = null;

    private Level m_Level = null;

    public World()
    {
        m_Terrain = new Terrain();
        m_Level = new Level();
    }

    public void Initialize() {
    }

    public void UnInitialize() {
    }

    public void Update() {
    }
}
