package JoeMob;

public class TestCase {
    public int Func(int x, int y, int z) {
        if ((x > 3) && (z > 8))
            x++;
        else
            x--;
        if ((y == 4) || (z < 10))
            y++;
        else
            y--;
        return x + y + z;
    }
}
