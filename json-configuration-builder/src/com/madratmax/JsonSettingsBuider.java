package com.madratmax;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonSettingsBuider
{
    private String jsonInput;
    private JSONParser parser;
    private JSONObject jsonData;
    private JSONObject currentSection;
    private JSONArray currentSections;
    private String sectionName;

    public JsonSettingsBuider(String jsonInput) throws IOException, ParseException {
        this.jsonInput = jsonInput;
        parser = new JSONParser();
        jsonData = (JSONObject) parser.parse(new FileReader(jsonInput));
        currentSections = new JSONArray();
    }

    public Object Value;

    /**
     * Define section by name.
     */
    public JsonSettingsBuider getSection(String section) throws IOException, ParseException {
        return this.getSection(section, -1);
    }

    /**
     * Define section from section array by name with specified index.
     */
    public JsonSettingsBuider getSection(String section, int index) throws IOException, ParseException {
        JSONObject jsonObject = null;

        if(currentSection == null){
            jsonObject = jsonData;
        }
        else{
            jsonObject = currentSection;
        }

        try{
            var sectionData = (JSONObject) jsonObject.get(section);
            currentSection = sectionData;
        }
        catch (ClassCastException ex){
            var sectionData = (JSONArray) jsonObject.get(section);

            if(index >= 0){
                currentSection = (JSONObject) sectionData.toArray()[index];
            }
            else{
                currentSections = sectionData;
            }
        }
        return this;
    }

    public JsonSettingsBuider getValueByKey(String key) throws IOException, ParseException {
        return this.getValueByKey(key, -1);
    }

    /**
     * Get value by specified key.
     */
    public JsonSettingsBuider getValueByKey(String key, Integer index) throws IOException, ParseException {
        Object value = null;

        try{
            value =  currentSection.get(key);
        }
        catch (NullPointerException ex){
            ex.printStackTrace();
        }

        if(value instanceof JSONArray)
        {
            try{
                var valuesArr =  (JSONArray) currentSection.get(key);
                var len = valuesArr.toArray().length;

                if(Value instanceof JSONObject){
                    currentSection = (JSONObject) Value;
                }

                if(index >= 0){
                    Value = valuesArr.toArray()[index];
                    return this;
                }
                else{
                    var arr = new ArrayList<Object>();

                    for (Object item: valuesArr
                         ) {
                        arr.add((Object) item);
                    }

                    Value = arr;
                    return this;
                }
            }
            catch (ArrayIndexOutOfBoundsException ex){
                Exceptions.ArrayIndexOutOfBoundsException(ex.getMessage());
            }
        }
        Value = currentSection.get(key);
        return this;
    }

    /**
     * Search in sections using unique key name.
     */
    public JsonSettingsBuider bySiblingKey(String keyName) throws IOException, ParseException, ParseException {
        this.bySiblingSection(keyName);
        return this;
    }

    /**
     * Search in sections using unique key value.
     */
    public JsonSettingsBuider byValue(Object keyValue) throws IOException, ParseException, ParseException {
        if(sectionName == null){
            Exceptions.NullPointerException("Section name is not specified. Use 'bySectionName(String section)' method before.");
        }

        for (Object section: currentSections) {
            this.currentSection = (JSONObject) section;
            var actualVal = currentSection.get(sectionName);
            if(actualVal != null && actualVal.toString().equals(keyValue.toString())){
                this.currentSection = (JSONObject) section;
                return this;
            }
        }
        throw new NullPointerException("value: '" + keyValue + "' does not match to the specified section ('" + sectionName + "')");
    }

    /**
     * Search using unique section name.
     */
    private JsonSettingsBuider bySiblingSection(String sectionName) throws IOException, ParseException, ParseException {
        if(this.currentSections == null){
            Exceptions.NullPointerException("No sections were defined. Use 'getSection(String sectionName)' method first.");
        }

        var counter = 0;
        for (Object section: currentSections) {
            if(section.toString().contains(sectionName)){
                this.currentSection = (JSONObject) section;
                this.currentSections.toArray()[counter] = section;
                break;
            }
            counter++;
        }
        this.sectionName = sectionName;
        return this;
    }
}