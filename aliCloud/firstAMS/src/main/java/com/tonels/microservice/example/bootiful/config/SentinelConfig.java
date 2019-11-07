package com.tonels.microservice.example.bootiful.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Collections;

@Configuration
public class SentinelConfig {

    @EventListener(ApplicationReadyEvent.class)
    public void config() {
        FlowRule fr = new FlowRule();
        fr.setRefResource("/code");
        fr.setCount(2);
        fr.setGrade(RuleConstant.FLOW_GRADE_QPS);
        fr.setLimitApp("default");

        FlowRuleManager.loadRules(Collections.singletonList(fr));

    }


}
