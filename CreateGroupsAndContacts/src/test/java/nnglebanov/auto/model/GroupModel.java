package nnglebanov.auto.model;

import java.util.Objects;

public class GroupModel {
    private String groupName;
    private String groupHeader;
    private String groupFooter;

    @Override
    public String toString() {
        return "GroupModel{" +
                "groupName='" + groupName + '\'' +
                ", groupHeader='" + groupHeader + '\'' +
                ", groupFooter='" + groupFooter + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    private int id;

    public GroupModel(String name, String logo, String comment) {
        groupName = name;
        groupHeader = logo;
        groupFooter = comment;
    }
    public GroupModel(String name, String logo, String comment,int groupId) {
        groupName = name;
        groupHeader = logo;
        groupFooter = comment;
        id=groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupModel that = (GroupModel) o;
        return Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupName, groupHeader, groupFooter);
    }

    public GroupModel() {
        groupName="TestName";
        groupFooter="TestFooter";
        groupHeader="TestHeader";
    }

    public GroupModel(String name,int groupId){
        groupName=name;
        id=groupId;
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
