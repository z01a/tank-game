package com.z01a.tankgame;

import com.z01a.engine.Game;
import com.z01a.engine.framework.Feature;
import com.z01a.engine.framework.FeatureEdition;
import com.z01a.engine.managers.SystemManager;

class GameFeature extends Feature {
    public GameFeature(SystemManager systemManager) {
        super(systemManager);
    }

    @Override
    public void CollectSystems() {
//        m_SystemManager.RegisterSystem(new MoveSystem(), MoveSystem.class);
    }
}

public class TankGame extends FeatureEdition implements Game {
    public TankGame(SystemManager systemManager) {
        super(systemManager);
    }

    @Override
    public void CollectFeatures() {
        new GameFeature(GetSystemManager()).CollectSystems();
    }
}
