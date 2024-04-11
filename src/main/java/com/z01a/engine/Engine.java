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

    private Stage m_Stage = null;

    private Loop m_Loop = null;

    private World m_World = null;

    private InputManager m_InputManager = null;
    public Engine(Stage stage) {
        m_Stage = stage;

        m_Loop = new Loop(this);
        m_World = new World();
        m_InputManager = new InputManager();
    }

    public void Initialize() {
        m_World.Initialize();

        Scene scene = new Scene(m_World.GetRoot(), 320, 240);

        scene.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                m_InputManager.Handle(mouseEvent);
            }
        });

        scene.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                m_InputManager.Handle(keyEvent);
            }
        });

        m_Stage.setTitle("TankGame!");
        m_Stage.setScene(scene);
        m_Stage.show();
    }

    private void Update() {
        System.out.println("Update!");
//        m_InputManager.Update();
//        m_World.Update();
    }

    public void UnInitialize() {
        m_World.UnInitialize();

        m_Stage.close();
    }

    public void Start() {
        m_Loop.start();
    }

    public void Stop() {
        m_Loop.stop();
    }
}
