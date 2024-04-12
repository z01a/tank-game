package com.z01a.engine;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class World {
    private Terrain m_Terrain = null;

    private Level m_Level = null;

    private EntityManager m_EntityManager = null;

    public World()
    {
        m_Terrain = new Terrain();
        m_Level = new Level();
        m_EntityManager = new EntityManager();
    }

    public void Initialize() {
    }

    public void UnInitialize() {
    }

    public void Update() {
    }
}
