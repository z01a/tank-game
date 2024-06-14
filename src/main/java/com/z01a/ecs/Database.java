package com.z01a.ecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Database {
    private final HashSet<Long> m_Entities = new HashSet<>();

    private final HashMap<Long, HashSet<IComponent>> m_Components = new HashMap<>();

    public boolean IsValidEntity(long entity)
    {
        return (entity != Entity.Invalid && m_Entities.contains(entity));
    }

    public long CreateEntity()
    {
        assert (Entity.Id + 1) != (Long.MAX_VALUE) : "[Database::CreateEntity] - We exceeded maximal number of entities!";

        long id = Entity.Id++;

//        AddDelayedCommand(new CreateEntityCommand(id));

        return id;
    }

    protected void CreatePendingEntity(long id)
    {
        m_Entities.add(id);
        m_Components.put(id, new HashSet<>());
    }

    public void DeleteEntity(long entity)
    {
        assert (entity != Entity.Invalid) : "[Database::DeleteEntity] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::DeleteEntity] - Entity doesn't exist!";

        m_Components.remove(entity);
        m_Entities.remove(entity);

//        AddDelayedCommand(new DeleteEntityCommand(entity));
    }

    protected void DeletePendingEntity(long id)
    {

    }

    public HashSet<Long> GetEntities()
    {
        assert (!m_Entities.isEmpty()) : "[Database::DeleteEntity] - There are no entities!";

        return m_Entities;
    }

    public <T extends IComponent> void CreateComponent(long entity, T component, Class<T> componentId)
    {
        assert (entity != Entity.Invalid) : "[Database::CreateComponent] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::CreateComponent] - Entity doesn't exist!";

        assert (!HasComponent(entity, componentId)) : "[Database::CreateComponent] - Component is already added!";

        m_Components.get(entity).add(component);

        //        AddDelayedCommand(new CreateComponentCommand(entity, component));
    }

    public <T extends IComponent> boolean HasComponent(long entity, Class<T> componentId)
    {
        assert (entity != Entity.Invalid) : "[Database::GetComponent] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::GetComponent] - Entity doesn't exist!";

        for (IComponent component : m_Components.get(entity))
        {
            if (component.getClass().getName().equals(componentId.getName()))
            {
                return true;
            }
        }

        return false;
    }

    public <T extends IComponent> HashSet<IComponent> GetComponents(long entity)
    {
        assert (entity == Entity.Invalid) : "[Database::GetComponents] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::GetComponents] - Entity doesn't exist!";

        return m_Components.get(entity);
    }

    public <T extends IComponent> T GetComponent(long entity, Class<T> componentId)
    {
        assert (entity != Entity.Invalid) : "[Database::GetComponent] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::GetComponent] - Entity doesn't exist!";

        for (IComponent component : m_Components.get(entity))
        {
            if (component.getClass().equals(componentId))
            {
                return (T) component;
            }
        }

        return null;
    }

    public <T extends IComponent> void DeleteComponent(long entity, Class<T> componentId)
    {
        assert (entity != Entity.Invalid) : "[Database::DeleteComponent] - Invalid entity!";
        assert (m_Entities.contains(entity)) : "[Database::DeleteComponent] - Entity doesn't exist!";

        ArrayList<IComponent> componentsToDelete = new ArrayList<>();

        for (IComponent component : m_Components.get(entity))
        {
            if (component.getClass().equals(componentId))
            {
                componentsToDelete.add(component);
            }
        }

        for (IComponent component : componentsToDelete)
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
