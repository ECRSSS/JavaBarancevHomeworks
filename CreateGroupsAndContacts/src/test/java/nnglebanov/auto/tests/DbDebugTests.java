package nnglebanov.auto.tests;

import org.testng.annotations.Test;

public class DbDebugTests extends TestBase{

    @Test
    public void test() {
        System.out.println(app.db().groups().iterator().next().getContacts());
        System.out.println(app.db().contacts().iterator().next().getGroups());
    }
}
