package com.example.universityeventstools.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ykoby_000 on 20.05.2014.
 */
public class PasswordValidator {


    private static final String PASSWORD_PATTERN = "(.{6,12})";
    private Pattern pattern;
    private Matcher matcher;

    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validate(final String password) {

        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}