package edu.uwp.appfactory.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

// object class
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Demo {
    @Id
    @Persistent
    private UUID id;

    @NotBlank
    @Size(max = 100)
    private String name;

    public Demo(String name) {
        this.name = name;
    }
}
