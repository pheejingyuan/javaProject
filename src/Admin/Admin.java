package Admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
    private @Getter @Setter int adminID;
    private @Getter @Setter String password;
    private @Getter @Setter String name;
    private @Getter @Setter String phoneNumber;
}
