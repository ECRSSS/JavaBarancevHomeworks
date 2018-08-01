package nnglebanov.auto.model;

import java.util.Objects;

public class GroupModel {
    private String groupName = "TestName";
    private String groupHeader = "TestHeader";
    private String groupFooter = "TestFooter";

    public GroupModel(String name, String logo, String comment) {
        groupName = name;
        groupHeader = logo;
        groupFooter = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupModel that = (GroupModel) o;
        return Objects.equals(groupName, that.groupName) &&
                Objects.equals(groupHeader, that.groupHeader) &&
                Objects.equals(groupFooter, that.groupFooter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupName, groupHeader, groupFooter);
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
