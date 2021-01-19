package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.composite.Room;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CubatureVisitorTest {
    @Test
    void RoomMock() {
        Room mock_obj = mock(Room.class);

        when(mock_obj.calculateCubature()).thenReturn(120f);
        when(mock_obj.getId()).thenReturn(10);

        CubatureVisitor visitor = new CubatureVisitor (10);
        visitor.visitRoom(mock_obj);

        float cub = visitor.getCubature();
        verify(mock_obj).getId();
        verify(mock_obj).calculateCubature();
        assertEquals(120f, cub);

    }

}