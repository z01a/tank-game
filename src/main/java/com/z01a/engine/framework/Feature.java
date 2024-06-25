package com.z01a.engine.framework;

import com.z01a.engine.managers.SystemManager;

class CollectSystemsContext {

}

class GameplayBeginContext {

}

public abstract class Feature {
    public SystemManager GetSystemManager() {
        return m_SystemManager;
    }

    private SystemManager m_SystemManager = null;

    public Feature(SystemManager systemManager) {
        m_SystemManager = systemManager;
        CollectSystems();
    }

    public abstract void CollectSystems();

    public abstract void OnCollectSystems(CollectSystemsContext context);
    public abstract void OnGameplayBegin(GameplayBeginContext context);
}
