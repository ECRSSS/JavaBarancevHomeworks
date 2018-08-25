package nnglebanov.auto.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "group_list")
public class GroupModel {
    @Expose
    @Column(name="group_name")
    private String groupName;
    @Expose
    @Type(type ="text")
    @Column(name="group_header")
    private String groupHeader;
    @Expose
    @Column(name="group_footer")
    @Type(type ="text")
    private String groupFooter;

    @Id
    @Column(name="group_id")
    private int id;

    public GroupModel withName(String name){
        this.groupName=name;
        return this;
    }
    public GroupModel withHeader(String header){
        this.groupHeader=header;
        return this;
    }
    public GroupModel withFooter(String footer){
        this.groupFooter=footer;
        return this;
    }
    public GroupModel withId(int id){
        this.id=id;
        return this;
    }

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


    public GroupModel(String name, String logo, String comment) {
        groupName = name;
        groupHeader = logo;
        groupFooter = comment;
    }

    public GroupModel(String name, String logo, String comment, int groupId) {
        groupName = name;
        groupHeader = logo;
        groupFooter = comment;
        id = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupModel that = (GroupModel) o;
        return id == that.id &&
                Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupName, id);
    }

    public GroupModel() {
        groupName = "TestName";
    }

    public GroupModel(String name, int groupId) {
        groupName = name;
        id = groupId;
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
