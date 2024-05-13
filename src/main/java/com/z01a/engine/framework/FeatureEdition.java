package com.z01a.engine.framework;

import com.z01a.engine.managers.FeatureManager;
import com.z01a.engine.managers.SystemManager;

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
}
