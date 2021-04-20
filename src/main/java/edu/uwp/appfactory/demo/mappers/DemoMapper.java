package edu.uwp.appfactory.demo.mappers;

import edu.uwp.appfactory.demo.entities.Demo;
import edu.uwp.appfactory.demo.responseObjects.DemoResponse;
import org.mapstruct.Mapper;

// mapper that, in short..
// DemoResponse -- Object you want
// map -- map method
// (Demo demo) -- Object you have and want mapped to the object you want
@Mapper(componentModel = "spring")
public interface DemoMapper {
    DemoResponse map(Demo demo);
}
