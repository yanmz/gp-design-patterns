import java.util.List;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        List<String> list = null;
        System.out.println(Optional.ofNullable(list).isPresent());
    }
}
