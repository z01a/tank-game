import com.z01a.ecs.IComponent;
import com.z01a.ecs.Database;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatabaseTest extends TestCase {
    private Database m_Database;

    @Override
    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        m_Database = new Database();
    }

    @Override
    @AfterEach
    protected void tearDown() throws Exception {
        super.tearDown();
        m_Database.Destroy();
    }

    @Test
    @DisplayName("Test simple entity creation")
    void CreateEntities()
    {
        long entity1 = m_Database.CreateEntity();
        assertEquals(0, entity1);

        long entity2 = m_Database.CreateEntity();
        assertEquals(1, entity2);

        long entity3 = m_Database.CreateEntity();
        assertEquals(2, entity3);

        assertEquals(3, m_Database.GetEntities().size());
    }

    @Test
    @DisplayName("Test simple entity deletion")
    void DeleteEntities()
    {
        long entity1 = m_Database.CreateEntity();
        assertEquals(0, entity1);
        assertEquals(1, m_Database.GetEntities().size());
        assertEquals(true, m_Database.IsValidEntity(entity1));

        long entity2 = m_Database.CreateEntity();
        assertEquals(1, entity2);
        assertEquals(2, m_Database.GetEntities().size());
        assertEquals(true, m_Database.IsValidEntity(entity2));

        m_Database.DeleteEntity(entity2);
        assertEquals(false, m_Database.IsValidEntity(entity2));
        assertEquals(1, m_Database.GetEntities().size());

        long entity3 = m_Database.CreateEntity();
        assertEquals(2, entity3);
        assertEquals(true, m_Database.IsValidEntity(entity3));

        assertEquals(2, m_Database.GetEntities().size());
    }

    @Test
    @DisplayName("Test simple component creation")
    void CreateComponents()
    {
        class Position implements IComponent {
            public double m_X, m_Y;

            public Position(double x, double y)
            {
                m_X = x;
                m_Y = y;
            }
        }

        long entity1 = m_Database.CreateEntity();
        Position position1 = new Position(10, 10);
        m_Database.CreateComponent(entity1, position1, Position.class);
        assertEquals(true, m_Database.HasComponent(entity1, Position.class));

        assertEquals(10.0, m_Database.GetComponent(entity1, Position.class).m_X);
        assertEquals(10.0, m_Database.GetComponent(entity1, Position.class).m_Y);

        long entity2 = m_Database.CreateEntity();
        Position position2 = new Position(100, 100);
        m_Database.CreateComponent(entity2, position2, Position.class);
        assertEquals(true, m_Database.HasComponent(entity2, Position.class));

        assertEquals(100.0, m_Database.GetComponent(entity2, Position.class).m_X);
        assertEquals(100.0, m_Database.GetComponent(entity2, Position.class).m_Y);
    }

    @Test
    @DisplayName("Test simple component deletion")
    void DeleteComponents()
    {
        class Position implements IComponent {
            public double m_X, m_Y;

            public Position(double x, double y)
            {
                m_X = x;
                m_Y = y;
            }
        }

        class Velocity implements IComponent {
            public double m_X, m_Y;

            public Velocity(double x, double y)
            {
                m_X = x;
                m_Y = y;
            }
        }

        long entity1 = m_Database.CreateEntity();
        Position position1 = new Position(10, 10);
        Velocity velocity1 = new Velocity(20, 20);

        m_Database.CreateComponent(entity1, position1, Position.class);
        m_Database.CreateComponent(entity1, velocity1, Velocity.class);
        assertEquals(true, m_Database.HasComponent(entity1, Position.class));
        assertEquals(true, m_Database.HasComponent(entity1, Velocity.class));
        assertEquals(10.0, m_Database.GetComponent(entity1, Position.class).m_X);
        assertEquals(10.0, m_Database.GetComponent(entity1, Position.class).m_Y);
        assertEquals(20.0, m_Database.GetComponent(entity1, Velocity.class).m_X);
        assertEquals(20.0, m_Database.GetComponent(entity1, Velocity.class).m_Y);

        m_Database.DeleteComponent(entity1, Velocity.class);
        assertEquals(true, m_Database.HasComponent(entity1, Position.class));
        assertEquals(false, m_Database.HasComponent(entity1, Velocity.class));
    }
}
