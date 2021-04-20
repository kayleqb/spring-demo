package edu.uwp.appfactory.demo.requestObjects;

import lombok.*;

// request object or POJO (google it) to use with requests
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoRequest {
    String name;
}
