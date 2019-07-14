package factory;

import java.util.ArrayDeque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

import model.Mobile;

public class MobileFactory {

	public static void main(String[] args) {
		ArrayList<Mobile> mobileList = new ArrayList<Mobile>(6);
		mobileList.add(new Mobile(1,5,7));
		mobileList.add(new Mobile(2,1,2));
		mobileList.add(new Mobile(3,8,2));
		mobileList.add(new Mobile(4,5,4));
		mobileList.add(new Mobile(5,3,7));
		mobileList.add(new Mobile(6,4,4));
		int time = MobileFactory.getInstance().produce(mobileList);
		System.out.println("==========================================================");
		System.out.println("Total time required for manufactoring : "+time +" mins");
		System.out.println("==========================================================");
	}
	
	private static MobileFactory instance;
	
	private MobileFactory() {
		
	}
	
	public static MobileFactory getInstance() {
		if(instance == null) {
			instance = new MobileFactory();
		}
		
		return instance;
	}

	private  int produce(ArrayList<Mobile> mobileList) {
		System.out.println("Given Mobiles :"+mobileList);
		Collections.sort(mobileList);
		System.out.println("Sorted Mobile on pmi " +mobileList);

		int aiRemainingTime = 0;
		int timeSpent = 0;
		Queue<Mobile> assemblyLine = new ArrayDeque<Mobile>();
		for(int i=0;i<mobileList.size();i++) {
			Mobile current = mobileList.get(i);
			timeSpent += current.getPmi();
			System.out.println("Parts Manufactured for mobile no :  "+current.getMobileNo()+ " timeSpent : "+timeSpent);
			if(!assemblyLine.isEmpty()) {
				aiRemainingTime +=  current.getPmi();
				while(!assemblyLine.isEmpty()) {
							Mobile currentAssemblyMobile = assemblyLine.peek();
							System.out.println(" Assembling mobile no:  "+currentAssemblyMobile.getMobileNo() +" Need : "+currentAssemblyMobile.getAi());
							if( (aiRemainingTime-currentAssemblyMobile.getAi()) < 0) {
								currentAssemblyMobile.setAi(currentAssemblyMobile.getAi() - aiRemainingTime);
								System.out.println("Partially Assembling mobile no:  "+currentAssemblyMobile.getMobileNo() +" Remaining Need : "+currentAssemblyMobile.getAi());

								aiRemainingTime = 0;
								break;
							}else {
								aiRemainingTime =  aiRemainingTime - currentAssemblyMobile.getAi();
								assemblyLine.remove();
								System.out.println("Assembling completed "+currentAssemblyMobile.getMobileNo());
							}
				}
				if(assemblyLine.isEmpty()) {
					System.out.println("Assembling time wasted "+aiRemainingTime);
					aiRemainingTime =0;
				}
				assemblyLine.add(current);
		}else {
				assemblyLine.add(current);
			}
		}
		
		while(!assemblyLine.isEmpty()) { 
			Mobile currentAssemblyMobile = assemblyLine.remove();
			System.out.println("Manufactoring completed for all mobiles. Assembling Remaining Mobile : "+currentAssemblyMobile.getMobileNo() + " Adding assembly time : "+currentAssemblyMobile.getAi());
			timeSpent += currentAssemblyMobile.getAi();
			System.out.println("Assembling completed (Final) : "+currentAssemblyMobile.getMobileNo());

		}
		
		return timeSpent;
	}

}
