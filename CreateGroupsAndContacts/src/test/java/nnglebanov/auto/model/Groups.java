package nnglebanov.auto.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupModel> {

    private HashSet<GroupModel> delegate;

    public Groups(){
        this.delegate=new HashSet<GroupModel>();
    }

    public Groups(Groups groups){
        this.delegate=new HashSet<GroupModel>(groups.delegate);
    }

    @Override
    protected Set delegate() {
        return delegate;
    }

    public Groups withAdded(GroupModel groupModel){
        Groups groups=new Groups(this);
        groups.add(groupModel);
        return groups;
    }
    public Groups without(GroupModel groupModel){
        Groups groups=new Groups(this);
        groups.remove(groupModel);
        return groups;
    }
}
