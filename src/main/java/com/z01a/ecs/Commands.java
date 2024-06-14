package com.z01a.ecs;

class CreateEntityCommand implements ICommand {
    private long m_Id = Entity.Invalid;

    CreateEntityCommand(long id) {
        m_Id = id;
    }

    @Override
    public void Execute(Database database) {
        database.CreatePendingEntity(m_Id);
    }
}

class DeleteEntityCommand implements ICommand {
    private long m_Id = Entity.Invalid;

    DeleteEntityCommand(long id) {
        m_Id = id;
    }

    @Override
    public void Execute(Database database) {
        database.DeletePendingEntity(m_Id);
    }
}

class CreateComponentCommand implements ICommand {
    private IComponent m_Component = null;
    private long m_Id = Entity.Invalid;

    CreateComponentCommand(long id, IComponent component) {
        m_Component = component;
        m_Id = id;
    }

    @Override
    public void Execute(Database database) {
//        database.PopulateComponent(m_Id);
    }
}
