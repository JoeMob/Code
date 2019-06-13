package JoeMob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCaseTestDecisionConditionalCoverage {
    private int x, y, z;
    TestCase testCase = new TestCase();

    public TestCaseTestDecisionConditionalCoverage(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Parameterized.Parameters
    public static Collection conditionalDecisionCoverageParameter() {
        return Arrays.asList(new Object[][]{
                {4, 3, 8},// T1F2F3T4 1F2T
                {3, 4, 11},// F1T2T3F4 1F2T
                {4, 3, 11}// T1T2F3F4 1T2F
        });
    }

    @Test
    public void conditionalDecisionCoverage() {
        System.out.println(this.x);
        System.out.println(this.y);
        System.out.println(this.z);
        testCase.Func(this.x, this.y, this.z);
    }
}