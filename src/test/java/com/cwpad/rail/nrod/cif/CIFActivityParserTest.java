package com.cwpad.rail.nrod.cif;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class CIFActivityParserTest {
    @Test
    public void allActivities() throws IOException {
        ClassLoader cl = getClass().getClassLoader();
        Set<String> activities = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                cl.getResourceAsStream("com/cwpad/rail/nrod/cif/activities.txt")))) {
            reader.lines()
                    .map(String::trim)
                    .forEach(activities::add);
        }

        for (String activity : activities) {
            for (int i = 0; i < activity.length() - 2; i+=2) {
                String a = activity.substring(i, i + 2).trim();
                if (a.length() > 0) {
                    assertNotNull(String.format("Checking activities: %s", a), Activity.parse(a));
                }
            }
        }
    }
}
