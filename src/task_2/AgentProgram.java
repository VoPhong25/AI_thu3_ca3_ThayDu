package task_2;

import java.util.Random;

import task_2.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		Random random= new Random();
		int i= random.nextInt(4);
		if(p.getLocationState()==LocationState.DIRTY) {
			return new DynamicAction("SUCK");
		}else if(i==0) {
			return new DynamicAction("LEFT");
		}else if(i==1) {
			return new DynamicAction("RIGHT");
		}else if(i==2) {
			return new DynamicAction("UP");
		}else if(i==3) {
			return new DynamicAction("DOWN");
		}
		return NoOpAction.NO_OP;
		
	}
}
