package com.z01a.engine.managers;

import com.z01a.ecs.Database;
import com.z01a.ecs.ISystem;
import com.z01a.engine.Manager;

import java.util.ArrayList;

public class SystemManager implements Manager {
    private final Database m_Database = new Database();
    private final ArrayList<ISystem> m_Systems = new ArrayList<>();

    public <T extends ISystem> void RegisterSystem(T system, Class<T> systemId)
    {
        assert (!IsSystemRegistered(systemId)) : "[SystemManager::RegisterSystem] - System is already registered!";
        m_Systems.add(system);
    }

    @Override
    public void Update()
    {
        for (ISystem system : m_Systems)
        {
            system.Execute();
        }
    }

    private <T extends ISystem> boolean IsSystemRegistered(Class<T> systemId)
    {
        for (ISystem system : m_Systems)
        {
            if (system.getClass().getName().equals(systemId.getName()))
            {
                return true;
            }
        }

        return false;
    }

    public <T extends ISystem> void UnRegisterSystem(Class<T> systemId)
    {
        assert (IsSystemRegistered(systemId)) : "[SystemManager::UnRegisterSystem] - System was not registered!";

        ArrayList<ISystem> systemsToUnRegister = new ArrayList<>();

        for (ISystem system : m_Systems)
        {
            if (system.getClass().getName().equals(systemId.getName()))
            {
                systemsToUnRegister.add(system);
            }
        }

        for (ISystem system : systemsToUnRegister)
        {
            m_Systems.remove(system);
        }
    }

    @Override
    public void Initialize() {
    }

    @Override
    public void UnInitialize() {
    }
}
