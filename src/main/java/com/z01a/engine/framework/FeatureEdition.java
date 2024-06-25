package com.z01a.engine.framework;

import com.z01a.ecs.Database;
import com.z01a.engine.managers.FeatureManager;
import com.z01a.engine.managers.SystemManager;

class GameplayFeatureSet {
    private final Database m_Database;

    public GameplayFeatureSet(Database database) {
        m_Database = database;
    }
}

public abstract class FeatureEdition {
    public SystemManager GetSystemManager() {
        return m_SystemManager;
    }

    private SystemManager m_SystemManager = null;


    public FeatureEdition(SystemManager systemManager) {
        m_SystemManager = systemManager;
        CollectFeatures();
    }

    public abstract void CollectFeatures();

    public abstract void CreateFeatures(GameplayFeatureSet gameplayFeatureSet);
}
