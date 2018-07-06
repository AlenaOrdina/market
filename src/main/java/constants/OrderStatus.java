package constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OrderStatus {
    BUCKET, DONE, UNDONE;

    public static List<OrderStatus> orderValues(){
        List<OrderStatus> statuses = new ArrayList<>(Arrays.asList(OrderStatus.values()));
        statuses.remove(BUCKET);
        return statuses;
    }

    public static OrderStatus valueOfOrNull(String enumValue){
        if(enumValue == null)
            return null;
        else
            return OrderStatus.valueOf(enumValue);
    }
}
