package Course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;  

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
    private @Getter @Setter String cid;
    private @Getter @Setter String cname;
}
