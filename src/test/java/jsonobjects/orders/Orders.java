package jsonobjects.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orders {
    private String name;
    private List<Order> order;
    private boolean success;
}
