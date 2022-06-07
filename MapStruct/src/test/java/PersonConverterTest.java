import com.entity.Person;
import com.entity.PersonDTO;
import com.entity.User;
import com.mapper.PersonConverter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PersonConverterTest {
    @Test
    public void test() {
//        Person person = new Person(1L,"zhige","zhige.me@gmail.com",new Date(),new User(1));
//        PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
//        assertNotNull(personDTO);
//        assertEquals(personDTO.getId(), person.getId());
//        assertEquals(personDTO.getName(), person.getName());
//        assertEquals(personDTO.getBirth(), person.getBirthday());
//        String format = DateFormatUtils.format(personDTO.getBirth(), "yyyy-MM-dd HH:mm:ss");
//        assertEquals(personDTO.getBirthDateFormat(),format);
//        assertEquals(personDTO.getBirthExpressionFormat(),format);
//
//        List<Person> people = new ArrayList<>();
//        people.add(person);
//        List<PersonDTO> personDTOs = PersonConverter.INSTANCE.domain2dto(people);
//        assertNotNull(personDTOs);
//
//
//
//        assertTrue(PersonConverter.INSTANCE.convert2Bool(1));
//        assertEquals((int)PersonConverter.INSTANCE.convert2Int(true),1);

        Person person1 = new Person(1L, "zhige", "zhige.me@gmail.com", new Date(), new User(1));
        PersonDTO personDTO1 = PersonConverter.INSTANCE.domain2dto(person1);
        person1.setName("xiaozhi");
        PersonConverter.INSTANCE.update(person1, personDTO1);
        System.out.println(person1);
        System.out.println(personDTO1);
        assertEquals("xiaozhi", personDTO1.getName());
    }
}