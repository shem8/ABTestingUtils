# ABTestingUtils

Simple class to let you A/B test easily on your android app.

For creating a new A/B test fir extend `ABTestingUtils` class
Than implement a new enum for what you want to test:

	public enum AB_TEST_CASE_EXAMPLE implements ABTestingEnum {
		OPTION_A(0.2), OPTION_B(0.2), OPTION_C(0.6);
		private final double value;
		
        	AB_TEST_CASE_EXAMPLE(double value) {
			this.value = value;
		}
		
		public double getValue() {
			return value;
		}
	}

Than to get the right option value just use:

	option = getABTestingType(sharedPref, AB_TEST_CASE_EXAMPLE.class, "AB_TEST_CASE_KEY");
