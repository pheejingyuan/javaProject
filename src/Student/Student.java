package Student;

import User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student implements User {
    private @Getter @Setter int studentID;
    private @Getter @Setter String name;
    private @Getter @Setter String password;
    private @Getter @Setter String phoneNumber;

    public Student(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

}
