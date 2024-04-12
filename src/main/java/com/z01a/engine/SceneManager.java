package com.z01a.engine;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

public class SceneManager {
    private Group m_Root = null;
    private Scene m_Scene = null;
    public SceneManager()
    {
        m_Root = new Group();

        m_Scene = new Scene(m_Root, 320, 420);
    }

    public void Add(Node node)
    {
        m_Root.getChildren().add(node);
    }

    public void Remove(Node node)
    {
        m_Root.getChildren().remove(node);
    }

    public Scene GetScene()
    {
        return m_Scene;
    }
}
