package To_Do_List.shar1140.beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Task {
	private Long id;
	private String title;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")  // Specify the date format
    private Date dueDate;
	private boolean completed;
}
