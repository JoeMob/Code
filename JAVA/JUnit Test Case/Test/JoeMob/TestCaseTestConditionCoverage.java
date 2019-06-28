package JoeMob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCaseTestConditionCoverage {
    private int x, y, z;
    TestCase testCase = new TestCase();

    public TestCaseTestConditionCoverage(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Parameterized.Parameters
    public static Collection conditionCoverageParameter() {
        return Arrays.asList(new Object[][]{
                {3, 4, 7},// F1F2 T3T4
                {4, 3, 11},// T1T2 F3F4
                {4, 3, 7}// T1F2 F3T4
        });
    }

    @Test
    public void conditionalCoverage() {
        System.out.println(this.x);
        System.out.println(this.y);
        System.out.println(this.z);
        testCase.Func(this.x, this.y, this.z);
    }
}