import java.util.HashMap;
import java.util.Map;

public interface Observer {
    Map<String, String> stocks = new HashMap<String, String>();

    void addStock(Stock stock);

    void checkPrice(Stock stock);
}
