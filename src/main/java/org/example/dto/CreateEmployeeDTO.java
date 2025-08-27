package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateEmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String position;
    private Double basicSalary;
    private Double grossSemiMonthly;
    private Double hourlyRate;
    private String sss;
    private String philhealth;
    private String pagibig;
    private String tin;
    private List<AllowanceDTO> allowances = new ArrayList<>();

}
