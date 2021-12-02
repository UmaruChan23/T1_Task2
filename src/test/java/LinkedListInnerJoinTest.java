import entity.Line;
import org.junit.jupiter.api.Test;
import sql.JoinLinkedList;

import java.util.LinkedList;

public class LinkedListInnerJoinTest extends InnerJoinTestBase {
    public LinkedListInnerJoinTest() {
        super(new LinkedList<>(), new LinkedList<>());
    }

    @Test
    public void testJoinLinkedList() {
        testJoin(new JoinLinkedList());
    }
}
