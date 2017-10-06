package ua.com.cashup.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.cashup.application.entity.Client;
import ua.com.cashup.application.enums.Gender;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Вадим on 04.10.2017.
 */
public class Test {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");

    static EntityManager em = emf.createEntityManager();

    private static String path = "C:\\IdeaProject\\ClientsOrdersTask\\src\\main\\java\\test.txt";

    public static void main(String[] args) throws IOException, ParseException {

        ObjectMapper mapper = new ObjectMapper();
        Client client = new Client();
        client.setName("Vadym");
        client.setSurname("Pylypchenko");
        client.setBirthday(new Date());
        client.setGender(Gender.MALE);
        client.setTIN(11111);
//        mapper.
        mapper.writeValue(new File(path),Client.class);
        System.out.println(client);
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();


        em.close();
        emf.close();
    }
}
