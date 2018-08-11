package nnglebanov.auto.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import nnglebanov.auto.model.GroupModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupsGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file path")
    public String file;


    public static void main(String[] args) throws IOException {
        GroupsGenerator groupsGenerator = new GroupsGenerator();
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
        List<GroupModel> groups = generateGroups(count);
        save(groups, new File(file));
    }

    private void save(List<GroupModel> groups, File file) throws IOException {
        Gson gson=new Gson();
        String item=gson.toJson(groups);
        Writer writer=new FileWriter(file);
        writer.write(item);
        writer.close();
    }

    private List<GroupModel> generateGroups(int count) {
        List<GroupModel> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            GroupModel gm=new GroupModel()
                    .withName("TestName"+i)
                    .withHeader("TestHeader"+i)
                    .withFooter("TestFooter"+i);
            groups.add(gm);
        }
        return groups;
    }
}
