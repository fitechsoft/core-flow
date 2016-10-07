package com.fitechsoft.flow.core;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by chun on 2016/10/7.
 */
public class FFProcessRegistry {

    private static FFProcessRegistry registry;

    private Map<String, FFProcess> processMap;

    static {
        registry = new FFProcessRegistry();
    }

    protected FFProcessRegistry() {
        super();
        processMap = new HashMap<>();
    }

    public static FFProcessRegistry getProcessRegistry() {
        return registry;
    }

    public void register(FFProcess process) {
        this.processMap.put(process.getProcessName(), process);
    }

    public List<FFProcess> getRegisteredProcessesByCategory(String category) {

        if (null == category) {
            return null;
        }

        List<FFProcess> processes = new ArrayList<>();
        for (Map.Entry<String, FFProcess> entry : processMap.entrySet()) {
            FFProcess value = entry.getValue();

            if (category.equals(value.getCategory())) {
                processes.add(value);
            }

        }

        return processes;
    }

    public FFProcess getRegisteredProcessesByName(String name) {

        if (null == name) {
            return null;
        }

        for (Map.Entry<String, FFProcess> entry : processMap.entrySet()) {
            if (name.equals(entry.getKey())) {
                return entry.getValue();
            }
        }

        return null;
    }


}
