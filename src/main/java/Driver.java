import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.service.Service;

public class Driver {
    public static void main(String[] args) {
        Service s = new Service();
        Ticket t = s.getTicketByID(1);
        System.out.println(t.getTimestamp());
    }
}
