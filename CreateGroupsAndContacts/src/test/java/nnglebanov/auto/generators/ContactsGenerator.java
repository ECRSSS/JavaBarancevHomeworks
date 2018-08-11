package nnglebanov.auto.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nnglebanov.auto.model.ContactModel;
import nnglebanov.auto.model.GroupModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactsGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file path")
    public String file;


    public static void main(String[] args) throws IOException {
        ContactsGenerator groupsGenerator = new ContactsGenerator();
        JCommander jc = new JCommander(groupsGenerator);
        try {
            jc.parse(args);
        } catch (ParameterException ex) {
            jc.usage();
            return;
        }
        groupsGenerator.run();
    }

    private void run() throws IOException {
        List<ContactModel> groups = generateContacts(count);
        save(groups, new File(file));
    }

    private void save(List<ContactModel> contacts, File file) throws IOException {
        Gson gson=new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String item=gson.toJson(contacts);
        Writer writer=new FileWriter(file);
        writer.write(item);
        writer.close();
    }

    private List<ContactModel> generateContacts(int count) {
        List<ContactModel> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ContactModel cm=new ContactModel()
                    .withFirstName("FirstName"+i)
                    .withLastName("LastName"+i)
                    .withAddress("TestAddress"+i)
                    .withHomePhone(""+i)
                    .withFirstEmail("email"+i);
            contacts.add(cm);
        }
        return contacts;
    }
}