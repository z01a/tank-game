package com.z01a.ecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Database {
    private final HashSet<Long> m_Entities = new HashSet<>();

    private final HashMap<Long, HashSet<Component>> m_Components = new HashMap<>();

    public boolean IsValidEntity(long entity)
    {
        return (entity != Entity.Invalid && m_Entities.contains(entity));
    }

    public long CreateEntity()
    {
        assert (Entity.Id + 1) != (Long.MAX_VALUE) : "[Database::CreateEntity] - We exceeded maximal number of entities!";

        long id = Entity.Id++;

        m_Entities.add(id);
        m_Components.put(id, new HashSet<>());

        return id;
    }

    public void DeleteEntity(long entity)
    {
        assert (entity != Entity.Invalid) : "[Database::DeleteEntity] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::DeleteEntity] - Entity doesn't exist!";

        m_Components.remove(entity);
        m_Entities.remove(entity);
    }

    public HashSet<Long> GetEntities()
    {
        assert (!m_Entities.isEmpty()) : "[Database::DeleteEntity] - There are no entities!";

        return m_Entities;
    }

    public <T extends Component> void CreateComponent(long entity, T component, Class<T> componentId)
    {
        assert (entity != Entity.Invalid) : "[Database::CreateComponent] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::CreateComponent] - Entity doesn't exist!";

        assert (!HasComponent(entity, componentId)) : "[Database::CreateComponent] - Component is already added!";

        m_Components.get(entity).add(component);
    }

    public <T extends Component> boolean HasComponent(long entity, Class<T> componentId)
    {
        assert (entity != Entity.Invalid) : "[Database::GetComponent] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::GetComponent] - Entity doesn't exist!";

        for (Component component : m_Components.get(entity))
        {
            if (component.getClass().getName().equals(componentId.getName()))
            {
                return true;
            }
        }

        return false;
    }

    public <T extends Component> HashSet<Component> GetComponents(long entity)
    {
        assert (entity == Entity.Invalid) : "[Database::GetComponents] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::GetComponents] - Entity doesn't exist!";

        return m_Components.get(entity);
    }

    public <T extends Component> T GetComponent(long entity, Class<T> componentId)
    {
        assert (entity != Entity.Invalid) : "[Database::GetComponent] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::GetComponent] - Entity doesn't exist!";

        for (Component component : m_Components.get(entity))
        {
            if (component.getClass().equals(componentId))
            {
                return (T) component;
            }
        }

        return null;
    }

    public <T extends Component> void DeleteComponent(long entity, Class<T> componentId)
    {
        assert (entity != Entity.Invalid) : "[Database::DeleteComponent] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::DeleteComponent] - Entity doesn't exist!";

        ArrayList<Component> componentsToDelete = new ArrayList<>();

        for (Component component : m_Components.get(entity))
        {
            if (component.getClass().equals(componentId))
            {
                componentsToDelete.add(component);
            }
        }

        for (Component component : componentsToDelete)
        {
            m_Components.get(entity).remove(component);
        }
    }

    public void Destroy()
    {
        m_Components.clear();
        m_Entities.clear();

        Entity.Id = 0;
    }
}
