package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        // ToDo
        super(name, surname, year);
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        // ToDo
        JsonObject jsonObject = super.toJsonObject();
        List<JsonObject> examsList = new ArrayList<>();
        for (Tuple<String, Integer> exam: exams) {
            JsonObject examJsonObject = new JsonObject();
            examJsonObject.add(new JsonPair("course", new JsonString(exam.key)),
                    new JsonPair("mark", new JsonNumber(exam.value)),
                    new JsonPair("passed", new JsonBoolean(exam.value > 2)));
            examsList.add(examJsonObject);
        }

        JsonArray examsJsonArray = new JsonArray(examsList.stream().toArray(JsonObject[]::new));

        jsonObject.add(new JsonPair("exams", examsJsonArray));

        return jsonObject;
    }
}