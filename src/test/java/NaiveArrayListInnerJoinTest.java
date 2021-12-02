import org.junit.jupiter.api.Test;
import sql.NaiveJoinArrayList;

import java.util.ArrayList;

public class NaiveArrayListInnerJoinTest extends InnerJoinTestBase {

    public NaiveArrayListInnerJoinTest() {
        super(new ArrayList<>(InnerJoinTestBase.SIZE), new ArrayList<>(InnerJoinTestBase.SIZE));
    }

    @Test
    public void testJoinArrayList() {
        testJoin(new NaiveJoinArrayList());
    }
}
