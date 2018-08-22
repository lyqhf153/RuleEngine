package com.engine.rule;

import com.engine.rule.domain.Rule;
import com.engine.rule.domain.SignalData;
import com.engine.rule.util.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by manoj on 23/08/18.
 */
public class RuleEngine {

    public static void main(String[] args) throws IOException {
        String inputPath = "/Users/manoj/Desktop/manoj/workplace/workspace/sparkflow/RuleEngine/src/main/resources/raw_data.json"; //args[0];
        String rulePath = "/Users/manoj/Desktop/manoj/workplace/workspace/sparkflow/RuleEngine/src/main/resources/rule.json";//args[1];
        List<SignalData> signalDatum = Util.readJsonStream(SignalData.class, new FileInputStream(new File(inputPath)));
        Map<String, List<Rule>> signalRuleMap =  Util.getSignalRuleMap(rulePath);
        Set<String> invalidDatum = Util.getInvalidData(signalDatum, signalRuleMap);
        invalidDatum.forEach(System.out::println);
    }
}
