package ru.mpei.brics.behaviours.activePowerImbalanceFSMSubbehaviours;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class ReceiveFitnessWithTimeOut extends ParallelBehaviour {

    private ReceiveFitValSubBehaviour receivingSubBehaviour = new ReceiveFitValSubBehaviour(myAgent);

    public ReceiveFitnessWithTimeOut(Agent a) {
        super(a, WHEN_ANY);
    }

    @Override
    public void onStart() {
        addSubBehaviour(this.receivingSubBehaviour);
        addSubBehaviour(new WakerBehaviour(myAgent, 1000) {
            @Override
            protected void onWake() {
                super.onWake();
            }
        });
    }

    @Override
    public int onEnd() {
//        int i = receivingSubBehaviour.onEnd();
        return receivingSubBehaviour.onEnd();
    }
}
