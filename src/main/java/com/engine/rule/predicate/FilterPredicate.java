package com.engine.rule.predicate;

import com.engine.rule.domain.Rule;
import com.engine.rule.domain.SignalData;
import com.engine.rule.util.Util;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by manoj on 23/08/18.
 */
public class FilterPredicate implements Predicate<SignalData> {

    Map<String, List<Rule>> signalRuleMap;

    public FilterPredicate(Map<String, List<Rule>> signalRuleMap) {
        this.signalRuleMap = signalRuleMap;
    }

    @Override
    public boolean test(SignalData signalData) {
        List<Rule> rules = signalRuleMap.get(signalData.getSignal());
        if (rules == null) {
            return false;
        }

        List<Rule> listOfRulesAsPerDataType = rules.stream()
                .filter(rule -> rule.getType().equals(signalData.getValueType()))
                .collect(Collectors.toList());

        if (listOfRulesAsPerDataType == null) {
            return false;
        }

        return listOfRulesAsPerDataType.stream().anyMatch(rule ->
                {
                    switch (rule.getType()) {
                        case STRING: {
                            return Util.checkString(signalData, rule);
                        }
                        case INTEGER: {
                            return Util.checkInteger(signalData, rule);
                        }
                        case DATETIME: {
                            return Util.checkDateTime(signalData, rule);
                        }
                        default: {
                            return false;
                        }
                    }
                }
        );
    }

}
