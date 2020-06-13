package cn.joemob.data;

import lombok.Data;
import java.util.List;

@Data
public class Phone {
    private String id;
    private String name;
    private String price;
    private String voteCounts;
    private String voteRating;
    private List<String> describes;
    private String url;
}
