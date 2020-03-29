package com.madratmax;

import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        var builder = new JsonSettingsBuider("settings.json");

        // find second environment where user count is 35
        var env = builder
                .getSection("Accounts")
                .bySiblingKey("userCount")
                .byValue(35)
                .getValueByKey("environments", 1)
                .Value;

        // find login name for account within user count is 35
        var userLogin = builder
                .getSection("Accounts")
                .bySiblingKey("userCount")
                .byValue(35)
                .getValueByKey("login")
                .Value;

        // find password for account within user count is 35
        var userPass = builder
                .getSection("Accounts")
                .bySiblingKey("userCount")
                .byValue(35)
                .getValueByKey("password")
                .Value;

        System.out.println(env + "\n" + userLogin + "\n" + userPass);
    }
}




