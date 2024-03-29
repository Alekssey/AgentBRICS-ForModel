package ru.mpei.brics.behaviours.activePowerImbalanceFSMSubbehaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import lombok.extern.slf4j.Slf4j;
import ru.mpei.brics.agents.NetworkElementAgent;
import ru.mpei.brics.behaviours.AnalyzeFrequency;
import ru.mpei.brics.extention.configirationClasses.NetworkElementConfiguration;

@Slf4j
public class LastBehaviour extends OneShotBehaviour {

    private NetworkElementConfiguration cfg = ((NetworkElementAgent) myAgent).getCfg();


    public LastBehaviour(Agent a) {
        super(a);
//        System.err.println(myAgent.getLocalName() + " last behaviour created");
    }

//    @Override
//    public void onStart() {
//        System.err.println(myAgent.getLocalName() + " last behaviour start");
//    }

    @Override
    public void action() {
        log.info("\nFSM end. " +
                "\nFitness list: {}; " +
                "\nDeque map: {} " +
                "\nCurrent power: {}\n\n",
                cfg.getFitnessValues(), cfg.getAgentsQueue(), cfg.getCurrentP());
        cfg.setPTradeIsOpen(false);
        cfg.getFitnessValues().clear();
        cfg.getAgentsQueue().clear();
        ((NetworkElementAgent) myAgent).setKieSession(null);
        myAgent.addBehaviour(new AnalyzeFrequency(myAgent, ((NetworkElementAgent) myAgent).getCfg().getUdpMonitoringPeriod()));
        log.error("regulating time: {} seconds", (System.currentTimeMillis() - ((NetworkElementAgent) myAgent).getStartTime()) / 1000);
    }

//    @Override
//    public int onEnd() {
//        System.err.println(myAgent.getLocalName() + " last behaviour end");
//        return 0;
//    }
}
