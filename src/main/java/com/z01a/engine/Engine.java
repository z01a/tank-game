package com.z01a.engine;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Engine {

    public class Loop extends AnimationTimer {
        private Engine m_Engine = null;

        public Loop(Engine engine)
        {
            m_Engine = engine;
        }
        @Override
        public void handle(long l) {
            m_Engine.Update();
        }
    }

    private Loop m_Loop = null;

    private World m_World = null;

    private StageManager m_StageManager = null;
    private InputManager m_InputManager = null;
    public Engine(Stage stage) {
        m_Loop = new Loop(this);

        m_World = new World();

        m_InputManager = new InputManager();
        m_StageManager = new StageManager(stage);
    }

    public void Initialize() {
        m_World.Initialize();
        m_StageManager.Initialize();
        m_InputManager.Initialize();
    }

    private void Update() {
        m_World.Update();
        m_StageManager.Update();
        m_InputManager.Update();
    }

    public void UnInitialize() {
        m_InputManager.UnInitialize();
        m_StageManager.UnInitialize();
        m_World.UnInitialize();
    }

    public void Start() {
        m_Loop.start();
    }

    public void Stop() {
        m_Loop.stop();
    }
}
