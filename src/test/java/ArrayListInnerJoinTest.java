import entity.Line;
import org.junit.jupiter.api.Test;
import sql.JoinArrayList;

import java.util.ArrayList;

public class ArrayListInnerJoinTest extends InnerJoinTestBase {

    public ArrayListInnerJoinTest() {
        super(new ArrayList<>(InnerJoinTestBase.SIZE), new ArrayList<>(InnerJoinTestBase.SIZE));
    }

    @Test
    public void testJoinArrayList() {
        testJoin(new JoinArrayList());
    }
}
