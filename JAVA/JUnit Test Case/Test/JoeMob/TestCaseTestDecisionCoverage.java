package JoeMob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCaseTestDecisionCoverage {
    private int x, y, z;
    TestCase testCase = new TestCase();

    public TestCaseTestDecisionCoverage(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Parameterized.Parameters
    public static Collection decisionCoverageParameter() {
        return Arrays.asList(new Object[][]{
                {4, 4, 9},// TT
                {3, 3, 11}// FF
        });
    }

    @Test
    public void decisionCoverage() {
        System.out.println(this.x);
        System.out.println(this.y);
        System.out.println(this.z);
        testCase.Func(this.x, this.y, this.z);
    }
}