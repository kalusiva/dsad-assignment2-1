package model;

public class Mobile implements Comparable<Mobile> {
		public static String method = "pmi";
		public Mobile(int mobileNo, int pmi, int ai) {
		super();
		this.mobileNo = mobileNo;
		this.pmi = pmi;
		this.ai = ai;
	}
		public int getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(int mobileNo) {
			this.mobileNo = mobileNo;
		}
		public int getPmi() {
			return pmi;
		}
		public void setPmi(int pmi) {
			this.pmi = pmi;
		}
		public int getAi() {
			return ai;
		}
		public void setAi(int ai) {
			this.ai = ai;
		}
		private int mobileNo = 0;
		private int pmi = 0;
		private int ai = 0;
		@Override
		public int compareTo(Mobile o) {
				if(pmi == o.getPmi()) {
					return ai - o.getAi();
				}else {
					return pmi - o.getPmi();		
				}
		}
		public String toString() {
			return "mobile no : "+mobileNo+" pmi : "+pmi+" ai : "+ai+"\n";
		}
}
