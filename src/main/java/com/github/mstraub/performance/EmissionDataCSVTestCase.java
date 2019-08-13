package com.github.mstraub.performance;


public class EmissionDataCSVTestCase extends TestCase {

	protected String fileName;
	protected boolean useHashmap;
//	protected EmissionFactorHotContainer container;

	public EmissionDataCSVTestCase(String fileName, boolean useHashmap) {
		this.fileName = fileName;
		this.useHashmap = useHashmap;
//		container = new EmissionFactorHotContainer();
	}
	
	@Override
	public void prepareTest() {
		String[] header = {"VehicleCategory", "Emission", "TrafficSituation", "VehicleDescription",
				"Speed", "EmissionFactorHalfEmpty", "EmissionFactorEmpty", "EmissionFactorFull"};
		
	}
	
	@Override
	public void conductTest(int iterations) {
//		container.getEmissionFactor(emission, vehicle, rc, trafficState)

	}

	@Override
	public void conductTest2(int iterations) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void conductTest3(int iterations) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void conductTest4(int iterations) {
		// TODO Auto-generated method stub
		
	}

}
