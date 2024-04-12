package com.z01a.engine;

import javafx.stage.Stage;

public class StageManager {
    private Stage m_Stage = null;

    private SceneManager m_SceneManager = null;

    public StageManager(Stage stage)
    {
        m_Stage = stage;

        m_SceneManager = new SceneManager();
    }

    public void UnInitialize() {
        m_Stage.close();
    }

    public void Initialize()
    {
        m_Stage.setScene(m_SceneManager.GetScene());
        m_Stage.setTitle("Tank Game");
        m_Stage.show();
    }

    public void Update() {
    }
}
