package JoeMob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCaseTestBranchConditionCombinationCoverage {
    private int x, y, z;
    TestCase testCase = new TestCase();

    public TestCaseTestBranchConditionCombinationCoverage(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Parameterized.Parameters
    public static Collection conditionalCombineParameter() {
        return Arrays.asList(new Object[][]{
                {4, 3, 11},//1TT 2FF
                {3, 4, 11},// 1FT 2TF
                {4, 4, 7},// 1TF 2TT
                {3, 3, 7}// 1FF 2FT
        });
    }

    @Test
    public void conditionalCombine() {
        System.out.println(this.x);
        System.out.println(this.y);
        System.out.println(this.z);
        testCase.Func(this.x, this.y, this.z);
    }
}