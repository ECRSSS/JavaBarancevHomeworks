package nnglebanov.auto.model;

public class GroupModel {
    private String groupName = "TestName";
    private String groupHeader = "TestHeader";
    private String groupFooter = "TestFooter";

    public GroupModel(String name, String logo, String comment) {
        groupName = name;
        groupHeader = logo;
        groupFooter = comment;
    }

    public GroupModel() {
    }

    public GroupModel(String name){
        groupName=name;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String getGroupFooter() {
        return groupFooter;
    }
}
