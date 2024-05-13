package com.z01a.engine;

import com.z01a.engine.framework.Feature;
import com.z01a.engine.framework.FeatureEdition;
import com.z01a.engine.managers.FeatureManager;
import com.z01a.engine.managers.InputManager;
import com.z01a.engine.managers.SceneManager;
import com.z01a.engine.managers.SystemManager;
import com.z01a.engine.systems.ViewSystem;
import com.z01a.tankgame.TankGame;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

class EngineFeature extends Feature {
    public EngineFeature(SystemManager systemManager) {
        super(systemManager);
    }

    @Override
    public void CollectSystems() {
        GetSystemManager().RegisterSystem(new ViewSystem(), ViewSystem.class);
    }
}

public class Engine extends FeatureEdition {
    @Override
    public void CollectFeatures() {
        new EngineFeature(GetSystemManager()).CollectSystems();
    }

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

    private Game m_Game = null;
    private SceneManager m_SceneManager = null;
    private InputManager m_InputManager = null;
    private SystemManager m_SystemManager = null;
    public Engine(Stage stage) {
        super(new SystemManager());

        m_Loop = new Loop(this);

        m_World = new World();

        m_InputManager = new InputManager();
        m_SceneManager = new SceneManager(stage);

        m_SystemManager = GetSystemManager();
        m_Game = new TankGame(GetSystemManager());
    }

    public void Initialize() {
        m_World.Initialize();
        m_SceneManager.Initialize();
        m_InputManager.Initialize();
        m_SystemManager.Initialize();
    }

    private void Update() {
        m_World.Update();
        m_SceneManager.Update();
        m_InputManager.Update();
        m_SystemManager.Update();
    }

    public void UnInitialize() {
        m_SystemManager.UnInitialize();
        m_InputManager.UnInitialize();
        m_SceneManager.UnInitialize();
        m_World.UnInitialize();
    }

    public void Start() {
        m_Loop.start();
    }

    public void Stop() {
        m_Loop.stop();
    }
}
