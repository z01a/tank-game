package com.z01a.engine.managers;

import com.z01a.engine.Manager;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager implements Manager {
    private Stage m_Stage = null;

    private Group m_Root = null;

    private Scene m_Scene = null;

    public SceneManager(Stage stage)
    {
        m_Stage = stage;

        m_Root = new Group();
        m_Scene = new Scene(m_Root, 320, 420);
    }

    @Override
    public void UnInitialize() {
        m_Stage.close();
    }

    @Override
    public void Initialize()
    {
        m_Stage.setScene(m_Scene);
        m_Stage.setTitle("Tank Game");
        m_Stage.show();
    }

    public void Add(Node node) { m_Root.getChildren().add(node); }

    public void Remove(Node node)
    {
        m_Root.getChildren().remove(node);
    }

    public Scene GetScene()
    {
        return m_Scene;
    }

    @Override
    public void Update() {
    }
}
